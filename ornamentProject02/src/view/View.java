package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.dto.BagDTO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class View {
	private static Scanner sc = new Scanner(System.in);

	public String inputId() { // 아이디입력
		String pattern = "^[A-Za-z]{3,16}$";
		while (true) {
			System.out.print("아이디 입력 >> ");
			String id = sc.next();

			if (id.matches(pattern)) {
				return id;
			} else {
				System.out.println("영문 대소문자 구분 없이 30자 이내로 입력해주세요");
			}
		}
	}

	public String inputPassword() { // 비밀번호 입력
		System.out.print("비밀번호 입력 >> ");
		String pw = sc.next();
		return pw;
	}

	public String inputName() { // 이름 입력
		String pattern = "^[가-힣]{2,5}$";
		while (true) {
			System.out.print("이름 입력 >> ");
			String name = sc.next();

			if (name.matches(pattern)) {
				return name;
			} else {
				System.out.println("이름 형식이 잘못되었습니다.");
				System.out.println("다시 입력해주세요");
			}
		}
	}

	public String inputAddress() { // 주소 입력
		System.out.println("주소는 띄어쓰기없이 00시로 입력해주세요"); // 00구 00동
		System.out.print("주소 입력 >> ");
		String address = sc.next();
		return address;
	}

	public String inputPhoneNum() { // 핸드폰번호 입력
		System.out.println("전화번호는 '-'를 제외하고 숫자 11자리만 입력해주세요");

		String phoneNum;
		while (true) {
			System.out.print("핸드폰 번호 입력 >> ");
			phoneNum = sc.next();
			String pattern = "^(01[016789])\\d{7,8}$";

			if (phoneNum.matches(pattern)) {
				// 정규식에 맞을 때
				return phoneNum;
			} else {
				// 잘못된 형식일 때
				System.out.println("숫자 11자리로 다시 입력해주세요.");
			}
		}
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
		System.out.print("입력 >> ");
		int num = sc.nextInt();
		if (num == 1) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public int inputProductCountToPutInBag() {
		System.out.println("장바구니에 추가할 상품의 개수를 입력해주세요");
		System.out.print(" >>> ");
		int num = sc.nextInt();
		return num;
	}

	public void printLogoutSuccess() { // 로그아웃 성공 출력문
		System.out.println("로그아웃 성공!!");
	}

	public void printLogoutFail() { // 로그아웃 실패 출력문
		System.out.println("로그아웃 취소");
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
		System.out.println("회원 탈퇴 취소하셨습니다");
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
			if (cnt >= 0) {
				return cnt;
			}
			System.out.println("구매할 수량을 제대로 입력해주세요.");
		}
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
		System.out.print("번호 입력 >> ");
		int num = sc.nextInt();
		if (num == 1) {
			flag = true;
		}
		else {			
			flag = false;
		}
		return flag;
	}

	public void printDontBuyProduct() { // 상품 구매불가 출력문
		System.out.println("현재 남아있는 상품이 없어 구매가 불가능합니다.");
	}

	public String inputSearch() { // 검색어 입력
		System.out.print("검색어 입력 >> ");
		String search = sc.next();
		return search;
	}

	public void printProduct(ProductDTO data) { // 상품 상세보기 출력문
		System.out.println("----------------");
		System.out.println("상품명 : " + data.getProductName());
		System.out.println("가격 : " + data.getProductPrice());
		System.out.println("브랜드 명 : " + data.getProductBrand());
		System.out.println("상품 PK : " + data.getProductPK());
		System.out.println("----------------");
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

	public String inputBrandName() { // 브랜드 선택 입력
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
		if (datas.isEmpty()) {
			System.out.println("출력할 데이터가 없습니다");
		} else {
			System.out.println("------ 상품 ------");
			for (int i = 0; i < ((datas.size() < 5) ? datas.size() : 5); i++) {
				System.out.println("<" + (i + 1) + "> [" + datas.get(i).getProductName() + "], ["
						+ datas.get(i).getProductPrice() + "], [" + datas.get(i).getProductBrand() + "]");
			}
			System.out.println("------ -- ------");
		}
	}

	public int printProductDetailMenu() {
		System.out.println("12. 검색어로 출력");
		System.out.println("13. 가격 내림차순");
		System.out.println("14. 가격 오름차순");
		System.out.println("15. 브랜드별");
		System.out.println("8. 상품 상세보기");
		System.out.print("번호를 입력해주세요 >>> ");
		int num;
		while (true) {
			num = sc.nextInt();
			if (num == 12 || num == 13 || num == 14 || num == 15 || num == 8) {
				return num;
			}
			System.out.print("알맞은 번호를 눌러주세요 >>> ");
		}
	}

	public void printBag(ArrayList<BagDTO> datas) {
		System.out.println(" --- 장바구니 --- ");
		if (datas.isEmpty()) {
			System.out.println("장바구니가 비어있습니다.");
		} else {
			for (int i = 0; i < datas.size(); i++) {
				System.out.println("상품 이름: " + datas.get(i).getProductName());
				System.out.println("브랜드: " + datas.get(i).getProductBrand());
				System.out.println("개수: " + datas.get(i).getProductCount());
				System.out.println("가격: " + datas.get(i).getProductPrice());
			}
		}
		System.out.println();
		int totalPrice =0;
		for(int i=0;i<datas.size();i++) {
			totalPrice += datas.get(i).getProductPrice()*datas.get(i).getProductCount();
		}
		System.out.println("총 가격 : " + totalPrice + "원");
		System.out.println(" --- ---- --- ");
	}

	// 수정02 아래 메서드 시그니처부터 ProductDTO 반환하게 변환
	public ProductDTO inputProductNum(ArrayList<ProductDTO> datas) { // 상품번호입력
		// 사용자에게 상품 목록에 있는 1~5번 상품을 반환한다

		int num;
		while (true) {
			System.out.print("상품 선택 >> ");
			num = sc.nextInt();
			if (1 <= num && 5 >= num) {
				break;
			}
			System.out.println("범위 내(1~5)에서 다시 입력해주세요");
		}
		return datas.get(num - 1);
	}

	public int getPK() { // 상품PK입력

		int num;

		System.out.print("상품 pk 입력 >> ");
		num = sc.nextInt();

		return num;
	}

	public int printMypage(MemberDTO memberDTO) { // 마이페이지출력
		System.out.println("---------------<나의 정보 >--------------");
		System.out.println(" 아이디 : " + memberDTO.getMemberId());
		System.out.println(" 이름 : " + memberDTO.getMemberName());
		System.out.println(" 주소 : " + memberDTO.getMemberAddress());
		System.out.println(" 핸드폰번호 : " + memberDTO.getMemberPhoneNumber());
		System.out.println("---------------------------------------");
		System.out.println("20. 회원 탈퇴");
		System.out.println("25. 메인페이지로 돌아가기");
		System.out.print("메뉴 번호 입력 >> ");
		int num;
		while (true) {
			num = sc.nextInt();
			if (num == 20) {
				return num;
			} else if (num == 25) {
				return num;
			}
			System.out.println("다시 입력해주세요 ( 25 또는 20 )");
			System.out.print(">> ");
		}
	}

	public void printWrongPassword() {
		System.out.println("잘못된 비밀번호입니다.");
		System.out.println("다시 입력해주세요.");
	}

	// 탈퇴 여부 확인 1: 예 2: 아니오
	public int isCheckQuit() {
		System.out.println("정말로 탈퇴하시겠습니까?");
		System.out.println("1. 예      2. 아니오");
		System.out.print("번호 입력 >> ");
		int num = 0;
		while (true) {
			num = sc.nextInt();
			if (num == 1) {
				return num;
			} else if (num == 2) {
				return num;
			}
			System.out.println("다시 입력해주세요 ( 1 또는 2 )");
		}
	}

	public int printQuitMenu() { // 비회원 메뉴 출력
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("7.전체출력");
		System.out.println("11. 장바구니");
		int num = 0;
		while (true) {
			System.out.print("메뉴 번호 입력 >> ");
			num = sc.nextInt();
			if (num == 1 || num == 2 || num == 7 || num == 11) {
				return num;
			}
			System.out.println("다시 입력해주세요");
		}
	}

	public int printUserMenu() { // 회원 메뉴 출력
		System.out.println("3. 로그아웃");
		System.out.println("4. 마이페이지");
		System.out.println("7.전체출력");
		System.out.println("11. 장바구니");
		int num;
		while (true) {
			System.out.print("메뉴 번호 입력 >> ");
			num = sc.nextInt();
			if (num == 3 || num == 4 || num == 7 || num == 11) {
				return num;
			}
			System.out.println("다시 입력해주세요");
		}
	}

	// 상세보기 했을 때만 보이는 메뉴!!
	public int printDetailedMenu() {
		System.out.println("10. 바로 구매하기");
		System.out.println("23. 장바구니 추가");
		int num;
		while (true) {
			System.out.print("메뉴 번호 입력 >> ");
			num = sc.nextInt();
			if (num == 10 || num == 23) {
				return num;
			}
			System.out.println("다시 입력해주세요");
		}
	}

	public int printAdminMenu() { // 관라지 메뉴 출력
		System.out.println("4. 마이페이지");
		System.out.println("5. 상품 추가");
		System.out.println("6. 재고 추가");
		System.out.println("7.전체출력");
		System.out.println("11. 장바구니");
		int num;
		while (true) {
			System.out.print("메뉴 번호 입력 >> ");
			num = sc.nextInt();
			if (num == 4 || num == 5 || num == 6 || num == 7 || num == 11) {
				return num;
			}
			System.out.println("다시 입력해주세요");
		}
	}

	public int printBuy() {
		System.out.println("물품들을 구매하시겠습니까?");
		System.out.println("21. 예");
		System.out.println("22. 아니오");
		int num;
		while (true) {
			num = sc.nextInt();
			if (num == 21 || num == 22) {
				return num;
			}
			System.out.println("21 또는 22를 입력해주세요");
		}
	}

	public void printLoginFirst() {
		System.out.println("로그인 후 이용가능합니다.");
	}

	public void printProductBoughtSuccess(ProductDTO data) {
		System.out.println(data.getProductName() + "," + data.getProductCount() + "개 구매완료");
	}

	public void printProductBoughtFailed(ProductDTO data) {
		System.out.println(data.getProductName() + " 구매실패");
	}
	// 수정05 상품 상세보기 관리자뷰 제작
	public int printDetailedAdminMenu() {
		System.out.println("10. 바로 구매하기");
		System.out.println("23. 장바구니 추가");
		System.out.println("25. 상품 삭제");
		int num;
		while (true) {
			System.out.print("메뉴 번호 입력 >> ");
			num = sc.nextInt();
			if (num==10 || num==23 || num == 25) {
				return num;
			}
			System.out.println("다시 입력해주세요");
		}
	}
	public void printDeleteProductFail() {
		System.out.println("상품이 삭제되지 못하였습니다.");
	}
	public void printDeleteProductSuccess() {
		System.out.println("상품이 삭제되었습니다");
	}
	public void printDeleteProductCancel() {
		System.out.println("상품 삭제가 취소되었습니다.");
	}
}
