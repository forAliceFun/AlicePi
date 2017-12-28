package fun.forAlice.AlicePi.core.pubsub.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fun.forAlice.AlicePi.core.pubsub.bean.Handle;


public class SubscriberRedisImplTest {

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
	public void testListen() {
		SubscriberRedisImpl sub = new SubscriberRedisImpl();
		sub.on("/hello", "world1", (t)->{
			System.out.println(t);
			System.out.println("-----world1--------");
		});
		sub.on("/hello", "world", (t)->{
			System.out.println(t);
			System.out.println("-------world2------");
		});
		sub.on("/hello", "world", (t)->{
			System.out.println(t);
			System.out.println("*****world2****");
		});
		sub.on("/topic", "world", (t)->{
			System.out.println(t);
			System.out.println("-------------");
		});
		sub.off("/hello", "world");
		
		while(true) {
			
		}
	}

}
