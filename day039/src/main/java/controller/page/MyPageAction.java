package controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		request.setAttribute("mid", session.getAttribute("userInfo"));

		MemberDAO memberDAO = new MemberDAO();

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMid((String) session.getAttribute("userInfo"));
		memberDTO.setCondition("GETUSERNAME");
		
		request.setAttribute("name", memberDAO.selectOne(memberDTO).getName());
		
		ActionForward forward = new ActionForward();
		forward.setPath("mypage.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
