package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.StockDTO;

public class View {
	private static Scanner sc = new Scanner(System.in);

	
	public void printTurnOff() {
		System.out.println(" // ^^7 // ");
		System.out.println(" 안녕히가세요");
	}
	
	public void printAllStock(ArrayList<StockDTO> datas) {
		if (datas.isEmpty()) { // datas.size() == 0
			System.out.println("보여줄 데이터가 없습니다...");
			return;
		}
		for (StockDTO data : datas) {
			System.out.println(data);
		}
	}

	public void printMenu() {
		System.out.println("0. 프로그램 종료");
		System.out.println("1. 종목 추가하기");
		System.out.println("2. 전체 종목 출력");
		System.out.println("3. 종목가격 검색");
		System.out.println("4. 종목 제거");
		System.out.println("5. 종목 주가 변경");
	}

	public int getMenuNum() {
		int command=0;
		while (true) {
			System.out.print(" >>> ");
			try {
				command = sc.nextInt();
				sc.nextLine();	
			}
			catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("올바른 번호를 입력해주세요!");
				continue;
			}
			if (0 <= command && command < 6) {
				break;
			}
			System.out.println("올바른 입력을 해주세요!");
		}
		return command;
	}

	public void printStock(StockDTO stockDTO) {
		if (stockDTO == null) {
			System.out.println("출력할 데이터가 없습니다");
			return;
		}
		System.out.println(stockDTO);
	}

	public void printSuccess() {
		System.out.println("성공!");
	}

	public void printFail() {
		System.out.println("실패!");
	}

	public StockDTO getStockName() {
		System.out.print("주식 이름을 입력해주세요 >>> ");
		String name = sc.next();
		sc.nextLine();
		StockDTO stockDTO = new StockDTO();
		stockDTO.setName(name);
		return stockDTO;
	}

	public StockDTO getStockPrice() {
		int price=0;
		while (true) {
			System.out.print("가격을 입력해주세요 >>> ");
			try {
				price = sc.nextInt();
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("올바른 가격을 입력해주세요!");
				continue;
			}
			if (0 < price) {
				break;
			}
			System.out.println("올바른 가격을 입력해주세요!");
		}

		StockDTO stockDTO = new StockDTO();
		stockDTO.setPrice(price);
		return stockDTO;
	}

	public StockDTO getStockNameFromList(ArrayList<StockDTO> datas) {
		int command = 0;
		StockDTO stockDTO = new StockDTO();
		for (int i = 0; i < datas.size(); i++) {
			System.out.println((i + 1) + ". " + datas.get(i).getName());
		}
		while (true) {
			System.out.print("번호를 입력하세요. >>> ");
			try {
				command = sc.nextInt();
				sc.nextLine();
			}
			catch(InputMismatchException e){
				sc.nextLine();
				System.out.println("올바른 번호를 입력해주세요!");
				continue;
			}
			if (0 < command  && command <= datas.size()) {
				break;
			}
			System.out.println("올바른 입력을 해주세요!");
		}
		stockDTO.setId(datas.get(command-1).getId());
		return stockDTO;
	}
}
