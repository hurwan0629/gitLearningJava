package arrays;

import java.util.Arrays;

public class Test01 {
	public static void main(String[] args) {
		int[] arr = {1,63,21,34,51,23,12};
		System.out.println(arr.length);
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("있는 값 탐색시(인덱스 출력): " + Arrays.binarySearch(arr, 21));
		System.out.println("없는 값 탐색시(-1 출력): " + Arrays.binarySearch(arr, 0));
		
		int[] arrCopy = Arrays.copyOf(arr, arr.length);
		arr[0] = 10; // arr[0]값 변경하지만 arrCopy는 변화 없음
		System.out.println("arr: " + Arrays.toString(arr));
		System.out.println("copy: " + Arrays.toString(arrCopy));
		
		int[] arrCopyRange5 = Arrays.copyOfRange(arr, 2, 5);
		System.out.println("[a]~[b-1] 범위 지정 복사 : " 
						+ Arrays.toString(arrCopyRange5) );
		
		int[] arr3 = new int[5];
		Arrays.fill(arr3, 1);
		System.out.println("filled arr: " + Arrays.toString(arr3));
	}
}
