package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.dto.ProductDTO;

public class View {
	static protected Scanner scanner = new Scanner(System.in);

	
	public void showMenu() {
		System.out.println("");
		System.out.println("0. 나가기");
		System.out.println("1. 상품목록출력");
		System.out.println("2. 상품검색");
	}
	public int getCommand() {
		int command=0;
		while(true) {
			command = this.scanner.nextInt();
			if(0<=command && command <=2) {
				break;
			}
		}
		return command;
	}
	public void showTurnOff() {
		System.out.println("프로그램 종료...");
	}
	public String getKeyword() {
		System.out.print("검색어를 입력해주세요 >>> ");
		String keyword = this.scanner.next();
		return keyword;
	}
	public void showProducts(ArrayList<ProductDTO> datas) {
		if(datas.size()<=0) {
			System.out.println("상품이 존재하지 않습니다...");
			return;
		}
		for(ProductDTO data:datas) {
			System.out.println(data);
		}
	}
}
