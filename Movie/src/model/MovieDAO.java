package model;

import java.util.ArrayList;

public class MovieDAO {
	private ArrayList<MovieDTO> datas;
	
	public MovieDAO() {
		this.datas = new ArrayList<>();
		Crawling.makeSample(this.datas);
	}
	
	public ArrayList<MovieDTO> selectAll(MovieDTO movieDTO){ // 전부 출력할 때
		ArrayList<MovieDTO> datas = new ArrayList<>(); // 껍대기 배열 추가
		
		for(int i = 0; i < this.datas.size(); i++) { // 원본 데이터 유지를 위해 데이터 복사
			MovieDTO data = new MovieDTO(); // 데이터 포장
			data.setTitleId(this.datas.get(i).getTitleId());  // 영화 번호데이터
			data.setTitle(this.datas.get(i).getTitle()); // 영화이름 데이터
			data.setGenre(this.datas.get(i).getGenre()); // 영화 장르 데이터
			datas.add(data); // 껍대기 데이터에 삽입
		}
		return datas; // 복사한 데이터 반환
	}
	public MovieDTO selectOne(MovieDTO movieDTO){
		if(movieDTO.getCondition().equals("ONE")) {
			for(int i = 0; i<datas.size(); i++) {
				if(this.datas.get(i).getTitleId() == movieDTO.getTitleId()) { // 영화번호(PK)값 받아와서 같은 번호 일 때
					MovieDTO data = new MovieDTO(); // 데이터 포장
					data.setTitleId(this.datas.get(i).getTitleId());
					data.setTitle(this.datas.get(i).getTitle());
					data.setGenre(this.datas.get(i).getGenre());
					return data; // 데이터를 찾으면 반환
				}
			}
		}
		else if(movieDTO.getCondition().equals("CHECK")) { // 중복검사
			for(int i = 0; i< this.datas.size(); i++) { // 원래 배열에서 찾기위해 반복문
				if(this.datas.get(i).getTitle().equals(movieDTO.getTitle())) { // 영화 이름이 같은 값 찾기
					MovieDTO data = new MovieDTO(); 
					data.setTitle(this.datas.get(i).getTitle());
					return data; // 값을 찾으면 반환하기'
				}
			}
		}
		return null; // 찾지 못하면 null 반환
	}
	
	
	public boolean insert(MovieDTO movieDTO){ // 영화 추가-
		try {
		MovieDTO data = new MovieDTO();
			data.setTitleId(movieDTO.getTitleId());// 영화 번호 추가
			data.setTitle(movieDTO.getTitle()); // 영화 이름 추가
			data.setGenre(movieDTO.getGenre()); // 영화 장르 추가
			datas.add(data); // 기존 배열의 입력받은 정보 추가
		}
		catch(Exception e) {
			return false; // 오류 발생시 false 반환
		}
		return true; // 정상 작동시 true 반환
	}
	
	public boolean update(MovieDTO movieDTO){ 
		if(movieDTO.getCondition().equals("TITLE")) {//만약 제몫 변경일 떄
			for(int i = 0; i<this.datas.size(); i++) {  // 변경할 영화번호 찾기위해 반복문
				if(this.datas.get(i).getTitleId() == movieDTO.getTitleId()) { // 기존에 영화번호가 있으면
					this.datas.get(i).setTitle(movieDTO.getTitle()); // 제목 변경	
					return true; // true 반환
				}
			}
		}
		else if(movieDTO.getCondition().equals("NAME")) { // 장르 변경할 때
			for(int i = 0; i< this.datas.size(); i++) { // 변경할 장르가 있는지 확인하기위해 반복문
				if(this.datas.get(i).getTitleId() == movieDTO.getTitleId()) { // 기존에 데이터의 장르가 있다면
					this.datas.get(i).setGenre(movieDTO.getGenre());
					return true; // true 반환
				}
			}
		}
		return false;
	}
	
	public boolean delete(MovieDTO movieDTO){
		for(int i = 0; i<this.datas.size(); i++){ // 기존 데이터에서 찾기 위해 반복문
			if(this.datas.get(i).getTitleId() == movieDTO.getTitleId()) { //기존 데이터에 삭제하고싶은 데이터가 있는지 확인
				this.datas.remove(i); // 찾은 데이터 삭제
				return true; // 성공시 true 반환
			}
		}
		return false;
	}
}
