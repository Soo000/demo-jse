package wava.lang.thread.producer;

public class Test {

	public static void main(String[] arg) {
		
		Apple apple = new Apple();
		
		Producer producer = new Producer(apple);
		Consumer consumer = new Consumer(apple);
		
		Thread producerThread = new Thread(producer, "生成线程");
		Thread consumerThread = new Thread(consumer, "消费线程");
		
		producerThread.start();
		consumerThread.start();
	}
}
