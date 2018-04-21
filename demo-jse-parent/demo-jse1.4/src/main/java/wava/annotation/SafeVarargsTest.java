package wava.annotation;

/**
 * 
 * @author soosk
 * SafeVarargs 安全可变长度参数
 */
public class SafeVarargsTest {

	/**
	 * 
	 * @param name
	 * @param a 参数 a 为一个属组
	 */
	@SafeVarargs
	public static <E> void test(String name, E... a) {
		for (E i : a) {
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		test("wangke", 10, 20);
		test("wangke", "aaa", "bbb");
		test("wangke", true, false);
	}
}
