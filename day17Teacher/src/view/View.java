package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class View {
	static protected Scanner scanner = new Scanner(System.in);

	public void printMainMenu() {
		System.out.println();
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 전체상품출력");
		System.out.println("4. 상품검색");
		System.out.println("0. 프로그램 종료");
	}
	public void printLoginMenu(MemberDTO member) {
		System.out.println();
		System.out.println(member.getMemberName()+"님, 환영합니다!");
		System.out.println("5. 로그아웃");
		System.out.println("6. 회원탈퇴");
		System.out.println("7. 비밀번호 변경");
		System.out.println("8. 상품구매");
		System.out.println("0. 프로그램 종료");
	}
	public int getMenuNum() {
		System.out.print("메뉴번호입력 >> ");
		int num = scanner.nextInt();
		return num;
	}
	public void printTurnOff() {
		System.out.println("프로그램을 종료합니다.");
	}
	public String getProductName() {
		System.out.print("상품이름입력 >> ");
		String name = View.scanner.next();
		return name;
	}
	public void func01() {
		System.out.println("성공!");
	}
	public void func02() {
		System.out.println("실패...");
	}
	public void func03() {
		System.out.println("로그아웃 후 다시 이용해주세요!");
	}
	public int getCount(ProductDTO data) {
		int count; // scope(유효범위) 이슈
		while(true) {
			System.out.print("상품개수입력 >> ");
			count = scanner.nextInt();
			if(0<count && count<=data.getCount()) {
				break;
			}
			System.out.println("다시 입력해주세요!");
		}
		return count;
	}
	public int getProductNum(ArrayList<ProductDTO> datas) {
		int num;
		while(true) {
			try {
				System.out.print("상품번호입력 >> ");
				num = scanner.nextInt();

				boolean flag = false;
				for(ProductDTO data:datas) {
					if(data.getProductId() == num) {
						if(data.getCount() > 0) {
							flag = true;
						}
						break;
					}
				}
				if(!flag) {
					System.out.println("번호 및 재고 확인후 다시 입력해주세요!");
					continue;
				}

				break;
			}
			catch(Exception e) {
				scanner.nextLine();
				System.out.println("다시 입력해주세요!");
			}
		}
		return num;
	}
}
