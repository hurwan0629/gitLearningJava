package controller;

import java.util.ArrayList;

import model.MovieDAO;
import model.MovieDTO;
import view.View;

public class Controller {
	private MovieDAO mDAO;
	private View common;
	
	public Controller() {
		this.common = new View();
		this.mDAO = new MovieDAO();
	}
	public void startApp() {
		int PK = 106; // 영화 번호 PK
		while(true) {			
			common.printMainMenu(); // 메뉴 출력
			int command = common.getMenuNum(); // 메뉴 번호 입력받기
						
			if(command == 0) { // 0이 입력되면
				common.printTurnOff(); // 프로그램 종료 메세지
				break; // 프로그램 종료
			}
			else if (command == 1) {
				// 전체 출력
				MovieDTO movieDTO = new MovieDTO(); // 영화 타입 객체 생성
				ArrayList<MovieDTO> datas = mDAO.selectAll(movieDTO); 
				// 🔺 selectAll 데이터 받아오기
				
				common.printDatas(datas); // selectAll한 영화 출력
			}			
			else if(command == 2) {
				int titleId = common.getMovieNum();// 출력하고자 하는 영화 PK 입력
				// 하나만 출력
				MovieDTO movieDTO = new MovieDTO(); // 영화 객체 생성
				movieDTO.setCondition("ONE");
				movieDTO.setTitleId(titleId);
				MovieDTO data = mDAO.selectOne(movieDTO); // 영화 찾기
				common.printData(data);
			}
			else if(command == 3) {
				// 영화 추가
				String title;
				while(true) {
					title = common.getMovieTitle(); // 영화제목 입력받기
					// DB에 정보 넘겨서 저장하기
					MovieDTO movieDTO = new MovieDTO(); // 영화 객체 생성
					movieDTO.setCondition("CHECK");
					movieDTO.setTitle(title); // 입력한 제목 넘겨주기
					MovieDTO data = mDAO.selectOne(movieDTO); // db에서 selectOne 할래
					if(data == null) { // 중복되지 않은 영화제목이라면
						break;
					}
					// 중복 제목일 경우
					common.duplicationMovieTitle(); // 다시 입력					
				}
				
				String genre = common.getName(); // 영화 장르 입력받기
				MovieDTO movieDTO = new MovieDTO();
				movieDTO.setTitleId(PK++); // PK값 넘겨주기
				movieDTO.setTitle(title); // 입력한 제목 넘겨주기
				movieDTO.setGenre(genre); // 입력한 장르 넘겨주기
				boolean flag = mDAO.insert(movieDTO); 
				
				if(flag) {
					common.func01();
				}
				else {
					common.func02();
				}
			}
			else if(command == 4) {
				// 영화 삭제
				MovieDTO movieDTO = new MovieDTO();
				ArrayList<MovieDTO> datas = mDAO.selectAll(movieDTO);
				
				int titleId = common.getDeleteMovieNum(datas); // 삭제할 영화번호 PK 입력받기
				
				movieDTO = new MovieDTO();
				movieDTO.setTitleId(titleId); // 입력한 값 넘기기
				boolean flag = mDAO.delete(movieDTO); // 삭제
				
				// delete 성공 or 실패
				if(flag) {
					common.func01();
				} else {
					common.func02();
				}
			}
			else if(command == 5) {
				// 영화 수정
				
				int num = common.getChoiceMune(); // 번호 입력
				// 제목 수정 또는 출연진 수정
				// 어떤 방식으로 수정을 할 것 인지
				boolean flag = false;
				
				if(num == 1) { // 제목 변경
					
					// 변경하려는 영화 번호 PK 입력 
					num = common.getMovieNum(); // num 재활용
										
					// 해당 번호를 가진 데이터를 뽑아서 제목 변경
					String title = common.getMovieTitle();
					MovieDTO movieDTO = new MovieDTO();
					movieDTO.setTitleId(num); // PK 넘기기
					movieDTO.setCondition("TITLE");
					movieDTO.setTitle(title); // 새로 입력한 제목 넘기기
					flag = mDAO.update(movieDTO);
				}
				else if(num == 2) {
					// 변경하려는 영화 번호 PK 입력 
					num = common.getMovieNum(); // num 재활용
					
					// 변경하려는 영화 장르 입력
					String genre = common.getName(); 
					
					// 해당 이름를 가진 데이터를 뽑아서 이름 수정
					MovieDTO movieDTO = new MovieDTO();
					movieDTO.setTitleId(num); // PK 넘기기
					movieDTO.setCondition("NAME");
					movieDTO.setGenre(genre);
					flag = mDAO.update(movieDTO);
				}
				
				// 성공 or 실패
				if(flag) {
					common.func01();
				} else {
					common.func02();
				}
			}
		}
	}
}
