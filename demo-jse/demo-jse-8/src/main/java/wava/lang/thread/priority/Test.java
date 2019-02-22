package wava.lang.thread.priority;

public class Test {

	public static void main(String[] args) {
		Thread0 thread0 = new Thread0();
		thread0.setPriority(1);
		
		Thread0 thread1 = new Thread0();
		thread1.setPriority(10);
		
		thread0.start();
		thread1.start();
	}

}
