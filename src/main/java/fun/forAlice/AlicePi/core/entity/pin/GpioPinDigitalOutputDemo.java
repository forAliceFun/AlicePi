package fun.forAlice.AlicePi.core.entity.pin;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.pi4j.io.gpio.PinState;

public interface GpioPinDigitalOutputDemo extends com.pi4j.io.gpio.GpioPinDigitalOutput {
	@Override
	default Future<?>	blink(long delay) {return null;}
	@Override
	default Future<?>	blink(long delay, long duration) {return null;}
	@Override
	default Future<?>	blink(long delay, long duration, PinState blinkState) {return null;}
	@Override
	default Future<?>	blink(long delay, PinState blinkState) {return null;}
	@Override
	default void	high() {}
	@Override
	default void	low() {}
	@Override
	default Future<?>	pulse(long duration)  {return null;}
	@Override
	default Future<?>	pulse(long duration, boolean blocking) {return null;} 
	@Override
	default Future<?>	pulse(long duration, boolean blocking, Callable<Void> callback) {return null;} 
	@Override
	default Future<?>	pulse(long duration, Callable<Void> callback)  {return null;}
	@Override
	default Future<?>	pulse(long duration, PinState pulseState)  {return null;}
	@Override
	default Future<?>	pulse(long duration, PinState pulseState, boolean blocking)  {
		return null;
	}
	@Override
	default Future<?>	pulse(long duration, PinState pulseState, boolean blocking, Callable<Void> callback) {return null;} 
	@Override
	default Future<?>	pulse(long duration, PinState pulseState, Callable<Void> callback) {return null;} 
	@Override
	default void	setState(boolean state)  {}
	@Override
	default void	setState(PinState state)  {}
	@Override
	default void	toggle()  {}
}
