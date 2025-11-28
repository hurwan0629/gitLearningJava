package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO memberDTO = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		
		memberDTO.setCondition("LOGIN");
		memberDTO.setMid(request.getParameter("mid"));
		memberDTO.setMpw(request.getParameter("mpw"));
		MemberDTO data = memberDAO.selectOne(memberDTO);
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		if(data!=null){
			session.setAttribute("userInfo", data.getMid());
			session.setAttribute("userNameInfo", data.getName());
			session.setAttribute("userRoleInfo", data.getMrole());
			
			forward.setPath("mainPage.do");
			forward.setRedirect(true);
		}
		else{
			forward.setPath("loginFail.do");
			forward.setRedirect(true);
		}
		return forward;
		
	}
	
}
