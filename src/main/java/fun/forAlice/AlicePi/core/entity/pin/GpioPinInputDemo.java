package fun.forAlice.AlicePi.core.entity.pin;

import java.util.Collection;
import java.util.List;

import com.pi4j.io.gpio.trigger.GpioTrigger;

public interface GpioPinInputDemo extends com.pi4j.io.gpio.GpioPinInput {

	@Override
	default void addTrigger(GpioTrigger... trigger) {

	}

	@Override
	default void addTrigger(List<? extends GpioTrigger> triggers) {

	}

	@Override
	default Collection<GpioTrigger> getTriggers() {
		return null;
	}

	@Override
	default void removeAllTriggers() {

	}

	@Override
	default void removeTrigger(GpioTrigger... trigger) {

	}

	@Override
	default void removeTrigger(List<? extends GpioTrigger> triggers) {

	}

}
