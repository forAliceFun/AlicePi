package fun.forAlice.AlicePi.core.entity.pin;

import com.pi4j.io.gpio.PinState;

public  interface GpioPinDigitalInputDemo extends  com.pi4j.io.gpio.GpioPinDigitalInput {

	@Override
	default int	getDebounce(PinState state) {
		return 0;
	}
	
	@Override
	default boolean hasDebounce(PinState state) {
		return false;
	}
	
	@Override
	default void setDebounce(int debounce) {
		
	}
	
	@Override 
	default void 	setDebounce(int debounce, PinState... state) {
		
	}
}
