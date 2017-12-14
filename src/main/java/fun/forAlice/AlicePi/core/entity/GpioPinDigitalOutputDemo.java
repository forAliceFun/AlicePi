package fun.forAlice.AlicePi.core.entity;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;

public class GpioPinDigitalOutputDemo extends GpioPinDigitalDemo
										implements GpioPinDigitalOutput{
	@Override
	public Future<?>	blink(long delay){
		return null;
	}
	@Override
	public Future<?>	blink(long delay, long duration) {
		return null;
	}
	@Override
	public Future<?>	blink(long delay, long duration, PinState blinkState){
		return null;
	} 
	@Override
	public Future<?>	blink(long delay, PinState blinkState) {
		return null;
	}
	@Override
	public void	high() {
		
	}
	@Override
	public void	low() {
		
	}
	@Override
	public Future<?>	pulse(long duration) {
		return null;
	}
	@Override
	public Future<?>	pulse(long duration, boolean blocking) {
		return null;
	}
	@Override
	public Future<?>	pulse(long duration, boolean blocking, 
								Callable<Void> callback){
		return null;
	} 
	@Override
	public Future<?>	pulse(long duration, Callable<Void> callback) {
		return null;
	}
	@Override
	public Future<?>	pulse(long duration, PinState pulseState) {
		return null;
	}
	@Override
	public Future<?>	pulse(long duration, 
								PinState pulseState, 
								boolean blocking){
		return null;
	} 
	@Override
	public Future<?>	pulse(long duration, 
			PinState pulseState, boolean blocking, Callable<Void> callback) {
		return null;
	}
	@Override
	public Future<?>	pulse(long duration, 
			PinState pulseState, Callable<Void> callback) {
		return null;
	}
	@Override
	public void	setState(boolean state) {
		
	}
	@Override
	public void	setState(PinState state) {
		
	}
	@Override
	public void	toggle() {
		
	}
}
