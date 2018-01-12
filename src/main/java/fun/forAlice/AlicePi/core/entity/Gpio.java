package fun.forAlice.AlicePi.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;

public class Gpio implements Serializable {

	private Integer id;

	private Integer raspiPinNum;

	private String type = "output";

	private String mode = "pull-down";

	private Integer input = 0;

	private Integer output = 1;

	private Set<String> hook;

	private String key;

	@JsonIgnore
	private Map<String, GpioCallbackTrigger> hookCallback=new HashMap<>();

	public void addHookCallback(String hook ,GpioCallbackTrigger callback) {
		hookCallback.put(hook, callback);
	}
	public void removeHookCallback(String hook) {
		hookCallback.remove(hook);
	}
	public Gpio() {
		hook = new HashSet<>();
	}

	public Gpio(Integer raspiPinNum) {
		this.id = raspiPinNum;
		this.raspiPinNum = raspiPinNum;
		this.key = "gpio_" + raspiPinNum;
		this.hook = new HashSet<>();
	}

	public static String generateKey(Integer raspiPinNum) {
		return "gpio_" + raspiPinNum;
	}

	public Integer insertHook(String url) {
		this.hook.add(url);
		return this.hook.size();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getraspiPinNum() {
		return raspiPinNum;
	}

	public void setraspiPinNum(Integer raspiPinNum) {
		this.raspiPinNum = raspiPinNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Integer getInput() {
		return input;
	}

	public void setInput(Integer input) {
		this.input = input;
	}

	public Integer getOutput() {
		return output;
	}

	public void setOutput(Integer output) {
		this.output = output;
	}

	public Set<String> getHook() {
		return hook;
	}

	public void setHook(Set<String> hook) {
		this.hook = hook;
	}

	public Map<String, GpioCallbackTrigger> getHookCallback() {
		return hookCallback;
	}

	public void setHookCallback(Map<String, GpioCallbackTrigger> hookCallback) {
		this.hookCallback = hookCallback;
	}
}
