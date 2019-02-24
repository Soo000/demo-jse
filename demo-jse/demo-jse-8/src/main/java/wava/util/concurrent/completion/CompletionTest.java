package wava.util.concurrent.completion;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 动脑学院-Mark老师 创建日期：2017/12/10 创建时间: 21:57
 */
public class CompletionTest {
	private final int POOL_SIZE = 5;
	private final int TOTAL_TASK = 10;

	// 方法一，自己写集合来实现获取线程池中任务的返回结果
	public void testByQueue() throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
		BlockingQueue<Future<String>> queue = new LinkedBlockingDeque<>();
		// 向里面扔任务
		for (int i = 0; i < TOTAL_TASK; i++) {
			Future<String> future = pool.submit(new WorkTask("ExecTask" + i));
			queue.add(future);
		}

		// 检查线程池任务执行结果
		for (int i = 0; i < TOTAL_TASK; i++) {
			System.out.println("ExecTask:" + queue.take().get());
		}

		pool.shutdown();

	}

	// 方法二，通过CompletionService来实现获取线程池中任务的返回结果
	public void testByCompletion() throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
		CompletionService<String> service = new ExecutorCompletionService<String>(pool);

		// 向里面扔任务
		for (int i = 0; i < TOTAL_TASK; i++) {
			service.submit(new WorkTask("ExecTask" + i));
		}

		// 检查线程池任务执行结果
		for (int i = 0; i < TOTAL_TASK; i++) {
			Future<String> future = service.take();
			System.out.println("CompletionService:" + future.get());
		}
		
		/*Future<String> future = null;
		do {
			future = service.take();
			System.out.println("CompletionService:" + future.get());
		} while (future != null);*/

	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletionTest completionTest = new CompletionTest();
		// completionTest.testByQueue();
		completionTest.testByCompletion();
	}

}
