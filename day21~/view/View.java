package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.dto.BoardDTO;
import model.dto.MemberDTO;

public class View {
	private static Scanner sc = new Scanner(System.in);
	private static final String NODATA="탈퇴한 사용자"; 
	
	public void printJoinInfo() {
		System.out.println("존재하는 아이디입니다! 다시 입력해주세요!~~");
	}
	public void printMenu01() {
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("7. 글 전체출력");
		System.out.println("9. 제목으로 글 검색");
		System.out.println("10. 작성자로 글 검색");
		System.out.println("11. 조회수 순서대로 글 전체출력");
		System.out.println("0. 프로그램 종료");
	}
	public void printMenu02() {
		System.out.println("3. 로그아웃");
		System.out.println("4. 회원 이름변경");
		System.out.println("5. 회원탈퇴");
		System.out.println("6. 글 작성");
		System.out.println("8. 글 상세보기");
		System.out.println("0. 프로그램 종료");
	}
	public int getBoardMenuNum() {
		System.out.println("12. 글 내용 변경");
		System.out.println("13. 글 제목 변경");
		System.out.println("14. 글 삭제");
		System.out.print("번호입력 >> ");
		int num = sc.nextInt();
		return num;
	}
	public int inputBoardNum() {
		System.out.print("글 번호 입력 >> ");
		int num = sc.nextInt();
		return num;
	}
	public void printNoBoardData() {
		System.out.println("출력할 내용이 없습니다!");
	}
	
	public int printBoardData(BoardDTO data, MemberDTO userInfo) {
		String writer = data.getWriter() == null ? NODATA : data.getWriter();
		
		System.out.println("글 번호 : "+data.getBid());
		System.out.println("제목 : "+data.getTitle());
		System.out.println("내용 : "+data.getContent());
		System.out.println("작성자 : "+ writer);
		System.out.println("조회수 : "+data.getBcount());
		
		System.out.println();
		// 본인글이면 또는 접속한 사람이 관리자라면
		// == 현재 로그인한 사람의 정보 == 작성자의 정보
		if( userInfo.getMrole().equals("ADMIN") || 
				userInfo.getMid().equals(data.getMid())) {
			System.out.println("12. 제목변경");
			System.out.println("13. 내용 변경");
			System.out.println("14. 글 삭제");
		}
		
		System.out.print("15. 메뉴로 돌아가기");
		int num = sc.nextInt();
		return num;
	}
	public void printBoardDatas(ArrayList<BoardDTO> datas) {
		if(datas.isEmpty()) {
			System.out.println("출력할 글이 없습니다!");
			return;
		}
		
		System.out.println("글 번호 \t 글 제목 \t 작성자 \t\t 조회수");
		for(BoardDTO data:datas) {
			String writer = data.getWriter() == null ? NODATA : data.getWriter()+"\t";
			System.out.println(data.getBid() + " \t " + data.getTitle() + " \t " + writer + " \t " + data.getBcount());
		}
	}
	public String getTitle() {
		System.out.print("글 제목 입력 >> ");
		String title = sc.next();
		return title;
	}
	public String getContent() {
		System.out.print("글 내용 입력 >> ");
		String content = sc.next();
		return content;
	}
	public int inputCommand() {
		System.out.print("메뉴번호입력 >> ");
		int command = sc.nextInt();
		return command;
	}
	public String inputMid() {
		System.out.print("회원 ID 입력 >> ");
		String mid = sc.next();
		return mid;
	}
	public String inputPasswd() {
		System.out.print("회원 PW 입력 >> ");
		String passwd = sc.next();
		return passwd;
	}
	public String inputName() {
		System.out.print("회원 이름 입력 >> ");
		String name = sc.next();
		return name;
	}
	public void printFunc00() {
		System.out.println("프로그램 종료");
	}
	public void printFunc01() {
		System.out.println("성공!");
	}
	public void printFunc02() {
		System.out.println("실패...");
	}
}
