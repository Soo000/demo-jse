package wava.util.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

	private LinkedBlockingQueue<Visitor> queue =  new LinkedBlockingQueue<Visitor>();
	
	private void testQueueToArray() throws InterruptedException {
		Visitor visitor0 = new Visitor("0", "visitor0", 0);
		queue.offer(visitor0);
		
		Visitor visitor1 = new Visitor("1", "visitor1", 10);
		queue.offer(visitor1);
		
		Visitor visitor2 = new Visitor("2", "visitor2", 20);
		queue.offer(visitor2);
		
		Visitor[] visitors = new Visitor[0];
		visitors = queue.toArray(visitors);
		
		for (int i = 0; i < visitors.length; i++) {
			System.out.println(visitors[i].getId() + " | " + visitors[i].getName() + " | " + visitors[i].getAge());
		}
	}
	
	public static void main(String[] args) {
		LinkedBlockingQueueTest linkedBlockingQueueTest = new LinkedBlockingQueueTest();
		try {
			linkedBlockingQueueTest.testQueueToArray();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	class Visitor {
		
		private String id;
		private String name;
		private int age;
		
		public Visitor(String id, String name, int age) {
			this.id = id;
			this.name = name;
			this.age = age;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
	}
	
}
