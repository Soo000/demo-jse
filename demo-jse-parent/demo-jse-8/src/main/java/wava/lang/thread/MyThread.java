package wava.lang.thread;

import java.util.Date;

public class MyThread implements Runnable {

	@Override
	public void run() {
		while(true) {
			System.out.println(new Date() + ", 线程 " + Thread.currentThread().getName() + " 正在执行...");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
