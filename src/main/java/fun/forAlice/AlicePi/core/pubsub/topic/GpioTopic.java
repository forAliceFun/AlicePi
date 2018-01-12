package fun.forAlice.AlicePi.core.pubsub.topic;

import org.springframework.stereotype.Component;

import fun.forAlice.AlicePi.core.pubsub.annotation.Topic;


@Component
public class GpioTopic {

	@Topic("/gpios")
	void gpios(String json) {
		
	}
	
	@Topic("/gpios/{id}")
	void gpioPin(String id,String json) {
		
	}
	
}
