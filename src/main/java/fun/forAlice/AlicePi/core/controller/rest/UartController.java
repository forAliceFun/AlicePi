package fun.forAlice.AlicePi.core.controller.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.serial.Serial;

import fun.forAlice.AlicePi.core.entity.Uart;

@RestController
public class UartController {
	
	@Autowired
	Uart uart;

	@Autowired
	Serial serial;

	@Scheduled(fixedRate=4*1000)
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
