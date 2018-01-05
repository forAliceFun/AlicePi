package fun.forAlice.AlicePi.core.pubsub.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fun.forAlice.AlicePi.core.service.impl.MqRedisServiceImpl;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class PublisherRedisImpl {

    Logger logger = LoggerFactory.getLogger(PublisherRedisImpl.class);

	JedisPool jedisPool;
	
	public PublisherRedisImpl() {
		String host="127.0.0.1";
		Integer port = 6379;
		Integer timeout = 0;
		Integer maxIdle = 8;
		Integer maxWaitMillis = -1;
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

//      JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        this.jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
		
	}
	
	public void publish(String channel, String msg) {
		Jedis jedis = this.jedisPool.getResource();
		jedis.publish(channel, msg);
	}
}
