package wava.nio.buffer;

import java.nio.IntBuffer;

/**
 * 缓冲区操作
 * @author Soosky
 *
 */
public class DemoBuffer {

	public static void main(String[] args) {
		// 创建指定长度的缓冲区
		IntBuffer buff = IntBuffer.allocate(10);
		System.out.println(buff);
		
		// 使用数组来创建一个缓冲区试图
		int[] array = new int[] {3, 5, 1};
		buff = IntBuffer.wrap(array);
		
		// 利用数组的某一个区间来创建视图
		//buff = buff.wrap(array, 0, 2);
		
		// 对缓冲区某个位置上面的元素进行修改
		buff.put(0, 7);
		
		// 遍历缓冲区中数据
		for (int i = 0; i < buff.limit(); i++) {
			System.out.print(buff.get() + "\t ");
		}
		System.out.println(buff);
		
		// 遍历数组中元素，表明缓冲区中的修改会影响原数组
		for (int a: array) {
			System.out.print(a + "\t");
		}
		
		System.out.println("调用 flip");
		buff.flip(); // 对缓冲区进行反转
		System.out.println("调用 flip 后 " + buff);
		
		System.out.println("调用 clear");
		buff.clear();
		System.out.println("调用 clear 后 " + buff);
		
		// 赋值一个新的缓冲区
		IntBuffer buff2 = buff.duplicate();
		System.out.println(buff2);
	}
	
}
