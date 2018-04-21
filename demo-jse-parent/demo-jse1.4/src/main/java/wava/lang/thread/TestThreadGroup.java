package wava.lang.thread;

import java.util.ArrayList;

public class TestThreadGroup {

	public static void main(String[] args) {
		ThreadGroup threadGroup0 = new ThreadGroup("ThreadGroup0");
		ThreadGroup threadGroup1 = new ThreadGroup("ThreadGroup1");
		
		System.out.println(threadGroup0);
		System.out.println(threadGroup1);
		
		Thread thread0 = new Thread(threadGroup0, "thread0");
		Thread thread1 = new Thread(threadGroup1, "thread1");
		
		System.out.println(thread0);
		System.out.println(thread1);
	}
	
	final ArrayList<String> list = new ArrayList<String>();
	
	Thread t0 = new Thread() {
		@Override
		public void run() {
			while(true) {
				synchronized(list) {
					list.add("a");
				}
			}
		}
	};
	
	Thread t1 = new Thread() {
		@Override
		public void run() {
			while(true) {
				synchronized(list) {
					list.add("b");
				}
			}
		}
	};

}
