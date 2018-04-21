package wava.lang.thread.priority;

public class Thread0 extends Thread {

	@Override
	public void run() {
		int i = 0;
		while(true) {
			System.out.println(Thread.currentThread().getName() + " 正在运行，输出 " + i);
			i++;
			
			if (i > 10) {
				break;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
