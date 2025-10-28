package controller;

import java.util.ArrayList;

import model.dao.MemberDAO;
import model.dao.ProductDAO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;
import view.AdminView;
import view.ClientView;
import view.View;

public class Controller {
	private MemberDAO mDAO;
	private ProductDAO pDAO;

	private View common;
	private ClientView client;
	private AdminView admin;

	private MemberDTO userInfo; // 로그인 여부(쿠키,세션,로컬 스토리지 등)
	public Controller() {
		this.mDAO = new MemberDAO();
		this.pDAO = new ProductDAO();

		this.common = new View();
		this.client = new ClientView();
		this.admin = new AdminView();

		this.userInfo = null; // 로그인을 안한 상태
	}
	public void startApp() {
		int PK = 1003; // 상품의 PK
		while(true) {
			if(userInfo == null) {
				common.printMainMenu();
			}
			else {
				if(userInfo.getMemberId().equals("admin")) {
					// 관리자 모드
					admin.printLoginMenu(userInfo);
				}
				else {
					common.printLoginMenu(userInfo);
				}
			}
			int command = common.getMenuNum(); 
			if(command == 0) {
				common.printTurnOff();
				break;
			}
			else if(command == 1) {
				// 회원가입

				String memberId;
				while(true) {
					memberId = client.getMemberId();

					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setCondition("JOIN");
					memberDTO.setMemberId(memberId);
					MemberDTO data = mDAO.selectOne(memberDTO);
					if(data == null) { 
						break;
					}
					client.printInfo();
				}
				String memberPasswd = client.getMemberPasswd00();
				String name = client.getMemberName();

				MemberDTO mDTO = new MemberDTO();
				mDTO.setMemberId(memberId);
				mDTO.setMemberPasswd(memberPasswd);
				mDTO.setMemberName(name);
				boolean flag = mDAO.insert(mDTO);

				if(flag) {
					common.func01();
				}
				else {
					common.func02();
				}
			}
			else if(command == 2) {
				// 로그인

				String memberId = client.getMemberId();
				String memberPasswd = client.getMemberPasswd00();

				MemberDTO mDTO = new MemberDTO();
				mDTO.setCondition("LOGIN");
				mDTO.setMemberId(memberId);
				mDTO.setMemberPasswd(memberPasswd);
				MemberDTO member = mDAO.selectOne(mDTO);

				if(member != null) {
					userInfo = member;
					common.func01();
				}
				else {
					common.func02();
				}
			}
			else if(command == 3) {
				// 상품목록출력

				ProductDTO productDTO = new ProductDTO();
				productDTO.setCondition("ALL");
				ArrayList<ProductDTO> datas = pDAO.selectAll(productDTO);

				client.printProductDatas(datas);
			}
			else if(command == 4) {
				// 상품검색

				String keyword = common.getProductName();

				ProductDTO productDTO = new ProductDTO();
				productDTO.setCondition("SEARCH");
				productDTO.setProductName(keyword);
				ArrayList<ProductDTO> datas = pDAO.selectAll(productDTO);

				client.printProductDatas(datas);
			}
			else if(command == 5) {
				// 로그아웃

				userInfo = null;
				common.func01();
			}
			else if(command == 6) {
				// 회원탈퇴

				String passwd = client.getMemberPasswd01();
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMemberId(userInfo.getMemberId());
				memberDTO.setMemberPasswd(passwd);
				MemberDTO data = mDAO.selectOne(memberDTO);

				if(data == null) {
					common.func02();
					continue;
				}

				memberDTO = new MemberDTO();
				memberDTO.setMemberId(userInfo.getMemberId());
				boolean flag = mDAO.delete(memberDTO);

				if(flag) {
					common.func01();
					common.func03();
					userInfo = null; // 로그아웃
				}
				else {
					common.func02();
				}	
			}
			else if(command == 7) {
				// 비밀번호 변경

				String passwd = client.getMemberPasswd01();
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMemberId(userInfo.getMemberId());
				memberDTO.setMemberPasswd(passwd);
				MemberDTO data = mDAO.selectOne(memberDTO);

				if(data == null) {
					common.func02();
					continue;
				}

				passwd = client.getMemberPasswd02();
				memberDTO = new MemberDTO();
				memberDTO.setMemberId(userInfo.getMemberId());
				memberDTO.setMemberPasswd(passwd);
				boolean flag = mDAO.update(memberDTO);

				if(flag) {
					common.func01();
					common.func03();
					userInfo = null; // 로그아웃
				}
				else {
					common.func02();
				}	
			}
			else if(command == 8) {
				// 상품구매

				ProductDTO productDTO = new ProductDTO();
				productDTO.setCondition("ALL");
				ArrayList<ProductDTO> datas = pDAO.selectAll(productDTO);
				client.printProductDatas(datas);
				if(datas.isEmpty()) {
					continue;
				}

				int num = client.getProductNum(datas);
				productDTO = new ProductDTO();
				productDTO.setProductId(num);
				ProductDTO data = pDAO.selectOne(productDTO);
				client.printProductData(data);

				int count = client.getCount(data);

				productDTO = new ProductDTO();
				productDTO.setCondition("PAY");
				productDTO.setProductId(num);
				productDTO.setCount(count);
				boolean flag = pDAO.update(productDTO);

				if(flag) {
					common.func01();
				}
				else {
					common.func02();
				}	
			}
			else if(command == 9) {
				// 상품추가
				
				String name = admin.getProductName();
				int price = admin.getProductPrice();
				int count = admin.getCount();
				
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductId(PK++);
				productDTO.setProductName(name);
				productDTO.setPrice(price);
				productDTO.setCount(count);
				boolean flag = pDAO.insert(productDTO);
				
				if(flag) {
					common.func01();
				}
				else {
					common.func02();
				}
			}
			else if(command == 10) {
				// 상품삭제
				
				ProductDTO productDTO = new ProductDTO();
				productDTO.setCondition("ALL");
				ArrayList<ProductDTO> datas = pDAO.selectAll(productDTO);
				int num = client.getProductNum(datas);
				
				productDTO = new ProductDTO();
				productDTO.setProductId(num);
				boolean flag = pDAO.delete(productDTO);
				
				if(flag) {
					common.func01();
				}
				else {
					common.func02();
				}
			}
			else if(command == 11) {
				// 상품재고추가
				
				ProductDTO productDTO = new ProductDTO();
				productDTO.setCondition("ALL");
				ArrayList<ProductDTO> datas = pDAO.selectAll(productDTO);
				int num = client.getProductNum(datas);
				int count = admin.getCount();
				
				productDTO = new ProductDTO();
				productDTO.setCondition("ADD");
				productDTO.setProductId(num);
				productDTO.setCount(count);
				boolean flag = pDAO.update(productDTO);
				
				if(flag) {
					common.func01();
				}
				else {
					common.func02();
				}
			}
		}
	}
}
