package fun.forAlice.AlicePi.core.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition{
	
	public boolean matches(ConditionContext context, 
						AnnotatedTypeMetadata metadata) {
		System.out.println(context.getEnvironment().getProperty("os.name"));
		return context.getEnvironment()
						.getProperty("os.name") // such as Linux
						.contains("Linux");
	}
}
