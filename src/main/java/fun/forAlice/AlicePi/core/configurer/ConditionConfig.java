package fun.forAlice.AlicePi.core.configurer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigital;
import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import fun.forAlice.AlicePi.core.condition.LinuxCondition;
import fun.forAlice.AlicePi.core.condition.WindowsCondition;
import fun.forAlice.AlicePi.core.entity.pin.GpioPinImplDemo;
import fun.forAlice.AlicePi.core.service.IGpioService;


@Configuration
public class ConditionConfig {
    Logger logger = LoggerFactory.getLogger(ConditionConfig.class);

    @Bean
	@Conditional(WindowsCondition.class)
	public List<GpioPinDigitalMultipurpose> gpioDemoService() {
		List<GpioPinDigitalMultipurpose> pinList  = new ArrayList<>();
		for(int i=0;i<32;i++) {
			GpioPinDigitalMultipurpose pin =  (GpioPinDigitalMultipurpose) new GpioPinImplDemo();
			pinList.add(pin);
		}
		return pinList;
	}

	@Bean
	@Conditional(LinuxCondition.class)
	public List<GpioPinDigitalMultipurpose> gpioRaspService() {
		GpioController gpioCtr = GpioFactory.getInstance();
		List<GpioPinDigitalMultipurpose> pinList  = new ArrayList<>();
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_00 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_01 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_02 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_03 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_04 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_05 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_06 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_07 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_08 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_UP));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_09 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_UP));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_10 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_11 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_12 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_13 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_14 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_15 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_16 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_17 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_18 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_19 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_20 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_21 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_22 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_23 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_24 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_25 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_26 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_27 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_28 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_29 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_DOWN));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_30 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_UP));
		pinList.add(gpioCtr.provisionDigitalMultipurposePin(RaspiPin.GPIO_31 ,PinMode.DIGITAL_OUTPUT, PinPullResistance.PULL_UP));

		return pinList;
	}
}
