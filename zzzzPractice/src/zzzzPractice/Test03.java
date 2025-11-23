package zzzzPractice;

public class Test03 {
	public static void main(String[] args) {
		int a=0;
		int b=0;
		int c=0;
		if((a++!=0 && b++!=0) || c++!=0) {
			System.out.println("1");
		}
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}
}
