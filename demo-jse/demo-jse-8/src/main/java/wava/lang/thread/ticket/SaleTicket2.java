package wava.lang.thread.ticket;

public class SaleTicket2 extends Thread {

	private static int num = 100;
	private static Object obj = new Object();

	public SaleTicket2(String name) {
		super(name);
	}
	
	/**
	 * 并发线程对票数 num 不加锁所产生的问题，代码见 run() 方法
	 */
	@Override
	public void run() {
		while(true) {
			synchronized(obj) {
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
		}
	}

}
