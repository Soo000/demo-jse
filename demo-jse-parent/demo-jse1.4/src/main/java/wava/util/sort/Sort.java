package wava.util.sort;

public class Sort {

	private static int[] unSort = {34, 19, 11, 109, 3, 56};
	
	public static void main(String[] args) {
		// 选择排序
		//int[] sorted = selectionSort(unSort);
		// 冒泡排序
		int[] sorted = bubbleSort(unSort);
		for (int i = 0; i < sorted.length; i++) {
			System.out.print(sorted[i] + ",");
		}
		System.out.print("");
	}
	
	/**
	 * 选择排序
	 * @return
	 */
	public static int[] selectionSort(int[] srcUnSort) {
		for (int i = 0; i < srcUnSort.length - 1; i++) {
			for (int j = i + 1; j < srcUnSort.length; j++) {
				int tmp = srcUnSort[i];
				if (srcUnSort[i] > srcUnSort[j]) {
					srcUnSort[i] = srcUnSort[j];
					srcUnSort[j] = tmp;
				}
			}
		}
		return srcUnSort;
	}
	
	/**
	 * 冒泡排序
	 * @param srcUnSort
	 * @return
	 */
	public static int[] bubbleSort(int[] srcUnSort) {
		for (int i = 0; i < srcUnSort.length - 1; i++) {
			for (int j = 0; j < srcUnSort.length - i - 1; j++) {
				int tmp = srcUnSort[j];
				if (srcUnSort[j] > srcUnSort[j + 1]) {
					srcUnSort[j] = srcUnSort[j + 1];
					srcUnSort[j + 1] = tmp;
				}
			}
		}
		return srcUnSort;
	}
	
}
