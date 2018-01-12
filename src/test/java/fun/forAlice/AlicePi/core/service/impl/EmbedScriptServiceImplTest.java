package fun.forAlice.AlicePi.core.service.impl;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import fun.forAlice.AlicePi.core.DeviceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmbedScriptServiceImplTest {
    @Autowired
    EmbedScriptServiceImpl embedScriptService;
    
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
	public void testLoadScript() {
//		embedScriptService.loadScript();
	}
	
	@Test
	public void demo() {
		Runnable r = ()->System.out.println(123);
	}

}
