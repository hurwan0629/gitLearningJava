package controller.board;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class WriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setContent(request.getParameter("content"));
		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setWriter((String) session.getAttribute("userInfo"));
		if (boardDAO.insert(boardDTO)) {
			request.setAttribute("msg", "글 작성 성공!");
			request.setAttribute("location", "mainPage.do");
		} else {
			request.setAttribute("msg", "글 작성 실패... 관리자에게 문의해주세요.");
			request.setAttribute("location", "mainPage.do");
		}
		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
