package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.dto.BoardDTO;
import model.dto.MemberDTO;
import model.dto.ReplyDTO;

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
	
	// 글 세부사항 출력하기
	// 느낌이 하나의 페이지(html이라던가)를 만들어서 거기에서 출력도 하고 입력도 받을 수 있는거같은거?
	// 지금 자바에서는 인자를 하나만 만들 수있어서 이렇게 return을 하나만 만들지만(배열이나 DTO빼고)
	// 실제 페이지에서는 하나의 페이지에서 많은 요청과 return이 있을 수 있다. 이런느낌인가?ㅁㄴㄴ
	public int printBoardData(BoardDTO data, MemberDTO userInfo, ArrayList<ReplyDTO> rdatas) {
		String writer = data.getWriter() == null ? NODATA : data.getWriter();
		
		System.out.println("글 번호 : "+data.getBid());
		System.out.println("제목 : "+data.getTitle());
		System.out.println("내용 : "+data.getContent());
		System.out.println("작성자 : "+ writer);
		System.out.println("조회수 : "+data.getBcount());
		
		System.out.println();
		System.out.println("=== 댓글 목록 ===");
		if(rdatas.isEmpty()) {
			System.out.println("댓글 없음!");
		}
		else {
			for(ReplyDTO rdata: rdatas) {
				System.out.println(rdata);
			}
		}
		System.out.println("===============");
		System.out.println();
		// 본인글이면 또는 접속한 사람이 관리자라면
		// == 현재 로그인한 사람의 정보 == 작성자의 정보
		// 만약에 관리자에게 다른 기능을 만들 수 있으면 else if로 떼어놓아야함
		if( userInfo.getMrole().equals("ADMIN") || 
				userInfo.getMid().equals(data.getMid())) {
			System.out.println("12. 제목변경");
			System.out.println("13. 내용 변경");
			System.out.println("14. 글 삭제");
		}
		
		System.out.println("15. 메뉴로 돌아가기");
		System.out.println("16. 댓글 작성");
		System.out.print(" >>> ");
		int num = sc.nextInt();
		return num;
	}
	// 모든 글 출력하기
	public void printBoardDatas(ArrayList<BoardDTO> datas) {
		if(datas.isEmpty()) {
			System.out.println("출력할 글이 없습니다!");
			return;
		}
		
		System.out.println("글 번호 \t 글 제목 \t 작성자 \t\t 조회수");
		for(BoardDTO data:datas) {
			// 글을 출력할 때 삭제된 사용자들은 NODATA값으로 출력하게 삼항연산자로 String 만들기
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
