package fun.forAlice.AlicePi.core.configurer.raspberry;

import java.io.IOException;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4j.io.gpio.exception.UnsupportedBoardType;
import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.StopBits;

import fun.forAlice.AlicePi.core.condition.LinuxCondition;
import fun.forAlice.AlicePi.core.condition.WindowsCondition;
import fun.forAlice.AlicePi.core.entity.Uart;
import fun.forAlice.AlicePi.core.entity.UartDataPayload;
import fun.forAlice.AlicePi.core.service.impl.HttpHookServiceImpl;

@Configuration
public class SerialConfig {
	
	Logger logger = LoggerFactory.getLogger("RaspberryPi Environment SerialConig");
	
	@Autowired
	HttpHookServiceImpl httpService;
	
	@Autowired
	ObjectMapper objMapper;

	@Bean
	@Conditional(LinuxCondition.class)
	public Uart generateUart() {
		return new Uart();
	}
	
	@Value("${uart-hook}")
	String uartHook;
	
	@Bean
	@Conditional(LinuxCondition.class)
	public Serial uartPort(Uart uart) {
		Serial serial = SerialFactory.createInstance();
        
		serial.addListener(new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {

                // NOTE! - It is extremely important to read the data received from the
                // serial port.  If it does not get read from the receive buffer, the
                // buffer will continue to grow and consume memory.

//            	[HEX DATA]   7F,7E,00
//            	[ASCII DATA]~
                Consumer<SerialDataEvent> callback = uart.getCallback();
				callback.accept(event);
				UartDataPayload payload = new UartDataPayload();
				
				StringBuffer buffer  = new StringBuffer();
				String hexByteString="";
				try {
					hexByteString = event.getHexByteString();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String[] hexByteArr = hexByteString.split(",");
				for(int i=0;i<hexByteArr.length;++i) {
					buffer.append("0x");
					buffer.append(hexByteArr[i]);
					buffer.append(" ");
					if(i==hexByteArr.length-1) {
						continue;
					}
				}


				payload.setHexByteString(buffer.toString());
				try {
					httpService.postHook(uartHook, objMapper.writeValueAsString(payload));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.error("listen");
            }
        });
		      
        
        // set default serial settings (device, baud rate, flow control, etc)
        //
        // by default, use the DEFAULT com port on the Raspberry Pi (exposed on GPIO header)
        // NOTE: this utility method will determine the default serial port for the
        //       detected platform and board/model.  For all Raspberry Pi models
        //       except the 3B, it will return "/dev/ttyAMA0".  For Raspberry Pi
        //       model 3B may return "/dev/ttyS0" or "/dev/ttyAMA0" depending on
        //       environment configuration.
        
        try {

	        serial.open("/dev/ttyAMA0", Baud._9600, DataBits._8, Parity.NONE,StopBits._1,FlowControl.NONE);
	        logger.info("serial open");

		} catch (UnsupportedBoardType | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serial;
	}
}
