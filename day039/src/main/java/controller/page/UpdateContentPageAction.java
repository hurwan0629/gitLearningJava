package controller.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class UpdateContentPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		boardDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		request.setAttribute("boardDTO", boardDAO.selectOne(boardDTO));
		ActionForward forward = new ActionForward();
		forward.setPath("updateContent.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
