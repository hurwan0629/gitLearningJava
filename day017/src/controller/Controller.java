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
	
	private MemberDTO userInfo;	// 로그인 여부(쿠키, 센선, 로컬스토리지 등)
	
	public Controller() {
		// 회원 DB드라이버
		this.mDAO = new MemberDAO();
		// 상품 DB드라이버
		this.pDAO = new ProductDAO();
		
		// 공통 사용 페이지
		this.common = new View();
		// 사용자 전용 페이지
		this.client = new ClientView();
		// 점주전용 전용 페이지
		this.admin = new AdminView();
		
		this.userInfo = null;	// 로그인 안한 상태
	}
	
	public void startApp() {
		int PK = 1003;
		while(true) {
			
			// 비로그인 상태면
			if(userInfo == null) {
				// 전체메뉴 보여주기
				this.common.printMenu();
			}
			// 로그인 상태면
			else {
				
				// 관리자가 로그인 했을때
				if(userInfo.getMemberId().equals("admin")) {
					// 관리자모드
					admin.printLoginMenu(userInfo);
				}
				else {
					this.common.printLoginMenu(userInfo);
				}
			}
			
			
			// 메뉴버튼 입력받기
			int command = this.common.getMenuNum();
			
			if(command == 0) {
				// 종료화면 보여주기
				this.common.printTurnOff();
				break;
			}
			// 회원가입
			else if (command == 1) {
				// 입력받기
				// 아이디
				String memberId = "";
				while(true) {
					memberId = this.client.getMemberId();

					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setMemberCondition("JOIN");
					memberDTO.setMemberId(memberId);
					
					MemberDTO data = mDAO.selectOne(memberDTO);
					// 중복되지 않은 아이디라면
					if(data==null) {
						break;
					}
					// 중복아이디라고 안내문 출력해주기
					client.printInfo();
				}
				
				// 비밀번호
				String memberPasswd = this.client.getMemberPasswd00();
				// 이름 
				String name = this.client.getMemberName();
				
				// DB에 추가
				MemberDTO mDTO = new MemberDTO();
				mDTO.setMemberId(memberId);
				mDTO.setMemberPasswd(memberPasswd);
				mDTO.setMemberName(name);
				boolean flag = this.mDAO.insert(mDTO);
				
				// 성공 or 실패
				if(flag) {
					this.common.func01();
				}
				else {
					this.common.func02();
				}
			} 
			// 로그인
			else if (command == 2) {
				// 입력받기
				// 아이디
				String memberId = this.client.getMemberId();
				// 비밀번호
				String memberPasswd = this.client.getMemberPasswd00();
				
				MemberDTO mDTO = new MemberDTO();
				mDTO.setMemberCondition("LOGIN");
				mDTO.setMemberId(memberId);
				mDTO.setMemberPasswd(memberPasswd);
				
				// 찾으면 값, 못찾으면 null
				MemberDTO member = this.mDAO.selectOne(mDTO);
				
				// 백단에서 pw를 막기도 함
				//member.setMemberPasswd(null);
				
				// 성공 or 실패
				if(member != null) {
					// 현재 로그인 성공한 사람의 정보
					this.userInfo = member; 
							
					this.common.loginSuccess(member);
					this.common.func01();
				}
				else {
					this.common.loginFail();
					this.common.func02();
				}
			} 
			// 상품목록 출력
			else if (command == 3) {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductCondition("ALL");
				
				// DB한테서 상품전부 받아오기
				ArrayList<ProductDTO> datas = this.pDAO.selectAll(productDTO);
				
				// 받은거 보여주기
				this.client.printProductDatas(datas);
			} 
			// 상품 검색
			else if (command == 4) {
				// 상품검색
				String keyword = this.common.getProductName();
				
				// DB에서 상품 받아오기
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductKeyword(keyword);
				productDTO.setProductCondition("KEYWORD");
				
				// 뿌리기
				ArrayList<ProductDTO> datas = this.pDAO.selectAll(productDTO);
				this.client.printProductDatas(datas);
				
			} 
			// 로그아웃
			else if (command == 5) {	
				this.userInfo = null;
				this.common.func01();
			}
			// 회원탈퇴
			else if(command == 6) {
				// v 로 기존 비밀번호를 확인
				String passwd = this.client.getMemberPasswd01();
				
				MemberDTO memberDTO = new MemberDTO();
				// 지금 로그인한 사람 아이디
				memberDTO.setMemberId(this.userInfo.getMemberId());
				memberDTO.setMemberPasswd(passwd);
				
				MemberDTO data = this.mDAO.selectOne(memberDTO);
				
				// 실패하면 out
				if(data == null) {
					this.common.func02();
					continue;
				}
				
				// 성공하면 계속...
				memberDTO = new MemberDTO();
				memberDTO.setMemberId(this.userInfo.getMemberId());
				// DB에서 해당 정보 삭제
				boolean flag = this.mDAO.delete(memberDTO);
				// 성공 or 삭제
				if(flag) {
					this.common.func01();
					this.common.func03();
					this.userInfo = null;
				}
				else {
					this.common.func02();
				}
			}
			
			// 비밀번호 변경
			else if (command == 7) {
				// 현재 비밀번호 받아오기
				// 너 로그인 제대로 한거 맞아? 확인
				String passwd = this.client.getMemberPasswd01();
				
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMemberId(this.userInfo.getMemberId());
				memberDTO.setMemberPasswd(passwd);
				
				// M한테 맞는 비번인지 알아오기 -> boolean
				MemberDTO data = this.mDAO.selectOne(memberDTO);
				
				// 실패 화면 출력 ( data = null )
				// -> 본인 확인을 다시 진행
				// -> 메뉴 탈출
				if(data == null) {
					this.common.func02();
					continue;
				}
				
				
				// 변경할 비밀번호 입력
				passwd = this.client.getMemberPasswd02();
				
				// M에서 비밀번호 입력
				// 누구를 변경할지
				// 무슨 비밀번호로 변경할지
				memberDTO = new MemberDTO();
				memberDTO.setMemberId(this.userInfo.getMemberId());
				memberDTO.setMemberPasswd(passwd);
				
				boolean flag = this.mDAO.update(memberDTO);
				
				
				
				// 성공 or 실패 출력
				if(flag) {
					// 성공 출력 후
					this.common.func01();
					
					// 로그아웃 화면으로 돌아갑니다 출력
					this.common.func03();
					
					// 로그인 다시하라 시키기
					this.userInfo = null;
				}
				else {
					this.common.func02();
				}
			}
			// 상품 구매
			else if (command == 8) {
				// 화면에 상품 목록이 나옴
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductCondition("ALL");
				
				ArrayList<ProductDTO> datas = this.pDAO.selectAll(productDTO);
				client.printProductDatas(datas);
				if(datas.isEmpty()) {
					continue;
				}
				
				// 사용자가 상품 1개를 선택
				String num = this.client.getProductNum(datas);
				productDTO = new ProductDTO();
				productDTO.setProductId(num);
				
				// 해당 상품의 상세정보가 나옴
				ProductDTO data = this.pDAO.selectOne(productDTO);
				this.client.printProductData(data);
				
				// 몇개 구매할지 결정
				int count = this.client.getCount(data);
				// 구매 시도
				
				productDTO = new ProductDTO();
				// 어떤 상품을
				productDTO.setProductId(num);
				// 몇개를 살지
				productDTO.setProductCount(count);
				// 조건 주기
				productDTO.setProductCondition("PAY");
				
				boolean flag = this.pDAO.update(productDTO);
				// 성공하면 성공화면
				if(flag) {
					this.common.func01();
				}
				// 실패하면 실패화면
				else {
					this.common.func02();
				}
			}
			// 상품 추가
			else if (command == 9) {
				// 관리자로부터 상품이름, 가격, 재곡를 받아오기
				String name = admin.getProductName();
				int price = admin.getProductPrice();
				int count = admin.getCount();
				
				// DB에 정보 넘겨서 저장시키기;
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductId(Integer.toString(PK++));
				productDTO.setProductName(name);
				productDTO.setProductPrice(price);
				productDTO.setProductCount(count);
				
				boolean flag = pDAO.insert(productDTO); 
						
				
				//
				if(flag) {
					common.func01();
				}
				else {
					common.func02();
				}
			}
			// 상품삭제
			else if(command == 10) {
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductCondition("ALL");
				ArrayList<ProductDTO> datas = pDAO.selectAll(productDTO);
				String num = client.getProductNum(datas);
				
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
			// 상품 재고 추가
			else if(command == 11) {
				// 누구를 몇개 추가할지 입력받기
				ProductDTO productDTO = new ProductDTO();
				productDTO.setProductCondition("ALL");
				ArrayList<ProductDTO> datas = pDAO.selectAll(productDTO);
				String num = client.getProductNum(datas);
				int count = admin.getCount();
				
				productDTO = new ProductDTO();
				productDTO.setProductCondition("ADD");
				productDTO.setProductId(num);
				productDTO.setProductCount(count);
				boolean flag = pDAO.update(productDTO);
				if(flag) {
					common.func01();
				}
				else{
					common.func02();
				}
				
			}
		}
	}
}
