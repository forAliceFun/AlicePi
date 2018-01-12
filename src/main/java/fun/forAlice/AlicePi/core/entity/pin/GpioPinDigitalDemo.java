package fun.forAlice.AlicePi.core.entity.pin;

import com.pi4j.io.gpio.PinState;

public interface GpioPinDigitalDemo extends com.pi4j.io.gpio.GpioPinDigital {

	@Override
	default PinState getState() {
		return null;
	}

	@Override
	default boolean isHigh() {
		return false;
	}

	@Override
	default boolean isLow() {
		return false;
	}

	@Override
	default boolean isState(PinState state) {
		return false;
	}
}
