package wava.lang.thread.ticket;

public class SaleTicket implements Runnable {

	private static int num = 10;

	/**
	 * 并发线程对票数 num 不加锁所产生的问题，代码见 run() 方法
	 */
	@Override
	public void run() {
		while(true) {
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
