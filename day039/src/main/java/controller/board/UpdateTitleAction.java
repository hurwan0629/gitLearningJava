package controller.board;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class UpdateTitleAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setCondition("UPDATE_TITLE");
		if(boardDAO.update(boardDTO)){
			request.setAttribute("msg", "제목변경 성공!");
			request.setAttribute("location", "mainPage.do");
		}
		else{
			request.setAttribute("msg", "제목변경 실패...");
			request.setAttribute("location", "mainPage.do");
		}
		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
