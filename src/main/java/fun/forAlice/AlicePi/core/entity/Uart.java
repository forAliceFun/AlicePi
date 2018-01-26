package fun.forAlice.AlicePi.core.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pi4j.io.serial.SerialDataEvent;

public class Uart {
	private Logger logger = LoggerFactory.getLogger(Uart.class);
	
	private Consumer<SerialDataEvent> callback = (event)->{
		try {
			logger.info("[HEX DATA]   " + event.getHexByteString());
			logger.info("[ASCII DATA] " + event.getAsciiString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	public Consumer<SerialDataEvent> getCallback() {
		return callback;
	}

	public void setCallback(Consumer<SerialDataEvent> callback) {
		this.callback = callback;
	}
	
	public DataPayload rx = new DataPayload();
	public DataPayload tx = new DataPayload();
	public String status;
	public String event;
	public Set<String> hook;
	
	@JsonIgnore
	public Byte[] getTxBytes() {
		List<Byte> bytes = new ArrayList<Byte>();
		String hexString = this.tx.hexByteString;
		String[] hexBytes = hexString.split(" ");
		for(String hexByte:hexBytes) {
			hexByte = hexByte.replace("0x", "");
			bytes.add(  Byte.parseByte(hexByte, 16) );
		}
		return bytes.toArray(new Byte[bytes.size()]);
	}
	
	public class DataPayload{
		public String hexByteString;
		public String asciiString;
		public String lastDatetime;
	}
	
	public class Config{
		
	}
	
}
