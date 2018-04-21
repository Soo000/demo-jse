package wava.lang.ref;

import java.lang.ref.WeakReference;

public class DemoWeakRef {

	public static void main(String[] args) {
		/*Map<String, String> map = new WeakHashMap<String, String>();
		
		String key = "companyName";
		String value = "kekexun.com";
		map.put(key, value);

		System.out.println(map.get(key));
		key = null;
		System.out.println(map);
		System.gc();
		System.out.println(map);*/
		
		String key = "wangke";
		WeakReference<String> weakReference = new WeakReference<String>(key);
		System.out.println(weakReference.get());
		key = null;
		System.gc();
		System.out.println(weakReference.get());
	}
	
}
