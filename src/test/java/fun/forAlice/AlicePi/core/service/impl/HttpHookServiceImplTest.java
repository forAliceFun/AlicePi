package fun.forAlice.AlicePi.core.service.impl;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HttpHookServiceImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		HttpHookServiceImpl service = new HttpHookServiceImpl();
		try {
			service.init();
			System.out.println(
					service.postHook("http://localhost:9000/hooks/gpios/1/input",
							"{\r\n" + 
							"\"id\": 1,\r\n" + 
							"\"raspiPinNum\": 1,\r\n" + 
							"\"type\": \"output\",\r\n" + 
							"\"mode\": \"pull-up\",\r\n" + 
							"\"input\": 1,\r\n" + 
							"\"output\": 0,\r\n" + 
							"\"hook\": [],\r\n" + 
							"\"key\": \"gpio_1\"\r\n" + 
							"}")
					);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
