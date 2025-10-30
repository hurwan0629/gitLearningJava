package model;

import java.util.ArrayList;


public class NewsDAO {
	private ArrayList<NewsDTO> datas; // 뉴스 데이터 저장소
	
	public NewsDAO() {
		this.datas = new ArrayList<>();
		Crawling.makeSample(this.datas); // 크롤링해서 샘플데이터 추가
	}
	
	// 뉴스 전체 출력
	public ArrayList<NewsDTO> selectAll(NewsDTO newsDTO){
		ArrayList<NewsDTO> datas = new ArrayList<>();
		for(int i=0;i<this.datas.size();i++) {
			NewsDTO data = new NewsDTO();
			data.setDate(this.datas.get(i).getDate());
			data.setId(this.datas.get(i).getId());
			data.setPress(this.datas.get(i).getPress());
			data.setSummary(this.datas.get(i).getSummary());
			data.setTitle(this.datas.get(i).getTitle());
			data.setUrl(this.datas.get(i).getUrl());
			datas.add(data);
		}				
		return datas; // 카피 데이터반출
	}
	
	// 뉴스 상세 검색
	public NewsDTO selectOne(NewsDTO newsDTO) {
		for(NewsDTO news : datas) { // 데이터안에서 뉴스반복해서
			if(news.getId() == newsDTO.getId())  // 입력한 뉴스랑 같다면
			return news; // 그뉴스 반환
		}
		return null; //아니면 null 반환
	}
	
	 // 뉴스 추가하기
	 public boolean insert(NewsDTO news) {
	     if (news == null) return false; // null 방어

	     try { //추가 시 오류 발생 가능성 있음
	         datas.add(news);
	         return true;
	     } 
	     catch (Exception e) {
	         e.printStackTrace(); // 예외 추적
	         return false;
	     }
	   }
	
	// 뉴스 요약 작성하기
	public boolean update(NewsDTO newsDTO) {
		for(int i=0; i<this.datas.size();i++) {
			if (this.datas.get(i).getId() == newsDTO.getId()) { // ID가 일치하는 뉴스 찾아서
				this.datas.get(i).setSummary(newsDTO.getSummary()); // 수정하고			
				return true;
			}					
		}		
		return false;	
		
	}
	
	
	
	// 뉴스 삭제하기
	public boolean delete(NewsDTO newsDTO) {
		for(int i=0; i<datas.size();i++) {
			if (datas.get(i).getId()==newsDTO.getId()) { // ID 일치하면
				datas.remove(i); // 삭제
				return true; // 삭제 성공
			}
		}
		return false; // 해당 ID 없음
	}
	
}
