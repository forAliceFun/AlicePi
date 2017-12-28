package fun.forAlice.AlicePi.core.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Condition;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class EmbedScriptServiceImpl {
    Logger logger = LoggerFactory.getLogger(EmbedScriptServiceImpl.class);


    @Autowired
    JedisPool jedisPool;
    
    private ScriptEngine engine;
    @PostConstruct
    public void init() {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        engine = engineManager.getEngineByName("nashorn");
        
    	String initScriptFile = "embedded-scripts/init.js";
    	Resource resource = new ClassPathResource(initScriptFile);
    	try {
	    	BufferedReader br = new BufferedReader(
					new InputStreamReader(
							resource.getInputStream()
						)
				);
	    	String line = br.readLine();
	    	StringBuffer scriptBuffer = new StringBuffer();
	    	while(line != null) {
	    		scriptBuffer.append(line).append("\n");
	    		line = br.readLine();
	    	}
	    	engine.eval(scriptBuffer.toString());
	    	
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("初始化脚本载入错误："+resource.getDescription());
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("初始化脚本执行错误："+resource.getDescription());
		}
   

    }
    
    Map<String,String> scripts;
    
	public int loadScript() {
		Jedis jedis = jedisPool.getResource();
		Set<String> keys = jedis.keys("scripts/*.config.js");
		logger.info("*.config.js: "+keys.toString());

        try {
			engine.eval("print(\"a\", \"foo\");");
		} catch (ScriptException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        keys.forEach((key)->{
        	String script = jedis.get(key);
        	try {
				engine.eval(script);
				logger.info("执行config.js: "+script);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
		return keys.size();
	}
	
	
}
