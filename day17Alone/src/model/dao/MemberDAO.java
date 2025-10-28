package model.dao;

import java.util.ArrayList;

import model.dto.MemberDTO;

public class MemberDAO {
	private ArrayList<MemberDTO> datas;
	
	public MemberDAO(){
		this.datas = new ArrayList<>();
		
		MemberDTO sample01 = new MemberDTO();
		sample01.setMemberId("admin");
		sample01.setMemberPw("1234");
		sample01.setMemberName("관리자");
		
		MemberDTO sample02 = new MemberDTO();
		sample02.setMemberId("user01");
		sample02.setMemberPw("1234");
		sample02.setMemberName("유저01");
	
		datas.add(sample01);
		datas.add(sample02);
	}
	
	public ArrayList<MemberDTO> selectAll(MemberDTO memberDTO) {
		return null;
	}

	public MemberDTO selectOne(MemberDTO memberDTO) {
		MemberDTO data=null;
		
		if(memberDTO.getMemberCondtion().equals("SAMEID")) {
			// 같은 아이디가 있는지 확인 
			// 없으면 null 출력
			for(int i=0;i<datas.size();i++) {
				if(this.datas.get(i).getMemberId().equals(memberDTO.getMemberId())) {
					data = new MemberDTO();
					data.setMemberId(memberDTO.getMemberId());
					break;
				}
			}
		}
		else if(memberDTO.getMemberCondtion().equals("LOGIN")) {
			// 제대로 로그인 했는지 확인
			for(int i=0;i<datas.size();i++) {
				// id가 같은지
				if(this.datas.get(i).getMemberId().equals(memberDTO.getMemberId())) {
					if(this.datas.get(i).getMemberPw().equals(memberDTO.getMemberPw())) {
						// 관리자인지 일반인인지 확인용 id 만들기
						// 로그인 성공 -> 환영합니다 oo 님 -> name 넣어서 주기
						data = new MemberDTO();
						data.setMemberId(this.datas.get(i).getMemberId());
						data.setMemberName(this.datas.get(i).getMemberName());
						break;
					}
				}
			}
		}
		return data;
	}

	
	public boolean insert(MemberDTO memberDTO) {
		try {
			datas.add(memberDTO);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(MemberDTO memberDTO) {
		return false;
	}

	public boolean delete(MemberDTO memberDTO) {
		return false;
	}

}
