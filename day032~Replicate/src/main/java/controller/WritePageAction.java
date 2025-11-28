package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WritePageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		if(request.getSession().getAttribute("userInfo")!=null){
			forward.setPath("write.jsp");
			forward.setRedirect(true);
		}
		else {
			request.setAttribute("msg", "이상이 감지되었습니다.");
			request.setAttribute("location", "mainPage.do");
			forward.setPath("message.jsp");
			forward.setRedirect(false);
		}
		return forward;
		
	}

}
