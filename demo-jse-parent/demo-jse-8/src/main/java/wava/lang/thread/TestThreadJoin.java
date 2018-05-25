package wava.lang.thread;

public class TestThreadJoin {

	public static void main(String[] args) {
		
		System.out.println("-- Main 线程开始执行");
		
		Thread threadB = new Thread() {

			@Override
			public void run() {
				System.out.println("-- 线程 ThreadB 开始执行");
				try {
					for(int i = 0; i < 10; i++) {
						System.out.printf("- 线程ThreadB 正在执行 i=%d%n", i);
						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("-- 线程 ThreadB 执行完毕");
			}
			
		};
		
		threadB.start();
		
		try {
			// threadB 加入主线程
			System.out.println("- ThreadB 加入 Main 线程前");
			threadB.join();
			System.out.println("- ThreadB 加入 Main 线程后");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("-- Main 线程执行完毕");
	}
	
}
