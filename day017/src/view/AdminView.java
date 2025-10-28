package view;

import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class AdminView extends View {
	
	public int getProductPrice() {
		System.out.print("상품가격입력 >> ");
		int price = View.scanner.nextInt();
		return price;
	}
	
	public void printLoginMenu(MemberDTO member) {
		System.out.println();
		System.out.println(" +++ 관리자 모드 +++ ");
		System.out.println(member.getMemberName() + "님 환영합니다!");
		System.out.println("9. 상품추가");
		System.out.println("10. 상품삭제");
		System.out.println("11. 상품재고 추가");
		System.out.println("5. 로그아웃");
		System.out.println("0. 프로그램 종료");
	}
	public int getCount() {
		int count; // scope(유효범위) 이슈
		while(true) {
			System.out.print("상품개수입력 >> ");
			count = scanner.nextInt();
			if(0<count ) {
				break;
			}
			System.out.println("다시 입력해주세요!");
		}
		return count;
	}
}
