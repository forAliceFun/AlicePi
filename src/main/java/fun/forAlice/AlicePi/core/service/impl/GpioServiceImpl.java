package fun.forAlice.AlicePi.core.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigital;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;

import fun.forAlice.AlicePi.core.configurer.RedisConfig;
import fun.forAlice.AlicePi.core.entity.Gpio;
import fun.forAlice.AlicePi.core.service.IRedisService;

@Service
public class GpioServiceImpl extends IRedisService<Gpio> {
	private Logger logger = LoggerFactory.getLogger(GpioServiceImpl.class);

	private final String REDIS_KEY = "GPIO";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	HttpHookServiceImpl httpHookService;

	// final GpioController gpio = GpioFactory.getInstance();

	@Override
	protected String getRedisKey() {
		return this.REDIS_KEY;
	}

	private List<Gpio> gpioList;

	@Autowired
	private List<GpioPinDigitalMultipurpose> pinList;
	

	private List<Map<String,GpioCallbackTrigger>> callbackPool = new ArrayList<>();

	private ValueOperations<String, Object> ops;

	@PostConstruct
	public void init() {
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Gpio>(Gpio.class));
		redisTemplate.afterPropertiesSet();
		this.ops = redisTemplate.opsForValue();

		this.gpioList = new ArrayList<Gpio>();
		for (int i = 0; i < 32; i++) {
			this.gpioList.add(new Gpio(i));
			callbackPool.add(new HashMap<>());
		}

	}

	@Scheduled(cron = "*/10 * * * * *")
	/**
	 * | | | | | | | | | | | | | | | | | +----- any day of the week. | | | |
	 * +------- any month (September). | | | +--------- any day of the month. | |
	 * +----------- every hour of the day. | +------------- top of the hour (minutes
	 * = 0). +--------------- top of the minute (seconds = 0).
	 */
	public List<Gpio> load() {
		for (int i = 0; i < 32; i++) {
			Gpio gpio = (Gpio) ops.get(Gpio.generateKey(i));
			if (gpio == null) {
				gpio = new Gpio(i);
				ops.set("gpio_" + i, gpio);
			}
			this.gpioList.set(gpio.getId(), gpio);
		}
		this.refresh();
		logger.debug("reload gpio status");
		return this.gpioList;
	}

	private void save() {
		for (Gpio it : this.gpioList) {
			ops.set(it.getKey(), it);
		}
	}

	private void refresh() {

		for (Gpio gpio : gpioList) {
			if (gpio.getType().equals("output")) {
				this.outputRefresh(gpio);
			}
			if (gpio.getType().equals("input")) {
				
				this.inputRefresh(gpio);

			}
		}


	}

	private void outputRefresh(Gpio outputGpio) {

		GpioPinDigitalMultipurpose pin = pinList.get(outputGpio.getId());
		pin.setMode(PinMode.DIGITAL_OUTPUT);
		if (outputGpio.getOutput().equals(1)) {

			if (pin.getState().equals(PinState.LOW)) {
				pin.high();
				logger.debug("gpio pin " + outputGpio.getId() + " : " + "on");

			}
		} else {
			if (pin.getState().equals(PinState.HIGH)) {
				pin.low();
				logger.debug("gpio pin " + outputGpio.getId() + " : " + "off");
			}

		}

	}

	private void inputRefresh(Gpio inputGpio) {
		Gpio inputGpio2 = inputGpio;
		this.pin2Input(inputGpio);
		
		Map<String, GpioCallbackTrigger> hookCallback = callbackPool.get(inputGpio2.getId());
		Set<String> oldHooks = hookCallback.keySet();
		Set<String> newHooks = inputGpio2.getHook();

		for(String hook:newHooks) {
			if(oldHooks.contains(hook)) {
				logger.info(hook+" exist");
				continue;
			}
			logger.info("gpio " + inputGpio2.getId() + ", add hook: " + hook);
			GpioCallbackTrigger callback2 = this.generateCallback(hook ,inputGpio2);
			hookCallback.put(hook, 	callback2);
		}
		gpioList.set(inputGpio2.getId(), inputGpio2);
		callbackPool.set(inputGpio2.getId(), hookCallback);
		
		// 移除失效的回调
		oldHooks.forEach(hook->{
			if(newHooks.contains(hook)) {
				return;
			}
			this.removeCallback(hook,inputGpio2);
			hookCallback.remove(hook);
		});			
		
		logger.info("gpio " + inputGpio2.getId() + " hooks :"+hookCallback.keySet().toString());
	}
	
	private void pin2Input(Gpio gpio) {
		GpioPinDigitalMultipurpose pin = pinList.get(gpio.getId());
		pin.setMode(PinMode.DIGITAL_INPUT);
		pin.setPullResistance(PinPullResistance.PULL_DOWN);
	}
	private GpioCallbackTrigger generateCallback(String hook,Gpio gpio) {
		GpioPinDigitalMultipurpose pin = pinList.get(gpio.getId());
		GpioCallbackTrigger callback =  new GpioCallbackTrigger(() -> {
			int input = pin.getState() == PinState.HIGH ? 1 : 0;
			gpio.setInput(input);
			String json = objectMapper.writeValueAsString(gpio);
			httpHookService.postHook(hook, json);
			logger.info("send: " + json);
			return null;
		});
		pin.addTrigger(callback);
		return callback;
	}
	
	private void removeCallback(String hook,Gpio gpio) {
		GpioPinDigitalMultipurpose pin = pinList.get(gpio.getId());
		Map<String, GpioCallbackTrigger> hookCallback = callbackPool.get(gpio.getId());
		GpioCallbackTrigger callback = hookCallback.get(hook);
		logger.info("gpio " + gpio.getId() + "remove " + hook);
		pin.removeTrigger(callback);
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
		System.out.println("Spring Container is destroy! Customer clean up");
	}

	public List<Gpio> getGpioList() {
		this.load();
		return this.gpioList;
	}

	public Gpio getGpioById(Integer id) {
		return this.gpioList.get(id);
	}

	public void updateGpio(Gpio gpio) {
		this.gpioList.set(gpio.getId(), gpio);
		this.save();

		this.refresh();

	}

	public void pinDemo() {

		String uuid = "121";
		logger.info("jedisPool uuid : " + uuid);
		Gpio gpio = new Gpio();
		ops.set("m", gpio);
		// try (Jedis jedis = jedisPool.getResource()) {
		// jedis.setex(uuid, 1000, "xutong");
		// }
	}

}
