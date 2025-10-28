package view;

public class LogoutView extends View{
	
	public void showMenu(){
		System.out.println("3. 회원가입");
		System.out.println("4. 로그인");
		System.out.print(" >>> ");
	}
	@Override
	public int getCommand(){
		int command=0;
		while(true) {
			command = View.scanner.nextInt();
			if(0<=command && command <=4) {
				break;
			}
		}
		return command;
	}
	
	public String getMemberId() {
		System.out.print(" 아이디 입력 >>> ");
		String memberId= View.scanner.next();
		return memberId;
	}
	public String getMemberPw() {
		System.out.print(" 비밀번호 입력 >>> ");
		String memberPw = View.scanner.next();
		return memberPw;
	}
	public String getMemberName() {
		System.out.print(" 이름 입력 >>> ");
		String memberName = View.scanner.next();
		return memberName;
	}
	
	public void showIdIsOverride() {
		System.out.println("이미 존재하는 아이디입니다.");
	}
	public void showSignInSuccess() {
		System.out.println("회원가입 성공!");
	}
	public void showSignInFail() {
		System.out.println("회원가입 실패...");
	}
	
	public void showLoginSuccess() {
		System.out.println("로그인 성공!");
	}
	public void showLoginFail() {
		System.out.println("로그인 실패...");
	}
	
}
