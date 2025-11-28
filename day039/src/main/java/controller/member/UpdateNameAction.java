package controller.member;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class UpdateNameAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMid((String)request.getSession().getAttribute("userInfo"));
		memberDTO.setName(request.getParameter("name"));
		if(memberDAO.update(memberDTO)){
			request.setAttribute("msg", "이름변경 성공! 로그인해서 이용해주세요.");
			request.setAttribute("location", "logout.do");
		}
		else{
			request.setAttribute("msg", "이름변경 실패... 관리자에게 문의해주세요.");
			request.setAttribute("location", "myPage.do");
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
		
	}
	
}
