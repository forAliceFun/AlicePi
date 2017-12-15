package fun.forAlice.AlicePi.core.entity;

import com.pi4j.io.gpio.GpioPinDigital;
import com.pi4j.io.gpio.PinState;

public class GpioPinDigitalDemo extends GpioPinDemo
								implements GpioPinDigital{
	@Override
	public PinState	getState() {
		return null;
	}
	@Override
	public boolean	isHigh() {
		return (Boolean) null;
	}
	@Override
	public boolean	isLow() {
		return (Boolean) null;
	}
	@Override
	public boolean	isState(PinState state) {
		return (Boolean) null;
	}
}
