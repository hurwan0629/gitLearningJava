package zzzzPractice;

public class Test02 {
	public static void main(String[] args) {
		String email = "hurwan0629@gmail.com";
		System.out.println(email.substring(0, email.indexOf('@')));
		System.out.println("01234".indexOf('3'));
		System.out.println("1");
		for(int i=0;i<10;i++) {
			for(int j=((i%2==0)?0:1);j<10;j+=2) {
				System.out.print("");
			}
		}
	}
}
