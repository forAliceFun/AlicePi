package fun.forAlice.AlicePi.core;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;


public class DemoTests {
	public void a(int var) {
		System.out.println(var);
	}

	public void p(int var,int varb ,BiConsumer <Integer,Integer> consumer) {
		consumer.accept(var,varb);
	}
	ScriptEngine engine;
	
	@Before
	public void setUp() throws Exception {
		ScriptEngineManager engineManager = new ScriptEngineManager();
        engine = engineManager.getEngineByName("nashorn");
	}
        

	@Test
	public void demo() throws ScriptException {
		Runnable r = ()->System.out.println(123);
		r.run();
		p(2,2,(t,q)->System.out.println(q*t*3));
		engine.eval("N = 3");
		String script = "var MyJavaClass = Java.type('fun.forAlice.AlicePi.core.DemoTests');\r\n" + 
				"\r\n" + 
				"var obj = new MyJavaClass();" +
				"obj.p(1,2,function (t,q){print(t*q*N*N)});";
		engine.eval(script);
	}
	
	@Test
	void pairCompare() {
		
	}
}
