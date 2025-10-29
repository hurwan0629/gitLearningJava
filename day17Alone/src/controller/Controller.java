package controller;


import model.dao.MemberDAO;
import model.dao.ProductDAO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;
import view.AdminView;
import view.ClientView;
import view.LogoutView;
import view.View;

public class Controller {
	MemberDAO mDAO;
	ProductDAO pDAO;
	
	AdminView admin;
	ClientView client;
	LogoutView logout;
	View common;
	
	MemberDTO userInfo;
	
	
	public Controller(){
		this.mDAO = new MemberDAO();
		this.pDAO = new ProductDAO();
		
		this.admin = new AdminView();
		this.client = new ClientView();
		this.logout = new LogoutView();
		this.common = new View();
		
		this.userInfo = new MemberDTO();
		this.userInfo.setMemberRole("LOGOUT");
	}
	
	public void startApp() {
		
		
		while(true) {
			this.common.showMenu();
			
			int command=0;
			// 로그아웃/로그인/관리자 중 하나
			// 로그아웃
			if(this.userInfo.getMemberRole().equals("LOGOUT")) {
				this.logout.showMenu();
				command = this.logout.getCommand();
			}
			else if(this.userInfo.getMemberRole().equals("CLIENT")) {
				this.client.showMenu(userInfo);
				command = this.client.getCommand();
			}
			else if(this.userInfo.getMemberRole().equals("ADMIN")) {
				this.admin.showMenu(userInfo);
				command = this.admin.getCommand();
			}
			 
//			System.out.println(" controller.Controller [로그1] command: "+command);
			// 종료하기
			if (command == 0) {
				this.common.showTurnOff();
				break;
			}
			// 상품목록 출력
			else if (command == 1) {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductCondition("ALL");
				
				this.common.showProducts(pDAO.selectAll(productDTO));
			}
			// 상품 검색
			else if (command == 2) {
				String keyword = this.common.getKeyword();
				
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductKeyword(keyword);
				productDTO.setProductCondition("KEYWORD");
				
				this.common.showProducts(pDAO.selectAll(productDTO));
			}
			/// 로그아웃
			// 회원가입
			else if (this.userInfo.getMemberRole().equals("LOGOUT") && command == 3) {
				MemberDTO memberDTO = new MemberDTO();
				while(true) {
					String memberId = this.logout.getMemberId();
					memberDTO.setMemberId(memberId);
					memberDTO.setMemberCondtion("SAMEID");
					
					if(mDAO.selectOne(memberDTO) == null) {
						// 아이디 겹치지 않음 
						// -> 비밀번호 받으러 ㄱㄱ
						break;
					}
					this.logout.showIdIsOverride();
				}
				
				String memberPw = this.logout.getMemberPw();
				String memberName = this.logout.getMemberName();
				
				memberDTO.setMemberPw(memberPw);
				memberDTO.setMemberName(memberName);
				
				if(mDAO.insert(memberDTO)) {
					this.logout.showSignInSuccess();
				}
				else {
					this.logout.showSignInFail();
				}
			}
			// 로그인
			else if (this.userInfo.getMemberRole().equals("LOGOUT")&& command == 4) {
				// 아이디 받고
				String memberId = this.logout.getMemberId();
				// 비번 받고
				String memberPw = this.logout.getMemberPw();
				
				// 맞으면
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMemberId(memberId);
				memberDTO.setMemberPw(memberPw);
				memberDTO.setMemberCondtion("LOGIN");
				
				MemberDTO mDTO = mDAO.selectOne(memberDTO);
				// 성공출력
				if(mDTO != null) {
					this.userInfo = mDTO;
					if(mDTO.getMemberId().equals("admin")) {
						this.userInfo.setMemberRole("ADMIN");
					}
					else {
						this.userInfo.setMemberRole("CLIENT");
					}
					this.logout.showLoginSuccess();
				}
				// 아님 실패
				else {
					this.logout.showLoginFail();
				}
			}
			/// 로그인
			// 회원탈퇴
			else if (command == 3) {
				while(true)

				client.getMemberPw(this.userInfo);
			}
			// 로그아웃
			else if((this.userInfo.getMemberRole().equals("CLIENT")
					||this.userInfo.getMemberRole().equals("ADMIN"))&&command == 4) {
				this.userInfo=new MemberDTO();
				this.userInfo.setMemberRole("LOGOUT");;
				client.showLogoutSuccess();
			}
			// 비번 변경
			else if (command == 7) {

			}
			// 상품구매
			else if (command == 8) {

			}
			/// 관리자
			// 상품 추가
			else if (command == 9) {

			}
			// 상품 삭제
			else if (command == 10) {

			}
			// 상품 재고 추가
			else if (command == 11) {

			}
		}
	}
}
