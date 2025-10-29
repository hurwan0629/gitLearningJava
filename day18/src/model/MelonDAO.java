package model;

import java.util.ArrayList;

public class MelonDAO {
	private ArrayList<MelonDTO> datas;
	public MelonDAO() {
		this.datas = new ArrayList<>();
		
		// 샘플 데이터 추가
		Crawling.makeSample(this.datas);
	}
	
	public ArrayList<MelonDTO> selectAll(MelonDTO melonDTO) {
		// datas 복사해서 리턴
		return this.datas;
	}

	private MelonDTO selectOne(MelonDTO melonDTO) {
		return null;
	}

	private boolean insert(MelonDTO melonDTO) {
		return false;
	}

	private boolean update(MelonDTO melonDTO) {
		return false;
	}

	private boolean delete(MelonDTO melonDTO) {
		return false;
	}

}
