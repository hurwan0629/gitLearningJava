package model.dao;

import java.util.ArrayList;

import model.dto.MemberDTO;

public class MemberDAO {
	private ArrayList<MemberDTO> datas; // DB 역할
	public MemberDAO() {
		this.datas = new ArrayList<>();

		MemberDTO data01 = new MemberDTO();
		data01.setMemberId("admin");
		data01.setMemberPasswd("1234");
		data01.setMemberName("관리자");
		this.datas.add(data01);
		MemberDTO data02 = new MemberDTO();
		data02.setMemberId("test");
		data02.setMemberPasswd("1234");
		data02.setMemberName("테스트");
		this.datas.add(data02);
	}

	private ArrayList<MemberDTO> selectAll(MemberDTO memberDTO){ // 코드의 결합도가 낮아짐 ★
		ArrayList<MemberDTO> datas = new ArrayList<>();
		for(int i=0;i<this.datas.size();i++) {
			MemberDTO data = new MemberDTO();
			data.setMemberId(this.datas.get(i).getMemberId());
			data.setMemberName(this.datas.get(i).getMemberName());
			datas.add(data);
		}
		return datas;
	}
	public MemberDTO selectOne(MemberDTO memberDTO) {
		if(memberDTO.getCondition().equals("LOGIN")) {
			for(int i=0;i<this.datas.size();i++) {
				if(this.datas.get(i).getMemberId().equals(memberDTO.getMemberId())) {
					if(this.datas.get(i).getMemberPasswd().equals(memberDTO.getMemberPasswd())) {
						MemberDTO data = new MemberDTO();
						data.setMemberId(this.datas.get(i).getMemberId());
						data.setMemberName(this.datas.get(i).getMemberName());
						return data;
					}
					else {
						System.out.println(" [로그] MemberDAO.selectOne() 해당하는 아이디는 찾았으나, 비밀번호가 올바르지 않습니다.");
						return null;
					}
				}
			}
			System.out.println(" [로그] MemberDAO.selectOne() 해당하는 아이디가 없습니다.");
		}
		else if(memberDTO.getCondition().equals("JOIN")) {
			for(int i=0;i<this.datas.size();i++) {
				if(this.datas.get(i).getMemberId().equals(memberDTO.getMemberId())) {
					MemberDTO data = new MemberDTO();
					data.setMemberId(this.datas.get(i).getMemberId());
					return data;
				}
			}
			System.out.println(" [로그] MemberDAO.selectOne() 해당하는 아이디가 없습니다.");
		}
		return null;
	}

	public boolean insert(MemberDTO memberDTO) {
		try {
			MemberDTO data = new MemberDTO();
			data.setMemberId(memberDTO.getMemberId());
			data.setMemberPasswd(memberDTO.getMemberPasswd());
			data.setMemberName(memberDTO.getMemberName());
			this.datas.add(data);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	public boolean update(MemberDTO memberDTO) {
		for(int i=0;i<this.datas.size();i++) {
			if(this.datas.get(i).getMemberId().equals(memberDTO.getMemberId())) {
				this.datas.get(i).setMemberPasswd(memberDTO.getMemberPasswd());
				return true;
			}
		}
		return false;
	}
	public boolean delete(MemberDTO memberDTO) {
		for(int i=0;i<this.datas.size();i++) {
			if(this.datas.get(i).getMemberId().equals(memberDTO.getMemberId())) {
				this.datas.remove(i);
				return true;
			}
		}
		return false;
	}
}
