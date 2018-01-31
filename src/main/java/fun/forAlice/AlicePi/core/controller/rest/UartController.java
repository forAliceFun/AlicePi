package fun.forAlice.AlicePi.core.controller.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.serial.Serial;

import fun.forAlice.AlicePi.core.entity.Uart;
import fun.forAlice.AlicePi.core.entity.UartDataPayload;

@RestController
@RequestMapping("/uart/")
public class UartController {
	Logger logger = LoggerFactory.getLogger(UartController.class);
	
	@Autowired
	Uart uart;

	@Autowired
	Serial serial;
	
	@RequestMapping("tx")
	/****
	 * 
	 * @param payload 	{ "hexByteString" :"", "asciiString":"","lastDatetime":""}
	 * @return
	 */
	public Uart tx(@RequestBody UartDataPayload payload) {
		Byte[] txBytes = payload.getBytes();
		for(Byte txByte:txBytes) {
			try {
				serial.write(txByte);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.debug("0x"+txByte+" ");
		}
		logger.info(payload.getHexByteString());
		return  uart;
	}

//	@Scheduled(fixedRate=4*1000)
	public void demo() {
		try {
			
			serial.write((byte) 01);
			serial.write((byte) 03);
			serial.write((byte) 00);
			serial.write((byte) 00);
			serial.write((byte) 00);
			serial.write((byte) 02);
			serial.write((byte) 0xC4);
			serial.write((byte) 0x0B);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
