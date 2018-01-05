package fun.forAlice.AlicePi.core.service.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class TimerServiceImpl {
	ScheduledExecutorService scheduledThreadPool;
	public TimerServiceImpl() {
		scheduledThreadPool = Executors.newScheduledThreadPool(32);

	}
	public class ThreadDemo implements Runnable{

	    public ThreadDemo(){
	        
	    }
	    @Override
	    public void run() {
	        System.out.println("简单执行一下!");
	    }
	}
	public void demo(Runnable x) {
		System.out.println("demo starts");
		ScheduledFuture future = scheduledThreadPool.scheduleAtFixedRate(()->{}, 0, 12, TimeUnit.SECONDS);
		future.cancel(true);
		scheduledThreadPool.submit(x, "ok");

	}
}
