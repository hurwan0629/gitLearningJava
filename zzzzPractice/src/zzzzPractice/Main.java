package zzzzPractice;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList datas = new ArrayList();
		
		datas.add(1);
		datas.add(2);
		
		for(Object data:datas) {
			System.out.println((Integer)data);
		}
	}
}
