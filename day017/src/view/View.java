package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class View {
	static protected Scanner scanner = new Scanner(System.in);

	public void printMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 전체상품 출력");
		System.out.println("4. 상품검색");
		System.out.println("0. 프로그램 종료");
	}
	public void printLoginMenu(MemberDTO userInfo) {
		System.out.println(userInfo.getMemberName() + "님 환영합니다!");
		System.out.println("5. 로그아웃");
		System.out.println("6. 회원탈퇴");
		System.out.println("7. 비밀번호 변경");
		System.out.println("8. 상품구매");
		System.out.println("0. 프로그램 종료");
	}
	
	public int getMenuNum() {
		System.out.println("메뉴번호입력");
		System.out.print(" >>> ");
		int num = scanner.nextInt();
		return num;
	}
	
	public void printTurnOff() {
		System.out.println("프로그램을 종료합니다.");
	}
	
	public void func01() {
		System.out.println("성공!");
	}
	public void func02() {
		System.out.println("실패...");
	}
	public void func03() {
		System.out.println("로그아웃 화면으로 돌아갑니다.");
	}
	public int getCount(ProductDTO data) {
		int count; // scope(유효범위) 이슈
		while(true) {
			System.out.print("상품개수입력 >> ");
			count = scanner.nextInt();
			if(0<count && count<=data.getProductCount()) {
				break;
			}
			System.out.println("다시 입력해주세요!");
		}
		return count;
	}
	public String getProductNum(ArrayList<ProductDTO> datas) {
//		for(ProductDTO data:datas) {
//			System.out.println(data);
//		}
//		
		int num;
		while(true) {
			try {
				System.out.print("상품번호입력 >> ");
				num = scanner.nextInt();
				
				boolean flag = false;
				for(ProductDTO data:datas) {
					if(data.getProductId().equals(Integer.toString(num))) {
						// 재고가 있다면
						if(data.getProductCount() > 0) {
							flag = true;
							break;	
						}
						// 재고가 없다면
						break;
					}
				}
				if(!flag) {
					System.out.println("번호및 재고를 확인 후 다시 입력해주세요!");
					continue;
				}
				break;
			}
			catch(Exception e) {
				scanner.nextLine();
				System.out.println("다시 입력해주세요!");
			}
		}
		return Integer.toString(num);
	}
	public String getProductName() {
		System.out.print("상품이름입력 >> ");
		String name = View.scanner.next();
		return name;
	}
	public void loginSuccess(MemberDTO data) {
		System.out.println("반갑습니다 " + data.getMemberName() + "님");
	}
	public void loginFail() {
		System.out.println("회원또는 비밀번호가 잘못되었습니다...");
	}
	
	
}

