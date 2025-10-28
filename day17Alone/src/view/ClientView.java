package view;

import model.dto.MemberDTO;

public class ClientView extends View{
	public void showMenu(MemberDTO memberDTO) {
		System.out.println(" +++ " + memberDTO.getMemberName() +"님 +++");
		System.out.println("3. 회원탈퇴");
		System.out.println("4. 로그아웃");
		System.out.println("5. 비번변경");
		System.out.println("6. 상품구매");
		System.out.print(" >>> ");
	}
	@Override
	public int getCommand(){
		int command=0;
		while(true) {
			command = View.scanner.nextInt();
			if(0<=command && command <=6) {
				break;
			}
		}
		return command;
	}
	public void showLogoutSuccess() {
		System.out.println("로그아웃 하였습니다!");
	}
}
