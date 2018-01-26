package fun.forAlice.AlicePi.core.configurer.demo;

import java.io.IOException;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.pi4j.io.gpio.exception.UnsupportedBoardType;
import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;
import com.pi4j.io.serial.StopBits;

import fun.forAlice.AlicePi.core.condition.WindowsCondition;
import fun.forAlice.AlicePi.core.entity.Uart;
import fun.forAlice.AlicePi.core.entity.serial.SerialDemo;

@Configuration
public class DemoSerialConfig {
    Logger logger = LoggerFactory.getLogger("Demo Environment SerialConig");

    @Bean
	@Conditional(WindowsCondition.class)
	public Uart generateUart() {
		return new Uart();
	}
	
    @Bean
	@Conditional(WindowsCondition.class)
	public Serial uartPort(Uart uart) {
		Serial serial = new SerialDemo();
        
		serial.addListener(new SerialDataEventListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {

                // NOTE! - It is extremely important to read the data received from the
                // serial port.  If it does not get read from the receive buffer, the
                // buffer will continue to grow and consume memory.

                Consumer<SerialDataEvent> callback = uart.getCallback();
				callback.accept(event);
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
