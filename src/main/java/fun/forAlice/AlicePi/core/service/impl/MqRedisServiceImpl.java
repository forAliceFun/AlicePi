package fun.forAlice.AlicePi.core.service.impl;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;

import fun.forAlice.AlicePi.core.service.IMqService;
import redis.clients.jedis.JedisPubSub;

public class MqRedisServiceImpl extends JedisPubSub
		implements IMqService{
	private Logger logger = LoggerFactory.getLogger(MqRedisServiceImpl.class);

    private Consumer<String> consumer=(t)->{logger.info("default callback:{}");};
    private BiConsumer<String,String> pConsumer=(channel,msg)->{logger.info("default callback:{}");};
    
    public void setCallback(Consumer<String> consumer) {
    	this.consumer = consumer;
    }
    public void setPCallback(BiConsumer<String,String> pConsumer) {
    	this.pConsumer = pConsumer;
    }
    public void onMessage(String channel, String message) {
    	logger.info("onMessage, channnel: "+channel+", message: "+message);
    	this.consumer.accept(message);
    }

    public void onSubscribe(String channel, int subscribedChannels) {
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
    }

    public void onPSubscribe(String pattern, int subscribedChannels) {
    	
    }

    public void onPUnsubscribe(String pattern, int subscribedChannels) {
    }

    public void onPMessage(String pattern, String channel, String message) {
    	logger.info("onMessage, pattern: "+pattern
    				+", channnel: "+channel
    				+", message: "+message);
    	this.pConsumer.accept(channel,message);
    }
}
