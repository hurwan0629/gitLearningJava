package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class View {
	private static Scanner sc = new Scanner(System.in);

	public String inputId() { // 아이디입력
		System.out.print("아이디 입력 >> ");
		String id = sc.next();
		return id;
	}

	public String inputPassword() { // 비밀번호 입력
		System.out.print("비밀번호 입력 >> ");
		String pw = sc.next();
		return pw;
	}

	public String inputName() { // 이름 입력
		System.out.print("이름 입력 >> ");
		String name = sc.next();
		return name;
	}

	public String inputAddress() { // 주소 입력
		System.out.print("주소 입력 >> ");
		String address = sc.next();
		return address;
	}

	public String inputPhoneNum() { // 핸드폰번호 입력
		System.out.print("핸드폰 번호 입력 >> ");
		String phoneNum = sc.next();
		return phoneNum;
	}

	public void printoverLapUser() { // 아이디 중복 출력문
		System.out.println("중복된 사용자입니다. 다시 입력해주세요");
	}

	public void printLoginSuccess() { // 로그인 성공 출력문
		System.out.println("로그인 성공!!");
	}

	public void printLoginFail() { // 로그인 실패 출력문
		System.out.println("로그인 실패...");
	}

	public boolean printLogoutMsg() { // 로그아웃 여부 메세지
		boolean flag = false;
		System.out.println("로그아웃 하시겠습니까??");
		System.out.println("1. 예      2. 아니오");
		int num = sc.nextInt();
		if (num == 1) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public void printLogoutSuccess() { // 로그아웃 성공 출력문
		System.out.println("로그아웃 성공!!");
	}

	public void printLogoutFail() { // 로그아웃 실패 출력문
		System.out.println("로그아웃 실패...");
	}

	public void printSigninSuccess() { // 회원가입 성공 출력문
		System.out.println("회원가입 성공!!");
	}

	public void printSigninFail() { // 회원가입 실패 출력문
		System.out.println("회원가입 실패...");
	}

	public void printAddProductSuccess() { // 상품 추가 성공 출력문
		System.out.println("상품 추가 성공!!!");
	}

	public void printAddProductFail() { // 상품 추가 실패 출력문
		System.out.println("상품 추가 실패...");
		System.out.println("해당 상품이 존재하지 않거나 시스템의 오류가 일어났습니다.");
	}

	public void printQuitSuccess() { // 회원 탈퇴 성공 출력문
		System.out.println("회원 탈퇴 성공");
	}

	public void printQuitFail() { // 회원 탈퇴 실패 출력문
		System.out.println("회원 탈퇴 실패...");
	}

	public void printBuySuccess() { // 구매 성공 출력문
		System.out.println("구매 성공");
	}

	public void printBuyFail() { // 구매 실패 출력문
		System.out.println("구매 실패");
	}

	public String inputProductName() { // 상품 이름 입력
		System.out.print("상품 이름 입력 >> ");
		String productName = sc.next();
		return productName;
	}

	public int inputProductPrice() { // 상품 가격 입력
		int productPrice;
		while (true) {
			System.out.print("상품 가격 입력 >> ");
			productPrice = sc.nextInt();
			if (productPrice <= 0) {
				break;
			}
			System.out.println("금액을 다시 입력하세요(0원인 물품은 없습니다.)");
		}
		return productPrice;
	}

	public int inputProductCount() { // 상품 재고 입력
		int productCount;
		while (true) {
			System.out.print("상품 재고 입력 >> ");
			productCount = sc.nextInt();
			if (productCount < 0) {
				break;
			}
			System.out.println("수량을 다시 입력해주세요");
		}
		return productCount;
	}

	public int buyProductCount() { // 상품 구매 수량 입력
		int cnt;
		while (true) {
			System.out.print("구매할 상품 수량 입력 >> ");
			cnt = sc.nextInt();
			if (cnt < 0) {
				break;
			}
			System.out.println("구매할 수량을 제대로 입력해주세요.");
		}
		return cnt;
	}

	public void printSearchMenu() { // 검색 메뉴 출력문
		System.out.println("------------------");
		System.out.println("1. 검색어로 출력");
		System.out.println("2. 가격 오름차순으로 출력");
		System.out.println("3. 가격 내림차순으로 출력");
		System.out.println("4. 브랜드명으로 출력");
		System.out.println("------------------");
	}

	public void printExit() { // 프로그램 종료 출력문
		System.out.print("프로그램 종료 .... ");
	}

	public boolean printDeleteProduct() { // 상품삭제질문 출력문
		boolean flag = false;
		System.out.println("정말로 삭제 하시겠습니까?");
		System.out.println("1. 예      2. 아니오");
		System.out.println("선택 1       2");
		int num = sc.nextInt();
		if (num == 1) {
			flag = true;
		}
		flag = false;
		return flag;
	}

	public void printDontBuyProduct() { // 상품 구매불가 출력문
		System.out.println("현재 남아있는 상품이 없어 구매가 불가능합니다.");
	}

	public String inputSearch() { // 검색어 입력
		System.out.println("검색어 입력 >> ");
		String search = sc.next();
		return search;
	}

	public void printProduct(ProductDTO data) { // 상품 상세보기 출력문
		System.out.println("상품명 : " + data.getProductName());
		System.out.println("가격 : " + data.getProductPrice());
		System.out.println("브랜드 명 : " + data.getProductBrand());
		System.out.println("상품 PK : " + data.getProductPK());
	}

	public int addInventory() { // 상품 재고추가
		int num;
		while (true) {
			System.out.println("추가할 수량 입력 : ");
			num = sc.nextInt();
			if (num <= 0) {
				break;
			}
			System.out.println("추가할 수량을 다시 입력해주세요(0개를 추가할 수 없습니다.)");
		}
		return num;
	}

	public void printBrandName() { // 브랜드 이름 출력
		System.out.println("1. 텐바이텐");
		System.out.println("2. 제이큐");
		System.out.println("3. 더원스토리");
		System.out.println("4. 아트박스");
	}

	public String inputBrandNum() { // 브랜드 선택 입력
		// 사용자가 숫자를 입력하면
		int num;
		while (true) {
			System.out.println("번호 입력 >> ");
			num = sc.nextInt();
			if (0 < num && num < 5) {
				break;
			}
			System.out.println("다시 입력해주세요");
		}
		if (num == 1) {
			return "텐바이텐";
		} else if (num == 2) {
			return "제이큐";
		} else if (num == 3) {
			return "더원스토리";
		} else if (num == 4) {
			return "아트박스";
		}
		return null;
	}

	public void printAllProducts(ArrayList<ProductDTO> datas) { // 상품 전체 출력
		// [물품이름], [가격], [브랜드] <- 이렇게 보여줄게
		// 5개 상품을 보여줄게
		System.out.println("=== 상품 ===");
		if(datas.size()==0) {
			System.out.println("상품이 존재하지 않습니다.");
		}
		else {
			for (int i = 0; i < datas.size()%5; i++) {
				System.out.println("[" + datas.get(i).getProductName() + "], [" + datas.get(i).getProductPrice() + "], ["
						+ datas.get(i).getProductBrand() + "]");
			}
		}
		System.out.println("===========");
	}

	public int inputProductNum() { // 상품번호입력
		// 사용자에게 상품 목록에 있는 1~5중 번호를 입력받는다
		int num;
		while (true) {
			System.out.println("상품 선택 >> ");
			num = sc.nextInt();
			if (0 <= num && 5 < num) {
				break;
			}
			System.out.println("범위 내(1~5)에서 다시 입력해주세요");
		}
		return num;
	}

	public int getPK() { // 상품PK입력

		int num;

		System.out.print("상품 pk 입력 >> ");
		num = sc.nextInt();

		return num;
	}

	public void printMypage(MemberDTO memberDTO) { // 마이페이지출력
		System.out.println("아이디 : " + memberDTO.getMemberId());
		System.out.println("이름 : " + memberDTO.getMemberName());
		System.out.println("주소 : " + memberDTO.getMemberAddress());
		System.out.println("핸드폰번호 : " + memberDTO.getMemberPhoneNumber());
		System.out.println("7. 회원 탈퇴");
	}

	public void printQuitMenu() { // 비회원 메뉴 출력
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("7.전체출력");
		System.out.println("11. 장바구니");
	}

	public void printUserMenu() { // 회원 메뉴 출력
		System.out.println("3. 로그아웃");
		System.out.println("4. 마이페이지");
		System.out.println("7.전체출력");
		System.out.println("11. 장바구니");
		
	}

	public void printAdminMenu() { // 관라지 메뉴 출력
		System.out.println("4. 마이페이지");
		System.out.println("5. 상품 추가");
		System.out.println("6. 재고 추가");
		System.out.println("7.전체출력");
		System.out.println("11. 장바구니");
	}

	public int getMenuNum() { // 메뉴 선택 숫자 받기
		int num;
		while (true) {
			System.out.print("메뉴 번호 입력 >> ");
			num = sc.nextInt();
			if (0 < num && num < 16) {
				return num;
			}
			System.out.println("다시 입력해주세요");
		}

	}

}
