package wava.lang.thread.daemon;


public class MyDaemonThread {

	public static void main(String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while(true) {
					System.out.println("Ke Wang");
				}
			}
		};
		
		thread.setDaemon(true);
		thread.start();
		
		int i = 0;
		while(i < 10) {
			i++;
			System.out.println("Main run i = " + i);
		}
	}

}
