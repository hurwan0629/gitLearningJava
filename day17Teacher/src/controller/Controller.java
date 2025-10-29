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
				
				// 회원가입시 받을 멤버 아이디 만들기
				String memberId;
				while(true) {
					// 클라이언트 뷰로부터 멤버 id 받아보기
					memberId = client.getMemberId();
					
					// 데이터 포장지 만들어서 넣을거 넣고
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setCondition("JOIN");
					memberDTO.setMemberId(memberId);
					
					// 중복 없으면 null나오게 만들어서 data에 받기
					MemberDTO data = mDAO.selectOne(memberDTO);
					if(data == null) { 
						break;
					}
					// 다시 적으라고 메세지 보내기
					client.printInfo();
				}
				// 아이디 제대로 받았으면 비번과 이름 받아서 회원 새로 만들어서 추가하기
				String memberPasswd = client.getMemberPasswd00();
				String name = client.getMemberName();
				
				// DTO에 넣어서 보내기
				MemberDTO mDTO = new MemberDTO();
				mDTO.setMemberId(memberId);
				mDTO.setMemberPasswd(memberPasswd);
				mDTO.setMemberName(name);
				
				// 추가 성공하면 flag에 성공했는지 확인해서 성공 유무 밑에서 출력해주기
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
				
				// 아이디와 패스워드 받기
				String memberId = client.getMemberId();
				String memberPasswd = client.getMemberPasswd00();
				
				// LOGIN조건으로 아이디와 비번 담아서 존재하는지 확인하기(selectOne)
				MemberDTO mDTO = new MemberDTO();
				mDTO.setCondition("LOGIN");
				mDTO.setMemberId(memberId);
				mDTO.setMemberPasswd(memberPasswd);
				MemberDTO member = mDAO.selectOne(mDTO);

				// 만약에 존재한다면 (-> not null) 로그인 성공
				// -> userInfo에 selectOne한 값 넣어놓기
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
				
				// 받는 조건 ALL로 설정해서 물건 전부 받아오기
				ProductDTO productDTO = new ProductDTO();
				productDTO.setCondition("ALL");
				// datas에 물품 전부 보관하기
				ArrayList<ProductDTO> datas = pDAO.selectAll(productDTO);

				// datas 전부 출력
				client.printProductDatas(datas);
			}
			else if(command == 4) {
				// 상품검색
				
				// 키워드 사용자한테 받기
				String keyword = common.getProductName();

				// DTO만들어서 키워드와 조건 담아서 존재하는지 확인하기
				ProductDTO productDTO = new ProductDTO();
				productDTO.setCondition("SEARCH");
				productDTO.setProductName(keyword);
				ArrayList<ProductDTO> datas = pDAO.selectAll(productDTO);
				// 있으면 출력 없으면 비어있다고 client에서 처리해주기
				client.printProductDatas(datas);
			}
			else if(command == 5) {
				// 로그아웃
				// 그냥 쿠키 다 지워버리기
				userInfo = null;
				common.func01();
			}
			else if(command == 6) {
				// 회원탈퇴

				// pw 받아오기 ( 확인용 )
				String passwd = client.getMemberPasswd01();
				// 저장해서 아이디와 비번 함께 환인하러 전송하기
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMemberId(userInfo.getMemberId());
				memberDTO.setMemberPasswd(passwd);
				// 데이터 존재하나 확인하기
				MemberDTO data = mDAO.selectOne(memberDTO);
				
				// 만약에 없으면 실패사인 보내주고 메뉴창으로 보내기
				if(data == null) {
					common.func02();
					continue;
				}

				// 만약에 제대로 아이디x비번 이 맞다면
				// 아이디 mDAO에 보내서 삭제시켜주기
				memberDTO = new MemberDTO();
				memberDTO.setMemberId(userInfo.getMemberId());
				boolean flag = mDAO.delete(memberDTO);

				if(flag) {
					// 로그아웃 시키기
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
			// 상품구매
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
			// 상품추가
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
			// 상품삭제
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
				
				// 상품 정보 전부 가져오기
				ProductDTO productDTO = new ProductDTO();
				productDTO.setCondition("ALL");
				ArrayList<ProductDTO> datas = pDAO.selectAll(productDTO);
				int num = client.getProductNum(datas);
				int count = admin.getCount();
				
				// 상품 추가하기(update -> 기존에 있던 상품의 재고 추가)
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
