package arrays;

import java.util.Arrays;

public class TestMultiArraysSort {
	public static void main(String[] args) {
		int[][] datas = {
				{ 1, 251104, 98 },
				{ 2, 251104, 90 },
				{ 3, 251103, 70 },
				{ 4, 251105, 86 } };
		
		// 점수 (2번 인덱스) 를 기준으로 오름차순 (정방향)
		// .compare 는 (a,b) 에서 ([a가 더 크면 1] / [같으면 0] / [b가 더 크면 -1]) 반환
		Arrays.sort(datas, (arr1, arr2) -> Integer.compare(arr1[2], arr2[2]));
		for(int[] data: datas) {
			System.out.println(Arrays.toString(data));
		}
	}
}
