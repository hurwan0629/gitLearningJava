package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.MovieDTO;

public class View {

	//스캐너를 공유자원으로 만들기
	static protected Scanner scanner = new Scanner(System.in);

	//메뉴출력 만들기
	public void printMainMenu() {
		System.out.println();
		System.out.println("/========영화관=======\\");
		System.out.println("  1. 전체출력하기");
		System.out.println("  2. 영화 하나만 출력하기");
		System.out.println("  3. 영화추가하기");
		System.out.println("  4. 영화삭제하기");
		System.out.println("  5. 영화변경하기");
		System.out.println("  0. 프로그램 종료");
		System.out.println("\\===================/\n");
	}

	//메뉴번호 입력하기
	public int getMenuNum() {
		System.out.print("메뉴번호를 입력해주세요 >> ");
		int num = getNum(0, 5);
		return num;
	}
	
	// 영화PK 입력하기
	public int getMovieNum() {
		System.out.print("영화 번호를 입력해주세요 >> ");
		int num = scanner.nextInt();
		return num;
	}
	
	//영화 PK 입력하기 -> 삭제할 영화번호 PK 입력받을 때 사용
	public int getDeleteMovieNum(ArrayList<MovieDTO> datas) {
		System.out.print("영화의 번호를 입력해주세요 >> ");
		int num = scanner.nextInt();
		return num;
	}
	
	//프로그램 종료하기
	public void printTurnOff() {
		System.out.println("프로그램을 종료합니다...");
	}

	//성공 출력하기
	public void func01() {
		System.out.println("성공!");
	}

	//실패 출력하기
	public void func02(){ 
		System.out.println("실패..");
	}
	
	// 영화변경하기 -> 변경방식선택
	public int getChoiceMune(){
		System.out.println("1. 제목 수정");
		System.out.println("2. 장르 수정");
		System.out.print("번호를 입력해주세요 >> ");
		int num = getNum(1, 2); // 유효성 검사
		return num;
	}


	// 영화 장르 입력하기
	public String getName() {
		String genre;
		while(true){
			System.out.print("장르를 입력해주세요! >> ");
			genre = scanner.next();
			if(genre.isEmpty()) {// 영화장르가 비어있다면
				System.out.println("장르는 비워둘수없습니다! 장르를 적어주세요!");
				continue;
			}
			return genre;
		}
		
	}

	// 영화제목입력하기(추가하기)
	public String getMovieTitle() {
	    String title;
	    while (true) {
	        System.out.print("영화제목을 입력해주세요! >> ");
	        title = scanner.next(); // ⬅️ 첫 줄의 scanner.nextLine(); 삭제됨
	        if (title.trim().isEmpty()) {
	            System.out.println("영화 제목은 비워둘 수 없습니다! 제목을 입력해주세요!");
	            continue;
	        }
	        return title;
	    }
	}
	
	// 중복된 제목일 경우
	public void duplicationMovieTitle() {
		System.out.println("해당 영화는 이미 등록되어 있습니다");
	}

	// 데이터에 저장된 영화가 없을때 //한개출력
	public void printData(MovieDTO data) {		
		if (data == null) {
	        System.out.println("해당 영화가 존재하지 않습니다.");
	        return;
	    }
	    System.out.println("----- 영화 정보 -----");
	    System.out.println("번호: " + data.getTitleId());
	    System.out.println("제목: " + data.getTitle());
	    System.out.println("장르: " + data.getGenre());
		System.out.println("-------------------");
	}
	
	
	// 데이터에 저장된 영화가 없을때 // 한개 이상 출력
	public void printDatas(ArrayList<MovieDTO> datas) {
		if(datas.isEmpty()) {
			System.out.println("현재 상영중인 영화가 없습니다...ㅜㅜ");
			return;
        }
        for(MovieDTO data:datas) {
        	System.out.println(data);
        }
	}

	//메인메뉴 숫자 말고 입력방지
	public int getNum(int min, int max) {
		while(true) {
			try {
				int num = scanner.nextInt(); // 숫자 입력받기
				if(num >= min && num <= max) {
					return num;
				}
				System.out.print("다시 입력해주세요 >> ");
			}catch(Exception e) {
				scanner.nextLine();
				System.out.println("문자는 입력할수 없습니다!");
				System.out.print("숫자만 입력해주세요! >>  ");
			}
			continue;	
		}
	}
}
