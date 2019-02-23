package wava.util.concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Soosky
 *
 */
public class DemoThreadPoolExecutor {

	public static void main(String[] args) {
		// 获取 CPU 核心个数
		int cpus = Runtime.getRuntime().availableProcessors();
		System.out.println("cpus = " + cpus);
		
		// 定义线程池
		ThreadPoolExecutor threadPoolExecutor = 
				new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
		// 向线程池中添加任务
		for (int i = 0; i < 5; i++) {
			MyTask myTask = new MyTask("name-" + i);
			threadPoolExecutor.execute(myTask);
		}
		// 关闭线程池
		threadPoolExecutor.shutdown();
	}
	
	/**
	 * 
	 */
	static class MyTask implements Runnable {
		
		private String name;
		private Random r = new Random();
		
		public MyTask(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(r.nextInt(1000) + 2000);
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getId() + " sleep ");
			}
			
			System.out.println("任务 " + name + "完成");
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
}
