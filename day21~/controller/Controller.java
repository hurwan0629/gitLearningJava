package controller;

import model.dao.BoardDAO;
import model.dao.MemberDAO;
import model.dao.ReplyDAO;
import model.dto.BoardDTO;
import model.dto.MemberDTO;
import view.View;

public class Controller {
	private MemberDAO memberDAO;
	private BoardDAO boardDAO;
	private ReplyDAO replyDAO;
	private View view;

	private MemberDTO userInfo; // 쿠키(로컬 스토리지,세션 등)
	public Controller() {
		memberDAO = new MemberDAO();
		boardDAO = new BoardDAO();
		replyDAO = new ReplyDAO();
		view = new View();

		userInfo = null; // 비로그인 상태
	}

	public void startApp() {
		while(true) {
			if(userInfo != null) { // 로그인했니?
				// 로그인했을때의 메뉴출력
				view.printMenu02();
			}
			else {
				// 로그인안했을때의 메뉴출력
				view.printMenu01();
			}
			int command = view.inputCommand();
			if(command == 0) { // 종료조건
				view.printFunc00();
				break;
			}
			else if(command == 1) {
				String mid;
				while(true) {
					mid = view.inputMid();

					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setCondition("JOIN");
					memberDTO.setMid(mid);
					memberDTO = memberDAO.selectOne(memberDTO);
					if(memberDTO == null) { // 중복이 아니라면
						break;
					}
					view.printJoinInfo();
				}
				String passwd = view.inputPasswd();
				String name = view.inputName();

				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMid(mid);
				memberDTO.setPasswd(passwd);
				memberDTO.setName(name);
				boolean flag = memberDAO.insert(memberDTO);
				if(flag) {
					view.printFunc01();
				}
				else {
					view.printFunc02();
				}
			}
			else if(command == 2) {
				String mid = view.inputMid();
				String passwd = view.inputPasswd();

				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setCondition("LOGIN");
				memberDTO.setMid(mid);
				memberDTO.setPasswd(passwd);
				memberDTO = memberDAO.selectOne(memberDTO);
				if(memberDTO == null) {
					// 로그인 실패
					view.printFunc02();
				}
				else {
					// 로그인 성공
					///// userInfo.setMid(memberDTO.getMid());
					/////  ▶ 이렇게 작성하면 NPE 발생!!!!!
					userInfo = memberDTO;
					userInfo.setPasswd(null);
					view.printFunc01();
				}
			}
			else if(command == 3) {
				userInfo = null;
				view.printFunc01();
			}
			else if(command == 4) {
				String name = view.inputName();

				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMid(userInfo.getMid());
				memberDTO.setName(name);
				boolean flag = memberDAO.update(memberDTO);
				if(flag) {
					userInfo = null; // 로그아웃 처리
					view.printFunc01();
				}
				else {
					view.printFunc02();
				}
			}
			else if(command == 5) {
				String passwd = view.inputPasswd(); // 비번을 입력받아서

				// 현재 로그인한 사람 + 새로 입력받은 비번 >> 올바른지 체크 == 재로그인
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setCondition("LOGIN");
				memberDTO.setMid(userInfo.getMid()); // 현재 로그인한 사람
				memberDTO.setPasswd(passwd); // 새로 입력받은 비번
				memberDTO = memberDAO.selectOne(memberDTO);

				if(memberDTO == null) {
					view.printFunc02();
					continue;
				}

				boolean flag = memberDAO.delete(memberDTO);
				if(flag) {
					userInfo = null; // 로그아웃 처리
					view.printFunc01();
				}
				else {
					view.printFunc02();
				}
			}
			else if(command == 6) {
				// 글 작성

				String title = view.getTitle();
				String content = view.getContent();

				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setTitle(title);
				boardDTO.setContent(content);
				boardDTO.setMid(userInfo.getMid()); // C의 무게 ★
				boolean flag = boardDAO.insert(boardDTO);
				if(flag) {
					view.printFunc01();
				}
				else {
					view.printFunc02();
				}
			}
			else if(command == 7) {
				// 글 전체출력

				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setCondition("ALL");
				view.printBoardDatas(boardDAO.selectAll(boardDTO));
			}
			else if(command == 8) {
				// 글 상세보기

				int bid = view.inputBoardNum();

				// 글 출력전에, 조회수++
				BoardDTO boardDTO = new BoardDTO();		
				boardDTO.setCondition("UPDATE_BCOUNT");
				boardDTO.setBid(bid);
				boardDAO.update(boardDTO);
				
				boardDTO = new BoardDTO();		
				boardDTO.setBid(bid);
				BoardDTO data = boardDAO.selectOne(boardDTO);
				if(data!=null) {
					command = view.printBoardData(data, userInfo);
					
				}
				else {
					view.printNoBoardData();
					continue;
				}

				// 제목 변경
				if (command == 12) {
					String title = view.getTitle();
					
					boardDTO = new BoardDTO();
					boardDTO.setCondition("UPDATE_TITLE");
					boardDTO.setTitle(title);
					boardDTO.setBid(data.getBid()); // 지금 화면에 출력한 글의 bid
					
					boolean flag = boardDAO.update(boardDTO);
					if(flag) {
						view.printFunc01();
					}
					else {
						view.printFunc02();
					}
						
				} 
				// 내용 변경
				else if (command == 13) {
					String content = view.getContent();

					boardDTO = new BoardDTO();
					boardDTO.setCondition("UPDATE_CONTENT");
					boardDTO.setContent(content);
					boardDTO.setBid(data.getBid());
					
					boolean flag = boardDAO.update(boardDTO);
					if(flag) {
						view.printFunc01();
					}
					else {
						view.printFunc02();
					}
				} 
				// 내용 삭제
				else if (command == 14) {
					boardDTO = new BoardDTO();
					boardDTO.setBid(data.getBid());
					boolean flag = this.boardDAO.delete(boardDTO);
					if(flag) {
						view.printFunc01();
					}
					else {
						view.printFunc02();
					}
				}
//				else if (command == 15) {
//
//				}
			}
			else if(command == 9) {
				// 제목으로 글 검색

				String title = view.getTitle();

				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setCondition("TITLE");
				boardDTO.setTitle(title);
				view.printBoardDatas(boardDAO.selectAll(boardDTO));
			}
			else if(command == 10) {
				// 작성자로 글 검색

				String name = view.inputName();

				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setCondition("NAME");
				boardDTO.setWriter(name);
				view.printBoardDatas(boardDAO.selectAll(boardDTO));
			}
			else if(command == 11) {
				// 조회수 순서대로 글 전체출력
				
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setCondition("BCOUNT");
				view.printBoardDatas(boardDAO.selectAll(boardDTO));
			}
		}
	}
}
