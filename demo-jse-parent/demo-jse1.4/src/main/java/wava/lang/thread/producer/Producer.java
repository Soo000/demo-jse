package wava.lang.thread.producer;

public class Producer implements Runnable {
	
	private static Apple apple;
	
	public Producer(Apple apple) {
		Producer.apple = apple;
	}

	@Override
	public void run() {
		while (true) {
			synchronized(apple) {
				if (!apple.isStatus()) {
					System.out.println("生成了一个苹果");
					apple.setStatus(true);
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
