package wava.lang.thread;

public class TestThreadInterrupt {

	public static void main(String[] args) {
		
		Thread thread = new Thread() {
			int i = 0;
			@Override
			public void run() {
				try {
					while(true) {
						System.out.println("i=" + i++);
						sleep(1000 * 3);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("-- 通过 interrupt 方法醒过来。");
			}
		};
		
		thread.start();
		
		//thread.interrupt();
	}

}
