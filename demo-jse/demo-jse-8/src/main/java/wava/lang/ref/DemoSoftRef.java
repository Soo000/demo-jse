package wava.lang.ref;

import java.lang.ref.SoftReference;

public class DemoSoftRef {

	public static void main(String[] args) {
		Object o = new Object();
		SoftReference<Object> softReference = new SoftReference<Object>(o);
		String str = "abcdefg";
		try {
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				str += str;
				str.intern();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		o = null;
		System.out.println(softReference.get() + "#########");
	}
	
}
