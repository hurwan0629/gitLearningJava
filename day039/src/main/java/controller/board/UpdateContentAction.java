package controller.board;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class UpdateContentAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		boardDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		boardDTO.setContent(request.getParameter("content"));
		boardDTO.setCondition("UPDATE_CONTENT");
		if(boardDAO.update(boardDTO)){
			request.setAttribute("msg", "글 수정됨");
			request.setAttribute("location", "boardPage.do?bid="+request.getParameter("bid"));
		}
		else{
			request.setAttribute("msg", "글 수정 실패");
			request.setAttribute("location", "boardPage.do?bid="+request.getParameter("bid"));
		}
		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
