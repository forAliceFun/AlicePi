package fun.forAlice.AlicePi.core.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import fun.forAlice.AlicePi.core.configurer.RedisConfig;

public class WindowsCondition implements Condition{
    Logger logger = LoggerFactory.getLogger(Condition.class);

	public boolean matches(ConditionContext context, 
						AnnotatedTypeMetadata metadata) {
		logger.info("os环境："+context.getEnvironment().getProperty("os.name"));
		return context.getEnvironment()
						.getProperty("os.name") // such as Windows 7
						.contains("Windows");
	}
}
