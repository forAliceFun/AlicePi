package fun.forAlice.AlicePi.core.pubsub.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.forAlice.AlicePi.core.service.impl.GpioServiceImpl;
import fun.forAlice.AlicePi.core.service.impl.MqRedisServiceImpl;
import fun.forAlice.AlicePi.core.pubsub.bean.Handle;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

@Service
public class SubscriberRedisImpl {
    Logger logger = LoggerFactory.getLogger(MqRedisServiceImpl.class);

	JedisPool jedisPool;
	
	@Autowired
	GpioServiceImpl gpioService;

	private Collection<Handle> handlePool;
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public Integer on(String event, String tag ,Consumer<String> consumer) {
		Handle handle = new Handle(event, tag, consumer);
		handlePool.add(handle);
		return handlePool.size();
	}
	public Integer off(String event, String tag ) {
		Handle handle = new Handle(event,tag);
		Iterator<Handle> it = handlePool.iterator() ;
		while(it.hasNext()){  
			Handle obj = it.next();
			if(obj.equals(handle)) {
				it.remove();
			}
		} 
		return handlePool.size();
	}
	
	void hook(String ch,String msg) {
		handlePool.stream()
					.filter(handle -> ch.equals(handle.getEvent()))
					.forEach(handle -> {
						handle.accept(msg);
					});
	}
	public SubscriberRedisImpl() {
		this.handlePool = new HashSet<Handle>();
		Properties props = new Properties();  
		String filePath = "application.properties";
		String host="127.0.0.1";
		Integer port = 6379;
		Integer timeout = 0;
		Integer maxIdle = 8;
		Integer maxWaitMillis = -1;
		//TODO
//		try {   
//			// InputStream ips = new BufferedInputStream(new FileInputStream(filePath));  
//			InputStream ips = Properties.class.getResourceAsStream(filePath);  
//			BufferedReader ipss = new BufferedReader(new InputStreamReader(ips));  
//			props.load(ipss);  
//			host = props.getProperty("spring.redis.host");  
//		} catch (FileNotFoundException e) {  
//			System.out.println("无法找到文件:"+filePath);  
//		} catch (IOException e) {  
//			System.out.println("读文件出错:"+filePath+"---"+e.getMessage());  
//		} 
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        this.jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
		Jedis jedis = this.jedisPool.getResource();

		executor.submit(() -> {
			MqRedisServiceImpl mq = new MqRedisServiceImpl();
			mq.setPCallback((ch,msg)->{
				this.hook(ch,msg);
				gpioService.load();
			});
			String threadName = Thread.currentThread().getName();
		    jedis.psubscribe(mq, "/*");
		    
		    System.out.println("Hello " + threadName);
		});
		System.out.println("Listener Run");	
		logger.info("Listener Run");
	}
	

}
