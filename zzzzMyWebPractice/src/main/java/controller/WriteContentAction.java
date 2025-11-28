package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ContentDAO;
import model.dto.ContentDTO;

public class WriteContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ContentDAO contentDAO = new ContentDAO();
		ContentDTO contentDTO = new ContentDTO();
		
		String content = request.getParameter("content");
		contentDTO.setContent(content);
		System.out.println(content);
		if(contentDAO.insert(contentDTO)) {
			request.setAttribute("msg", "글이 추가되었습니다.");
			request.setAttribute("location", "mainPage.do");
		}
		else {
			request.setAttribute("msg", "문제가 발생해 글이 추가되지 못했습니다.");
			request.setAttribute("location", "mainPage.do");
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
