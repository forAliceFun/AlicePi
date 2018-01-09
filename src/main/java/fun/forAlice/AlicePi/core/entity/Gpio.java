package fun.forAlice.AlicePi.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalOutput;


public class Gpio implements Serializable{

		private Integer id;
		
		private Integer raspiPinNum;
		
		private String type="output";
		
		private String mode="pull-down";
		
		private Integer input=0;
		
		private Integer output=1;
		
		private List<String> hook;
		
		private String key;
		

		public Gpio() {
			hook = new ArrayList<>();
		}
	
		public Gpio(Integer raspiPinNum){
			this.id = raspiPinNum;
			this.raspiPinNum = raspiPinNum;
			this.key = "gpio_"+raspiPinNum;
			this.hook = new ArrayList<>();
		}
		
		public static String generateKey(Integer raspiPinNum) {
			return "gpio_"+raspiPinNum;
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

		public List<String> getHook() {
			return hook;
		}

		public void setHook(List<String> hook) {
			this.hook = hook;
		}

}
