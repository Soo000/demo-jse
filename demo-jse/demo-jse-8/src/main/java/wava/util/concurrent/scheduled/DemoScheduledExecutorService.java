package wava.util.concurrent.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DemoScheduledExecutorService {

	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		
		long initialDelay1 = 1;
		long period1 = 1;
		// 从现在开始1秒钟之后，每隔1秒钟执行一次job1
		service.scheduleAtFixedRate(new DemoScheduledExecutorServiceTask("job1"), initialDelay1, period1, TimeUnit.SECONDS);
		
		long initialDelay2 = 2;
		long period2 = 2;
		// 从现在开始2秒钟之后，每隔2秒钟执行一次job2
		service.scheduleWithFixedDelay(new DemoScheduledExecutorServiceTask("job2"), initialDelay2, period2, TimeUnit.SECONDS);
		
		ScheduledExecutorService singleThreadScheduledPool = Executors.newSingleThreadScheduledExecutor();
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
	}
	
}
