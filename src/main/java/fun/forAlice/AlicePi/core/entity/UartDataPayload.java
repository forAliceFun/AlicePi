package fun.forAlice.AlicePi.core.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UartDataPayload {
	String hexByteString;
	String asciiString;
	String lastDatetime;
	
	@JsonIgnore
	public Byte[] getBytes() {
		if(hexByteString == null) {
			this.syncData();
		}
		List<Byte> bytes = new ArrayList<Byte>();
		String[] hexBytes = hexByteString.split(" ");
		for(String hexByte:hexBytes) {
			hexByte = hexByte.replace("0x", "");
			bytes.add(  Byte.parseByte(hexByte, 16) );
		}
		return bytes.toArray(new Byte[bytes.size()]);
	}
	
	private int syncData() {
		if(hexByteString == null 
				&& asciiString==null) {
			return -1;
		}
		if(hexByteString==null) {
			StringBuffer buffer = new StringBuffer();
			for(int index=0;index<asciiString.length();++index) {
				int code = asciiString.codePointAt(index);
				buffer.append("0x");
				buffer.append(Integer.toUnsignedString(code, 16));
				if(index==asciiString.length()-1) {
					continue;
				}
				buffer.append(" ");
			}
			hexByteString = buffer.toString();// as "0x31 0x32"
			return asciiString.length();
		}
		if(asciiString ==null) {
			String[] hexBytes = hexByteString.split(" ");
			StringBuffer buffer = new StringBuffer();
			for(String hexByte : hexBytes) {
				String hex = hexByte.replace("0x", "");
				int code = Integer.parseInt(hex, 16);
				buffer.append((char)code);
			}
			asciiString = buffer.toString();
			return hexBytes.length;
		}
		return -1;
	}
	
	public String getHexByteString() {
		return hexByteString;
	}
	public void setHexByteString(String hexByteString) {
		this.hexByteString = hexByteString;
	}
	public String getAsciiString() {
		return asciiString;
	}
	public void setAsciiString(String asciiString) {
		this.asciiString = asciiString;
	}
	public String getLastDatetime() {
		return lastDatetime;
	}
	public void setLastDatetime(String lastDatetime) {
		this.lastDatetime = lastDatetime;
	}

}
