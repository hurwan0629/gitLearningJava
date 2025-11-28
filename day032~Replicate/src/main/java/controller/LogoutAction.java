package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		session.removeAttribute("userNameInfo");
		session.removeAttribute("userRoleInfo");
		
		ActionForward forward = new ActionForward();
		forward.setPath("mainPage.do");
		forward.setRedirect(true);
		return forward;
	}
	
}