package wava.annotation;

/**
 * 
 * @author soosk
 * SafeVarargs ��ȫ�ɱ䳤�Ȳ���
 */
public class SafeVarargsTest {

	/**
	 * 
	 * @param name
	 * @param a ���� a Ϊһ������
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
