package wava.lang.thread.producer;

public class Consumer implements Runnable {

	private static Apple apple;
	
	public Consumer(Apple apple) {
		Consumer.apple = apple;
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(apple) {
				if (apple.isStatus()) {
					System.out.println("消费一个苹果");
					apple.setStatus(false);
					apple.notify();
				}
				
				try {
					apple.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
