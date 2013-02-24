package ch.k42.auroraprime.executors;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Executor {
	
	private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
	
	public static ScheduledThreadPoolExecutor getInstance(){
		return executor;
	}
	
}
