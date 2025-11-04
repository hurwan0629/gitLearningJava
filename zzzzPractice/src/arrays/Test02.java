package arrays;

import java.util.Arrays;

public class Test02 {
	public static void main(String[] args) {
		int[][] arr1 = {
				{ 1, 2 },
				{ 3, 4}};
		// 동일한 내용의 2차원 배열
		int[][] arr2 = {
				{ 1, 2 },
				{ 3, 4}};
		
		// .equals에서는 false가 나옴
		System.out.println(".equals(): " + Arrays.equals(arr1, arr2));
		
		// .deepEquals에서는 다차원 배열을 비교함
		System.out.println(".deepEquals(): " + Arrays.deepEquals(arr1, arr2));
		
		System.out.println(".toString(): " + Arrays.toString(arr1));
		System.out.println(".deepToString(): " + Arrays.deepToString(arr1));
	}
}
