package fun.forAlice.AlicePi.core.configurer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import fun.forAlice.AlicePi.core.condition.LinuxCondition;
import fun.forAlice.AlicePi.core.condition.WindowsCondition;
import fun.forAlice.AlicePi.core.entity.GpioPinDigitalOutputDemo;
import fun.forAlice.AlicePi.core.service.IGpioService;
import fun.forAlice.AlicePi.core.service.impl.GpioDemoServiceImpl;
import fun.forAlice.AlicePi.core.service.impl.GpioRaspServiceImpl;

@Configuration
public class ConditionConfig {
	@Bean
	@Conditional(WindowsCondition.class)
	public List<GpioPinDigitalOutput> gpioDemoService() {
		List<GpioPinDigitalOutput> pinList  = new ArrayList<GpioPinDigitalOutput>();
		for(int i=0;i<32;i++) {
			pinList.add(new GpioPinDigitalOutputDemo());
		}
		return pinList;
	}

	@Bean
	@Conditional(LinuxCondition.class)
	public List<GpioPinDigitalOutput> gpioRaspService() {
		GpioController gpioCtr = GpioFactory.getInstance();
		List<GpioPinDigitalOutput> pinList  = new ArrayList<GpioPinDigitalOutput>();
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_00 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_01 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_02 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_03 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_04 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_05 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_06 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_07 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_08 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_09 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_10 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_11 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_12 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_13 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_14 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_15 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_16 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_17 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_18 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_19 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_20 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_21 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_22 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_23 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_24 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_25 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_26 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_27 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_28 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_29 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_30 ,"DigitalOutput", PinState.LOW));
		pinList.add(gpioCtr.provisionDigitalOutputPin(RaspiPin.GPIO_31 ,"DigitalOutput", PinState.LOW));

		return pinList;
	}
}
