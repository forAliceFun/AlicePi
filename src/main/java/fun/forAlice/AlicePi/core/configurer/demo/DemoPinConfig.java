package fun.forAlice.AlicePi.core.configurer.demo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.pi4j.io.gpio.GpioPinDigitalMultipurpose;

import fun.forAlice.AlicePi.core.condition.WindowsCondition;
import fun.forAlice.AlicePi.core.entity.pin.GpioPinImplDemo;

@Configuration
public class DemoPinConfig {
    Logger logger = LoggerFactory.getLogger("Demo Environment PinConig");

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
}
