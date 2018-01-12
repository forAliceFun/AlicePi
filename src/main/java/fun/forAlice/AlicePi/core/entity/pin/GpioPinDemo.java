package fun.forAlice.AlicePi.core.entity.pin;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinShutdown;
import com.pi4j.io.gpio.GpioProvider;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinListener;

public interface GpioPinDemo extends GpioPin {
	@Override
	default public void	addListener(GpioPinListener... listener) {
		
	}
	@Override
	default public void	addListener(List<? extends GpioPinListener> listeners) {
		
	}
	@Override
	default public void	clearProperties() {
		
	}
	@Override
	default public void		export(PinMode mode) {
		
	}
	@Override
	default public void	export(PinMode mode, PinState defaultState) {
		
	}
	@Override
	default public Collection<GpioPinListener>	getListeners() {
		return null;
	}
	@Override
	default public PinMode	getMode() {
		return null;
	}
	@Override
	default public String	getName() {
		return null;
	}
	@Override
	default public Pin	getPin() {
		return null;
	}
	@Override
	default public 	Map<String,String>	getProperties() {
		return null;
	}
	@Override
	default public 	String	getProperty(String key) {
		return null;
	}
	@Override
	default public 	String	getProperty(String key, String defaultValue) {
		return null;
	}
	@Override
	default public GpioProvider	getProvider() {
		return null;
	}
	@Override
	default public PinPullResistance	getPullResistance() {
		return null;
	}
	@Override
	default public GpioPinShutdown	getShutdownOptions() {
		return null;
	}
	@Override
	default public Object	getTag() {
		System.out.println("tag");
		return null;
	}
	@Override
	default public boolean	hasListener(GpioPinListener... listener) {
		return (Boolean) null;
	}
	@Override
	default public boolean	hasProperty(String key) {
		return (Boolean) null;
	}
	@Override
	default public boolean	isExported() {
		return (Boolean) null;
	}
	@Override
	default public boolean	isMode(PinMode mode) {
		return (Boolean) null;
	}
	@Override
	default public boolean	isPullResistance(PinPullResistance resistance) {
		return (Boolean) null;
	}
	@Override
	default public void	removeAllListeners() {
		
	}
	@Override
	default public void	removeListener(GpioPinListener... listener) {
		
	}
	@Override
	default public void	removeListener(List<? extends GpioPinListener> listeners) {
		
	}
	@Override
	default public void	removeProperty(String key) {
		
	}
	@Override
	default public void	setMode(PinMode mode) {
		
	}
	@Override
	default public void	setName(String name) {
		
	}
	@Override
	default public void	setProperty(String key, String value) {
		
	}
	@Override
	default public void	setPullResistance(PinPullResistance resistance) {
		
	}
	@Override
	default public void	setShutdownOptions(Boolean unexport) {
		
	}
	@Override
	default public void	setShutdownOptions(Boolean unexport, PinState state) {
		
	}
	@Override
	default public void	setShutdownOptions(Boolean unexport, 
									PinState state, 
									PinPullResistance resistance) {
		
	}
	@Override
	default public void	setShutdownOptions(Boolean unexport, 
									PinState state, 
									PinPullResistance resistance, 
									PinMode mode) {
		
	}
	@Override
	default public void	setShutdownOptions(GpioPinShutdown options) {
		
	}
	@Override
	default public void	setTag(Object tag) {
		
	}
	@Override
	default public void	unexport() {
		
	}
}
