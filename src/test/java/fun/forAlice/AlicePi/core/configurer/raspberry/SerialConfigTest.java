package fun.forAlice.AlicePi.core.configurer.raspberry;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import fun.forAlice.AlicePi.core.entity.UartDataPayload;
import fun.forAlice.AlicePi.core.service.impl.HttpHookServiceImpl;

public class SerialConfigTest {
	HttpHookServiceImpl httpService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		httpService  = new HttpHookServiceImpl();
		httpService.init();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUartPort() {
		String hexByteString = "01,03,04,00,DD,01,03,2B,98";
		String[] hexByteArr = hexByteString.split(",");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < hexByteArr.length; ++i) {
			buffer.append("0x");
			buffer.append(hexByteArr[i]);
			buffer.append(" ");
			if (i == hexByteArr.length - 1) {
				continue;
			}
		}

		UartDataPayload payload = new UartDataPayload();
		String uartHook = "http://localhost:9000/hooks/uarts/rx";
		ObjectMapper objMapper = new ObjectMapper();
		payload.setHexByteString(buffer.toString());
		try {
			httpService.postHook(uartHook, objMapper.writeValueAsString(payload));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
