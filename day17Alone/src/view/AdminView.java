package view;

import model.dto.MemberDTO;

public class AdminView extends View {
	public void showMenu(MemberDTO memberDTO) {
		System.out.println(" +++ " + memberDTO.getMemberName() +"님 +++");
		System.out.println("3. 회원탈퇴");
		System.out.println("4. 로그아웃");
		System.out.println("5. 상품추가 ");
		System.out.println("6. 상품삭제");
		System.out.println("7. 상품재고 추가");
		System.out.print(" >>> ");
	}
	@Override
	public int getCommand(){
		int command=0;
		while(true) {
			command = View.scanner.nextInt();
			if(0<=command && command <=5) {
				break;
			}
		}
		return command;
	}
}
