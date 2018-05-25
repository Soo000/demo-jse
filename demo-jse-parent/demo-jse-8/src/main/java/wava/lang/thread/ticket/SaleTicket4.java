package wava.lang.thread.ticket;

public class SaleTicket4 extends Thread {

	private static int num = 100;

	public SaleTicket4(String name) {
		super(name);
	}
	
	/**
	 * 并发线程对票数 num 不加锁所产生的问题，代码见 run() 方法
	 */
	@Override
	public void run() {
		int i = 0;
		while(true) {
			if (i % 2 == 0) {
				synchronized(SaleTicket4.class) {
					if (num > 0) {
						System.out.println("窗口 " + Thread.currentThread().getName() + " 售出了第 " + num-- + " 张票");
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("窗口 " + Thread.currentThread().getName() + " 票买完了");
						break;
					}
				}
			} else {
				sale();
			}
			
		}
	}

	public static synchronized void sale() {
		if (num > 0) {
			System.out.println("窗口 " + Thread.currentThread().getName() + " 售出了第 " + num-- + " 张票");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("窗口 " + Thread.currentThread().getName() + " 票买完了");
		}
	}
	
}
