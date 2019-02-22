package wava.lang.thread.threadlocal;

import java.util.Random;

/**
 * 线程范围内的共享对象
 * @author Ke.Wang
 * @date 2018年5月25日 上午10:55:20
 */
public class ThreadScopeShareData {

	/**
	 * 一个ThreadLocal代表一个变量，故其中只能放一个数据；如果有N多对象需要在线程内共享，可以把N的对象封装放在ThreadLocal中
	 */
	static ThreadLocal<Integer> x = new ThreadLocal<Integer>();
	
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName() + " has put data: " + data);
					
					x.set(data); // 数据存储在当前线程中
					
					new A().get();
					new B().get();
				}
				
			}).start();
		}
	}
	
	static class A {
		public void get() {
			int data = x.get();
			System.out.println("A from " + Thread.currentThread().getName() + " get data: " + data);
		}
	}
	
	static class B {
		public void get() {
			int data = x.get();
			System.out.println("B from " + Thread.currentThread().getName() + " get data: " + data);
		}
	}
}
