package fun.forAlice.AlicePi.core.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JsonConfig {
	@Bean 
	public ObjectMapper ObjectMapper() {
		return new ObjectMapper();
	}
}
