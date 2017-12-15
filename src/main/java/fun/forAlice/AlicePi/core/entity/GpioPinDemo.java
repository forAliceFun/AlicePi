package fun.forAlice.AlicePi.core.entity;

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

public class GpioPinDemo implements GpioPin {
	@Override
	public void	addListener(GpioPinListener... listener) {
		
	}
	@Override
	public void	addListener(List<? extends GpioPinListener> listeners) {
		
	}
	@Override
	public void	clearProperties() {
		
	}
	@Override
	public void		export(PinMode mode) {
		
	}
	@Override
	public void	export(PinMode mode, PinState defaultState) {
		
	}
	@Override
	public Collection<GpioPinListener>	getListeners() {
		return null;
	}
	@Override
	public PinMode	getMode() {
		return null;
	}
	@Override
	public String	getName() {
		return null;
	}
	@Override
	public Pin	getPin() {
		return null;
	}
	@Override
	public 	Map<String,String>	getProperties() {
		return null;
	}
	@Override
	public 	String	getProperty(String key) {
		return null;
	}
	@Override
	public 	String	getProperty(String key, String defaultValue) {
		return null;
	}
	@Override
	public GpioProvider	getProvider() {
		return null;
	}
	@Override
	public PinPullResistance	getPullResistance() {
		return null;
	}
	@Override
	public GpioPinShutdown	getShutdownOptions() {
		return null;
	}
	@Override
	public Object	getTag() {
		System.out.println("tag");
		return null;
	}
	@Override
	public boolean	hasListener(GpioPinListener... listener) {
		return (Boolean) null;
	}
	@Override
	public boolean	hasProperty(String key) {
		return (Boolean) null;
	}
	@Override
	public boolean	isExported() {
		return (Boolean) null;
	}
	@Override
	public boolean	isMode(PinMode mode) {
		return (Boolean) null;
	}
	@Override
	public boolean	isPullResistance(PinPullResistance resistance) {
		return (Boolean) null;
	}
	@Override
	public void	removeAllListeners() {
		
	}
	@Override
	public void	removeListener(GpioPinListener... listener) {
		
	}
	@Override
	public void	removeListener(List<? extends GpioPinListener> listeners) {
		
	}
	@Override
	public void	removeProperty(String key) {
		
	}
	@Override
	public void	setMode(PinMode mode) {
		
	}
	@Override
	public void	setName(String name) {
		
	}
	@Override
	public void	setProperty(String key, String value) {
		
	}
	@Override
	public void	setPullResistance(PinPullResistance resistance) {
		
	}
	@Override
	public void	setShutdownOptions(Boolean unexport) {
		
	}
	@Override
	public void	setShutdownOptions(Boolean unexport, PinState state) {
		
	}
	@Override
	public void	setShutdownOptions(Boolean unexport, 
									PinState state, 
									PinPullResistance resistance) {
		
	}
	@Override
	public void	setShutdownOptions(Boolean unexport, 
									PinState state, 
									PinPullResistance resistance, 
									PinMode mode) {
		
	}
	@Override
	public void	setShutdownOptions(GpioPinShutdown options) {
		
	}
	@Override
	public void	setTag(Object tag) {
		
	}
	@Override
	public void	unexport() {
		
	}
}
