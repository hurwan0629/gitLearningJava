package view;

import java.util.ArrayList;

import model.dto.ProductDTO;

public class ClientView extends View {
	public String getMemberId() {
		
		System.out.print("회원 ID 입력 >> ");
		String id = View.scanner.next();
		return id;
	}
	public void printInfo() {
		System.out.println("해당아이디는 이미 사용되는 아이디입니다.");
		System.out.println("다른 아이디를 써주세요");
	}
	public String getMemberPasswd00() {
		System.out.print("회원 PW 입력 >> ");
		String pw = View.scanner.next();
		return pw;
	}
	public String getMemberPasswd01() {
		System.out.print("회원 PW 확인 >> ");
		String pw = View.scanner.next();
		return pw;
	}
	public String getMemberPasswd02() {
		System.out.print("바꿀 회원 PW 입력 >> ");
		String pw = View.scanner.next();
		return pw;
	}
	public String getMemberName() {
		System.out.print("회원 이름 입력 >> ");
		String name = View.scanner.next();
		return name;
	}
	public void printProductDatas(ArrayList<ProductDTO> datas) {
		if(datas.isEmpty()) {
			System.out.println("출력할 상품이 없습니다...");
			return;
		}

		for(ProductDTO data:datas) {
			System.out.println(data);
		}
	}
	public void printProductData(ProductDTO data) {
		System.out.println("상품명: " + data.getProductName());
		System.out.println("가격: " +data.getProductPrice());
		System.out.println("재고: " + data.getProductCount());
	}
}
