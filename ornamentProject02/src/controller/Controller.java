package controller;

import java.util.ArrayList;

import model.crawling.CrawlingProducts;
import model.dao.BagDAO;
import model.dao.MemberDAO;
import model.dao.ProductDAO;
import model.dto.BagDTO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;
import view.View;

public class Controller {
	private ProductDAO productDAO;
	private MemberDAO memberDAO;
	private BagDAO bagDAO;
	private View view;
	
	private MemberDTO userInfo; // 쿠키(로컬, 스토리지, 세션 등) 로그인 여부를 판단 
	
	public Controller() {
		productDAO = new ProductDAO();
		CrawlingProducts.crawlProducts();
		memberDAO = new MemberDAO();
		bagDAO = new BagDAO();
		view = new View();
		
		userInfo = null; // 비로그인 상태
	}
	public void startApp() {
		while(true) {
			System.out.println("Controller.startApp [로그] 메뉴창 출력전");
			int command = -1;
			if (userInfo != null) {

				if (userInfo.getMemberRole().equals("ADMIN")) {
					// 관리자모드로 로그인했을때 메뉴출력
					command = view.printAdminMenu();
				} else {
					// 일반회원 로그인 했을 때 메뉴 출력
					command = view.printUserMenu();
				}
			} else {
				// 로그인 안했을 때 메뉴 출력
				command = view.printQuitMenu();
			}
			if(command == 0) {
				// 종료 메시지 출력
				view.printExit();
				break;
			}
			else if(command == 1) { // 회원가입
				String memberId;
				while(true) {
					memberId = view.inputId();//뷰에서 사용자가 입력한 아이디 받아오기
			
					MemberDTO memberDTO = new MemberDTO();//멤버디티오 새로 만들어서 정보담기
					memberDTO.setCondition("JOIN");//회원가입을 위한 분기점만들기
					memberDTO.setMemberId(memberId);//회원가입하려는 사람의 아이디
					memberDTO = memberDAO.selectOne(memberDTO);
					//멤버DAO에 아이디정보 요청하기
					if(memberDTO == null) { //아이디가 이미 존재하면
						break;
					}
				    view.printoverLapUser();//뷰에서 아이디 중복안내 가져오기
				}
				String memberPassword = view.inputPassword();//뷰에서 비번입력 받아오기
				String memberName = view.inputName();//뷰에서 이름 주소 폰번호받기
				String memberAddress = view.inputAddress();
				String memberPhoneNumber = view.inputPhoneNum();
				
				MemberDTO memberDTO = new MemberDTO();// 새로만들어서 데이터담기
				memberDTO.setMemberId(memberId);//아이디
				memberDTO.setMemberPassword(memberPassword);//비번
				memberDTO.setMemberName(memberName);
				memberDTO.setMemberAddress(memberAddress);
				memberDTO.setMemberPhoneNumber(memberPhoneNumber);
				
				boolean flag = memberDAO.insert(memberDTO);//멤버DAO에 회원추가요청
			    if(flag) {
			        view.printSigninSuccess();//회원가입 성공 가져옴
			    } else {
			        view.printSigninFail();//회원가입 실패 가져옴
			    }				
			} 
			else if(command == 2) { // 로그인
				String memberId = view.inputId(); //뷰에서 아이디 비번 받기
				String memberPassword = view.inputPassword();
				
				MemberDTO memberDTO = new MemberDTO();//새로만들어서 정보담기
				
				memberDTO.setCondition("LOGIN");//로그인하기 위한 분기점만들기
				memberDTO.setMemberId(memberId);// 아이디
				memberDTO.setMemberPassword(memberPassword);//비번
				memberDTO=memberDAO.selectOne(memberDTO);
				//멤버DAO에 로그인 요청하기
				if(memberDTO == null) {//로그인실패 안내 가져옴
					view.printLoginFail();
				}
				else {
					//로그인성공
					userInfo = memberDTO;//멤버디티오에 유저정보 담아주기
					userInfo.setMemberPassword(null);//비번가려주기
					view.printLoginSuccess();//뷰에서 로그인 성공안내 가져오기
				}
			}
			else if(command == 3) { // 로그아웃
				boolean flag = view.printLogoutMsg();
				if(flag == true) {
					userInfo = null;// 유저인포 비워주기
					view.printLogoutSuccess();
				} else {
					view.printLogoutFail();
				}
				
			}
			else if(command == 4) { // 마이페이지
				//마이페이지를 들어가려면 일단 로그인상태여야함
				//내정보 전체출력
				MemberDTO memberDTO = new MemberDTO();
				//새로만들고 정보담아주기
				memberDTO.setMemberId(userInfo.getMemberId());
				memberDTO.setMemberName(userInfo.getMemberName());
				memberDTO.setMemberAddress(userInfo.getMemberAddress());
				memberDTO.setMemberPhoneNumber(userInfo.getMemberPhoneNumber());
				command = view.printMypage(memberDTO);
				
				// 회원탈퇴
				if (command == 20) {
					// 회원탈퇴하기
					// QUIT 분기점 없애기로 해서 LOGIN사용함
					
					while(true) {
						String memberPassword = view.inputPassword();
						// 현재로그인한사람 + 새로입력받은 비번이 올바른지체크하기
						MemberDTO ckmemberDTO = new MemberDTO();
						ckmemberDTO.setMemberId(userInfo.getMemberId());// 현재 로그인한 사람
						ckmemberDTO.setCondition("LOGIN");
						ckmemberDTO.setMemberPassword(memberPassword);// 새로입력한 비번
						MemberDTO data = memberDAO.selectOne(ckmemberDTO);
						// 멤버DAO에 입력한 정보가 일치하는지 요청하기
						if (data==null) {// 업데이트 실패시
							view.printWrongPassword();
						} else {// 일치한다면
							int num = view.isCheckQuit();
							if(num == 1) {
								memberDTO = new MemberDTO();
								memberDTO.setMemberId(userInfo.getMemberId());// 현재 로그인한 사람
								memberDTO.setMemberPassword(memberPassword);// 새로입력한 비번
								boolean flag = memberDAO.update(memberDTO);
								userInfo = null;// 로그아웃							
								view.printQuitSuccess();
								break;
							}
							else {
								// 회원탈퇴 취소
								view.printQuitFail();
								break;
							}
						}
					}
				}
				else if(command == 25) {
					continue;
				}
			}
			else if(command == 5) { // 상품 추가
				String productName =view.inputProductName();// 뷰에서 이름 가격 재고
				int productPrice = view.inputProductPrice();
				int productCount = view.inputProductCount();
				ProductDTO productDTO = new ProductDTO();
				
				String productBrand= view.inputBrandName();// 브랜드명입력받기
				
				productDTO = new ProductDTO();
				//상품 디티오 새로만들어서 데이터 담기
				productDTO.setProductName(productName);
				productDTO.setProductPrice(productPrice);
				productDTO.setProductCount(productCount);
				productDTO.setProductBrand(productBrand);
				
				boolean flag = productDAO.update(productDTO);
				//상품DTO에 상품추가요청하기
				if(flag) {
					view.printAddProductSuccess();
				}
				else {
					view.printAddProductFail();
				}
			}
			else if(command == 6) { // 재고 추가
				
				int productPk = view.getPK(); //view.pk입력받아오기
				int productCount = view.addInventory(); //view.수량입력받아오기
				
				ProductDTO productDTO = new ProductDTO();
				productDTO.setCondition("ADD_PRODUCT");
				//새로만들어서 데이터 담기
				productDTO.setProductPK(productPk);
				productDTO.setProductCount(productCount);
				
				boolean flag = productDAO.update(productDTO);
				if(flag) {
					view.printAddProductSuccess();
				}
				else {
					view.printAddProductFail();
				}
			}
			else if (command == 7) {
				// 상품 전체 출력
				ProductDTO productDTO = new ProductDTO();
				productDTO.setCondition("ALL_DESC"); // 기본은 상품PK내림차순
				// datas 출력 후 이후 command 받기
				ArrayList<ProductDTO> datas = new ArrayList<>();
				datas = productDAO.selectAll(productDTO);
				view.printAllProducts(datas); // view에서 datas 출력하기
				command = view.printProductDetailMenu();
				
// 수정07 검색어, 가격 내림/오름차순 / 브랜드별 출력 모두 수정
				if (command == 12) { // 검색어로 출력
					// 검색어 입력
					String keyword = view.inputSearch(); // 검색어 입력 받기

					productDTO = new ProductDTO();
					productDTO.setCondition("ALL_SEARCH");
					productDTO.setKeyword(keyword); // setProductName에 검색어 넘겨주기 ////////////////
					ArrayList<ProductDTO> searchDatas = productDAO.selectAll(productDTO);
					view.printAllProducts(searchDatas);
				} else if (command == 13) { // 가격 내림차순으로 출력
					productDTO = new ProductDTO();
					productDTO.setCondition("ALL_PRICE_DESC");
					ArrayList<ProductDTO> priceDescDatas = productDAO.selectAll(productDTO);
					
					view.printAllProducts(priceDescDatas);
				} else if (command == 14) { // 가격 오름차순으로 출력
					productDTO = new ProductDTO();
					productDTO.setCondition("ALL_PRICE_ASC");
					ArrayList<ProductDTO> priceAscDatas = productDAO.selectAll(productDTO);

					view.printAllProducts(priceAscDatas);
				} else if (command == 15) { // 브랜드별로 출력
					// 1. V에 현재 M에 있는 브랜드명 4개 넘겨주기
					// 2. View를 통해 사용자 입장에서는 브랜드 번호 입력 받고
					// 3. 내부적으로는 View에서 ▶ 브랜드 번호를 브랜드명으로 변경하여 M에 보내기

					// 브랜드 이름 뽑아오기
					view.printBrandName(); // 브랜드 리스트 출력
					String brandName = view.inputBrandName(); // 브랜드 이름 하나 받기 = 브랜드 이름 출력하기

					// 브랜드 이름 넘기기
					productDTO = new ProductDTO();
					productDTO.setCondition("ALL_BRAND");
					productDTO.setProductBrand(brandName); // 브랜드 이름 넘기기

					// 찾고자 하는 브랜드 출력하기
					view.printAllProducts(productDAO.selectAll(productDTO));
				}
				// 물건 상세보기
				// 물건 상세보기
				else if (command == 8) {
// 수정01 물건 상세보기 전체 수정
					ProductDTO data = view.inputProductNum(datas); // productDAO.selectAll(productDTO)

// 수정03 data는 무조건 받을 수 있으므로 분기 삭제
					// 상세보기 출력
					view.printProduct(data); // view에서 datas 보여달라고 요청 ✏✏😀🔻🔻🔻🔺🔺✏✏😀🔻🔻🔻🔺🔺
					if(userInfo!=null && userInfo.getMemberRole().equals("ADMIN")) {
						command = view.printDetailedAdminMenu();
					}
					else {						
						command = view.printDetailedMenu();
					}

					// 있을 경우 222
					if (command == 10) { // 바로 구매하기
						if(userInfo==null) {
							view.printLoginFirst();
							continue;
						}
						// 몇 개 구매하는지 입력받기
						int productCnt = view.buyProductCount();
						// 상품 재고가 부족하면
						if(productCnt > productDAO.selectOne(data).getProductCount()) {
							System.out.println("재고가 부족합니다");
							System.out.println("현재 재고: "+ productDAO.selectOne(data).getProductCount());
							continue;
						}
						productDTO = new ProductDTO();
						productDTO.setCondition("BUY_PRODUCT"); // condition
						productDTO.setProductCount(productCnt); // 입력 개수 넘기기
						productDTO.setProductPK(data.getProductPK()); // 상품 PK 넘기기
						// update 진행
						boolean flag = productDAO.update(productDTO);

						// 바로구매 성공/실패
						if (flag) {
							view.printBuySuccess();
						} else {
							view.printBuyFail();
						}
					} 
// 수정04 상품 삭제보기에서 장바구니 구매 기능은 설계에 존재하지 않음으로 삭제					
//					
// 수정06 상품 관리자뷰에서 상품 삭제 기능 수행가능하게 만들기
					// 상품 삭제
					else if (command == 25) { // 관리자 기능
						// View에서 상품 삭제 여부 물어보기
						boolean flag = view.printDeleteProduct();

						if (flag) { // 예라고 답했을 경우
							productDTO = new ProductDTO();
							productDTO.setProductPK(data.getProductPK()); // 삭제하려는 상품 PK

							// delete 진행
							flag = productDAO.delete(productDTO); // true/false 반환 받기

							// 상품 삭제 성공 여부
							if (flag) {
								view.printDeleteProductSuccess();
							} else {
								view.printDeleteProductFail();
							}

						} else { // 아니오 라고 답했을 경우
							view.printDeleteProductCancel();
						}
					}
					// 장바구니에 담기
					else if (command == 23) {
						if(userInfo==null) {
							view.printLoginFirst();
							continue;
						}
						int productCountToPutIn = this.view.inputProductCountToPutInBag();
						BagDTO bag = new BagDTO();
						bag.setMemberPk(userInfo.getMemberPk());
						bag.setProductPk(data.getProductPK());
						bag.setProductCount(productCountToPutIn);
						bagDAO.insert(bag);
					}

				}

			} 
			else if (command == 11) { // 장바구니
				if (userInfo == null) {
					view.printLoginFirst();
					continue;
				}
				// 장바구니에 상품 추가
				// V에서 몇 개 구매할지 입력 받기
				ArrayList<BagDTO> datas = new ArrayList<BagDTO>();

				BagDTO bagDTO = new BagDTO();
				// 데이터 담아주기 멤버Pk 상품Pk 추가할개수
				bagDTO.setMemberPk(userInfo.getMemberPk());
				datas = bagDAO.selectAll(bagDTO);
				view.printBag(datas);
				
				command = view.printBuy();

				if (command == 22) { // 구매 안하면
					continue;
				} else if (command == 21) { // 구매 한다고 하면
					for (int i = 0; i < datas.size(); i++) {
						ProductDTO pDTO = new ProductDTO();
						pDTO.setProductPK(datas.get(i).getProductPk());
						pDTO = productDAO.selectOne(pDTO);
						if(datas.get(i).getProductCount() > productDAO.selectOne(pDTO).getProductCount()) {
							System.out.println("재고가 부족합니다");
							System.out.println("현재 재고: "+ productDAO.selectOne(pDTO).getProductCount());
							continue;
						}
						ProductDTO data = new ProductDTO();
						data.setCondition("BUY_PRODUCT");
						data.setProductName(datas.get(i).getProductName());
						data.setProductCount(datas.get(i).getProductCount());
						data.setProductPK(datas.get(i).getProductPk());
						if (productDAO.update(data)) {
							view.printProductBoughtSuccess(data);
						} else {
							view.printProductBoughtFailed(data);
						}
						bagDAO.delete(datas.get(i));
					}
				}
			}
		}
	}
}
