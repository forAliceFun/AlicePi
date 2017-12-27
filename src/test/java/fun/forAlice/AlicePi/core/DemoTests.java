package fun.forAlice.AlicePi.core;

import java.util.function.Consumer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


public class DemoTests {
	public void a(int var) {
		System.out.println(var);
	}
	public void p(int var,Consumer<Integer> consumer) {
		consumer.accept(var);
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
		p(2,(t)->System.out.println(t*3));
		engine.eval("N = 3");
		String script = "var MyJavaClass = Java.type('fun.forAlice.AlicePi.core.DemoTests');\r\n" + 
				"\r\n" + 
				"var obj = new MyJavaClass();" +
				"obj.p(1,function (t){print(t*N*N)});";
		engine.eval(script);
	}
}
