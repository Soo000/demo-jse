package wava.util;

import java.util.Random;

public class TestRandom {
	
	private static int genRandomInt() {
		int min = 0000; 
		int max = 9999;
		Random random = new Random();
		int temp = random.nextInt(max) % (max - min + 1) + min;
		return temp;
	}

	public static void main(String[] args) {
		long result = 13909100000l;
		for (int i = 0; i < 100; i++) {
			int temp = genRandomInt();
			System.out.println(temp);
			long phoneNum = result + temp;
			System.out.println("phoneNum=" + phoneNum);
		}
	}
	
}
