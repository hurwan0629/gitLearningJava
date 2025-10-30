package controller;
import java.util.ArrayList;

import model.NewsDAO;
import model.NewsDTO;
import view.NewsView;

public class NewsController {
	private NewsDAO newsDAO;
	private NewsView newsView;

	public NewsController() {
		this.newsDAO = new NewsDAO(); // 데이터 처리 객체
		this.newsView = new NewsView(); // 사용자 입출력 객체
	}
	
	public void startApp() {
		int PK = 1006; //ID PK값 
		while (true) {
			int menu = newsView.showMenu();
			//사용자 메뉴 출력
			
			if (menu == 0) { //0번선택 프로그램 종료
				System.out.println("프로그램 종료!");
				break;
			}
			
			else if (menu == 1) { //1번 선택 뉴스추가하기
				//뉴스 추가를 하려면 어떤 뉴스인지 V가 입력받아야해
				//V야 어떤 뉴스 받을건지 받아와라~ 타이틀, 언론사, 날짜, url 받아와
				newsView.getNewNews();
				// View에서 검증된 값 받아오기
		        String title = newsView.inputTitle();
		        String press = newsView.inputPress();
		        String url = newsView.inputUrl();
		        String date = newsView.inputDate();
		        String summary = newsView.inputSummary();
		        
		        NewsDTO newsDTO = new NewsDTO();
		        newsDTO.setTitle(title);
		        newsDTO.setPress(press);
		        newsDTO.setUrl(url);
		        newsDTO.setDate(date);
		        newsDTO.setSummary(summary);		        
				newsDTO.setId(PK);  
				//newsDTO에 담아놨으니까 이걸로 newsDAO에 추가해라 모델아~
				boolean result = newsDAO.insert(newsDTO);
				if(result) {
				PK++;
				}
				//그리고 결과 알려줘
				newsView.showInsertResult(result);//결과값에 따러 출력안내 해				
			}
			
			else if (menu == 2) { //2번 선택 뉴스전체출력 
				//V가 2넘겨주면
				NewsDTO newsDTO = new NewsDTO(); //포장지 준비 
				ArrayList<NewsDTO> datas = newsDAO.selectAll(newsDTO); //포장에 해당하는거 다담아와라 M
				newsView.showAllNews(datas);//datas배열에 담겨있는거 전부 출력해줘 V
			}
			else if (menu == 3) { //3번 선택 뉴스 1개 출력
				NewsDTO newsDTO = new NewsDTO(); //포장지준비
				ArrayList<NewsDTO> datas = newsDAO.selectAll(newsDTO);
				newsView.showAllNews(datas); //전체 보여주고 나서
				int userPK = newsView.inputNewsId(); //V-PK값 입력받아서 넘겨주면 내가 담아둘게

				NewsDTO pkDTO = new NewsDTO();
				pkDTO.setId(userPK); //입력받은 PK값 담아서 DB로 보내자

				NewsDTO data = newsDAO.selectOne(pkDTO); //DTO에 담긴 PK값 기준 정보 찾아와
				newsView.showOneNews(data); //이제 이거 보여줘 V
			}
			
			else if (menu == 4) { //4번 선택 뉴스 업데이트
				NewsDTO newsDTO = new NewsDTO();
				ArrayList<NewsDTO> datas = newsDAO.selectAll(newsDTO);
				newsView.showAllNews(datas); //전체 보여주고 나서
				
				NewsDTO pkDTO = new NewsDTO();
				NewsDTO data = new NewsDTO();
				int userPK;
				while (true) {
					userPK = newsView.inputNewsId(); // ID 입력
					pkDTO.setId(userPK);
					data = newsDAO.selectOne(pkDTO); // DAO에서 조회
					 if (data != null) {
				            break; // 존재하면 반복 종료
				     } 
					 else {
				            System.out.println("❌ 해당 뉴스 번호가 존재하지 않습니다. 다시 입력해주세요.");
				     }
				}
				String summary = newsView.updateSummary(); // View에서 summary 입력
				data.setSummary(summary); // DTO에 설정
				    
				boolean result = newsDAO.update(data);				
				//그거 db로 보내고 업데이트 실패유무 알려줘
				//결과에 따라 출력해
				newsView.showUpdateResult(result);
			}
			
			else if (menu == 5) { //5번 선택 뉴스 삭제
				NewsDTO newsDTO = new NewsDTO();
				ArrayList<NewsDTO> datas = newsDAO.selectAll(newsDTO);
				newsView.showAllNews(datas);
				
				NewsDTO pkDTO = new NewsDTO();			
				NewsDTO data = new NewsDTO();			
				int userPK;
				while (true) {
					userPK = newsView.inputNewsId(); // ID 입력
					pkDTO.setId(userPK);
					data = newsDAO.selectOne(pkDTO); // DAO에서 조회
					 if (data != null) {
				       break; // 존재하면 반복 종료
				     } 
					 else {
				       System.out.println("❌ 해당 뉴스 번호가 존재하지 않습니다. 다시 입력해주세요.");
				     }					 
				}
				data.setId(userPK);
				boolean result = newsDAO.delete(data);
				newsView.showDeleteResult(result);
			}
			else {
				System.out.println("⚠️ 잘못된 입력입니다!");
			}
		}
	}
}