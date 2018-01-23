package fun.forAlice.AlicePi.core.configurer;

import java.io.IOException;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pi4j.io.gpio.exception.UnsupportedBoardType;
import com.pi4j.io.serial.*;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.util.Console;

import fun.forAlice.AlicePi.core.entity.Uart;

@Configuration
public class SerialConfig {
	
	Logger logger = LoggerFactory.getLogger(SerialConfig.class);

	@Bean
	public Uart generateUart() {
		return new Uart();
	}
	
	@Bean
	public Serial uartPort(Uart uart) {
		Serial serial = SerialFactory.createInstance();
        
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
		
		com.pi4j.io.serial.SerialConfig config = new com.pi4j.io.serial.SerialConfig();
        
        
        // set default serial settings (device, baud rate, flow control, etc)
        //
        // by default, use the DEFAULT com port on the Raspberry Pi (exposed on GPIO header)
        // NOTE: this utility method will determine the default serial port for the
        //       detected platform and board/model.  For all Raspberry Pi models
        //       except the 3B, it will return "/dev/ttyAMA0".  For Raspberry Pi
        //       model 3B may return "/dev/ttyS0" or "/dev/ttyAMA0" depending on
        //       environment configuration.
        
        try {
			config.device("/dev/ttyAMA0")
			      .baud(Baud._9600)
			      .dataBits(DataBits._8)
			      .parity(Parity.NONE)
			      .stopBits(StopBits._1)
			      .flowControl(FlowControl.NONE);
	        serial.open(config);
	        logger.info("serial open");

		} catch (UnsupportedBoardType | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serial;
	}


}
