package fun.forAlice.AlicePi.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import fun.forAlice.AlicePi.core.configurer.RedisConfig;
import fun.forAlice.AlicePi.core.entity.Gpio;
import fun.forAlice.AlicePi.core.service.IRedisService;

@Service
public class GpioServiceImpl extends IRedisService<Gpio>{
    private Logger logger = LoggerFactory.getLogger(GpioServiceImpl.class);
    
    private  final String REDIS_KEY = "GPIO";
    
	@Autowired
	private ObjectMapper objectMapper;
    

    
//    final GpioController gpio = GpioFactory.getInstance();

    @Override
    protected String getRedisKey() {
        return this.REDIS_KEY;
    }   


    private List<Gpio> gpioList; 
    
    @Autowired
    private List<GpioPinDigitalOutput> pinList;
	
    private ValueOperations<String, Object> ops;
    
    @PostConstruct
	public void init(){
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Gpio>(Gpio.class));
        redisTemplate.afterPropertiesSet();
        ops = redisTemplate.opsForValue();

		gpioList = new ArrayList<Gpio>();
		
		for(int i=0; i<32;i++) {
			gpioList.add(new Gpio(i));
		}
	}

	@Scheduled(cron = "*/5 * * * * *")
	/**                | | | | | | 
	 *                 | | | | | | 
	 *                 | | | | | +----- any day of the week. 
	 *                 | | | | +------- any month (September).
	 *                 | | | +--------- any day of the month.
	 *                 | | +----------- every hour of the day.
	 *                 | +------------- top of the hour (minutes = 0).
	 *                 +--------------- top of the minute (seconds = 0).
	 */
    public List<Gpio> load(){
    	for(int i=0;i<32;i++) {
    		Gpio gpio = (Gpio) ops.get(Gpio.generateKey(i));
    		this.gpioList.set(gpio.getId(), gpio);
    	}
    	this.refresh();
    	logger.info("reload gpio status");
    	return this.gpioList;
    }
    private void save() {
		for(Gpio it :this.gpioList) {
			ops.set(it.getKey(), it);
		}   	
    }
    
    private void refresh() {
    	for(Gpio it:gpioList) {
    		if(it.getOutput().equals(1)) {
    			pinList.get(it.getId()).high();
    			logger.info("gpio pin "+it.getId()+" : "+"on");
    		}
    		else {
    			pinList.get(it.getId()).low();
    			logger.info("gpio pin "+it.getId()+" : "+"off");
    		}
    		
    	}
    }

	@PreDestroy
	public void cleanUp() throws Exception {
	  System.out.println("Spring Container is destroy! Customer clean up");
	}
	
	public List<Gpio> getGpioList(){
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
//        try (Jedis jedis = jedisPool.getResource()) {
//            jedis.setex(uuid, 1000, "xutong");
//        }
	}
	
}
