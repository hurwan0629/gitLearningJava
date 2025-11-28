package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ContentDAO;
import model.dto.ContentDTO;

public class MainPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ContentDAO contentDAO = new ContentDAO();
		ContentDTO contentDTO = new ContentDTO();
		
		request.setAttribute("datas", contentDAO.selectAll(contentDTO));
		
		ActionForward forward = new ActionForward();
		forward.setPath("main.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
