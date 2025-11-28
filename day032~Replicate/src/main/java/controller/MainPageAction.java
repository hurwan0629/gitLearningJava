package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class MainPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();

		String keyword = request.getParameter("keyword");
		String condition = request.getParameter("condition");
		
		if (keyword == null || condition == null) {
			boardDTO.setCondition("ALL");
		}
		else {
			boardDTO.setCondition(condition);
			boardDTO.setKeyword(keyword);
		}
		request.setAttribute("datas", boardDAO.selectAll(boardDTO));
		ActionForward forward = new ActionForward();
		forward.setPath("main.jsp");
		forward.setRedirect(false);
		return forward;

	}

}
