package wava.util.sort;

public class Find {

	String a = "abc";
	StringBuffer b = new StringBuffer();
	static int[] numbers = {10, 33, 13, 98, 70, 47, 97, 68, 60};
	static int targetIndex = -1;
	
	/**
	 * 折半查找（二分法查找）
	 * @param numbers
	 * @param min
	 * @param max
	 * @param key
	 */
	public static void halfSearch(int[] numbers, int min, int max, int key) {
		int mid = (min + max) / 2;
		if (key == numbers[mid]) {
			targetIndex = mid;
			return;
		}
		
		if (key > numbers[mid]) {
			min = mid + 1;
		}
		if (key < numbers[mid]) {
			max = mid - 1;
		}
		
		if (min > max) {
			return;
		}
		
		halfSearch(numbers, min, max, key);
	}
	
	public static void main(String[] args) {
		int[] sortedNumbers = Sort.bubbleSort(numbers);
		for (int i = 0; i < sortedNumbers.length; i++) {
			System.out.print(sortedNumbers[i] + ",");
		}
		System.out.println("");
		
		halfSearch(sortedNumbers, 0, sortedNumbers.length - 1, 13);
		System.out.println(targetIndex);
	}
	
}
