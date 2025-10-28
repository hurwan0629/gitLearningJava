package view;

import model.dto.MemberDTO;

public class AdminView extends View {
	public int getCount() {
		System.out.print("상품개수입력 >> ");
		int count = scanner.nextInt();
		return count;
	}
	public int getProductPrice() {
		System.out.print("상품가격입력 >> ");
		int price = View.scanner.nextInt();
		return price;
	}
	public void printLoginMenu(MemberDTO member) {
		System.out.println();
		System.out.println(" +++ 관리자 모드 +++");
		System.out.println(member.getMemberName()+"님, 환영합니다!");
		System.out.println("9. 상품추가");
		System.out.println("10. 상품삭제");
		System.out.println("11. 상품재고추가");
		System.out.println("5. 로그아웃");
		System.out.println("0. 프로그램 종료");
	}
}
