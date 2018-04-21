package wava.util.bitoper;

public class BitOper {

	public static void main(String[] args) {
		System.out.println(6 & 3);
		System.out.println(6 | 3);
		System.out.println(6 ^ 3);
		System.out.println(~6);
		
		try {
			Thread.sleep(1000 * 60 * 5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(3<<2);
	}
	
}
