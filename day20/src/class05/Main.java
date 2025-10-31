package class05;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("1. 상품 추가하기");
			System.out.println("2. 상품 재고 변경하기");
			System.out.println("3. 상품 삭제하기");
			System.out.println("4. 상품 전체 출력하기");
			System.out.println("5. 상품 하나 출력하기");
			System.out.println("0. 프로그램 종료");
			System.out.print(" >>> ");
			int command = sc.nextInt();
			sc.nextLine();
			if(command == 0) {
				System.out.println("프로그램 종료...");
				break;
			}
			if (command == 1) {
				System.out.print("추가할 상품 이름 >>> ");
				String name = sc.next();
				System.out.print("추가할 상품 가격 >>> ");
				int price = sc.nextInt();
				System.out.print("추가할 상품 재고 >>> ");
				int count = sc.nextInt();

				Insert.insert(name, price, count);
			} else if (command == 2) {
				System.out.print("변경할 상품 번호 >>> ");
				int id = sc.nextInt();

				System.out.print("변경할 재고 수량 >>> ");
				int count = sc.nextInt();

				Update.update(id, count);
			} else if (command == 3) {
				System.out.print("삭제할 상품 번호 >>> ");
				int id = sc.nextInt();

				Delete.delete(id);
			} else if (command == 4) {
				SelectAll.selectAll();
			} else if (command == 5) {
				System.out.print("출력할 상품 번호 >>> ");
				int id = sc.nextInt();

				SelectOne.selectOne(id);
			}
		}
	}
}
