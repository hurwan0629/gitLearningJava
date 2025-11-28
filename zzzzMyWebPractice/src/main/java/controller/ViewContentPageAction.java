package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ContentDAO;
import model.dto.ContentDTO;

public class ViewContentPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int cpk = Integer.parseInt(request.getParameter("cpk"));
		
		ContentDAO contentDAO = new ContentDAO();
		ContentDTO contentDTO = new ContentDTO();
		
		contentDTO.setCpk(cpk);
		
		request.setAttribute("content", contentDAO.selectOne(contentDTO).getContent());
		
		ActionForward forward = new ActionForward();
		forward.setPath("viewContent.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
