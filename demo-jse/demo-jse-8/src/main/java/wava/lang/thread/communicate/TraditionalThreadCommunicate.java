package wava.lang.thread.communicate;

/**
 * 传统线程通信
 * 子线程先循环10次，然后主线程再循环100次，如此循环50次
 * @author Ke.Wang
 * @date 2018年5月25日 上午9:51:22
 */
public class TraditionalThreadCommunicate {

	private static Business business = new Business();
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}
			}
		}).start();
		
		
		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}
	}
	
}

class Business {
	
	private boolean shoudSub = true;
	
	public synchronized void sub(int loop) {
		while (!shoudSub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int j = 1; j <= 10; j++) {
			System.out.println("Sub thread sequece of " + loop + " loof of " + j );
		}
		shoudSub = false;
		notify();
	}
	
	public synchronized void main(int loop) {
		while (shoudSub) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int j = 1; j <= 100; j++) {
			System.out.println("Main thread sequece of " + loop + " loop of " + j);
		}
		shoudSub = true;
		notify();
	}
}
