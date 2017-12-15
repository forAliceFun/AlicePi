package fun.forAlice.AlicePi.core.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition{
	
	public boolean matches(ConditionContext context, 
						AnnotatedTypeMetadata metadata) {
		return context.getEnvironment()
						.getProperty("os.name") // such as Windows 7
						.contains("Windows");
	}
}
