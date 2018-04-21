package wava.lang.thread.ticket;

public class SaleTicketManager {
	
	private static void sale() {
		SaleTicket saleTicket = new SaleTicket();
		
		Thread saleWindow0 = new Thread(saleTicket, "售票窗口0");
		Thread saleWindow1 = new Thread(saleTicket, "售票窗口1");
		Thread saleWindow2 = new Thread(saleTicket, "售票窗口2");
		Thread saleWindow3 = new Thread(saleTicket, "售票窗口3");
		
		saleWindow0.start();
		saleWindow1.start();
		saleWindow2.start();
		saleWindow3.start();
	}
	
	private static void sale1() {
		SaleTicket1 saleTicket = new SaleTicket1();
		
		Thread saleWindow0 = new Thread(saleTicket, "售票窗口0");
		Thread saleWindow1 = new Thread(saleTicket, "售票窗口1");
		Thread saleWindow2 = new Thread(saleTicket, "售票窗口2");
		Thread saleWindow3 = new Thread(saleTicket, "售票窗口3");
		
		saleWindow0.start();
		saleWindow1.start();
		saleWindow2.start();
		saleWindow3.start();
	}
	
	private static void sale2() {
		SaleTicket2 st0 = new SaleTicket2("售票窗口0");
		SaleTicket2 st1 = new SaleTicket2("售票窗口1");
		SaleTicket2 st2 = new SaleTicket2("售票窗口2");
		SaleTicket2 st3 = new SaleTicket2("售票窗口3");
		
		st0.start();
		st1.start();
		st2.start();
		st3.start();
	}

	/**
	 * 并发线程对共享资源（火车票）不加锁所产生的问题实例代码，见main()方法
	 * @param args
	 */
	public static void main(String[] args) {
		sale2();
	}
	
}
