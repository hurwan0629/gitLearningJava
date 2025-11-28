package controller.page;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.BoardDAO;
import model.dao.ReplyDAO;
import model.dto.BoardDTO;
import model.dto.ReplyDTO;

public class BoardPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		ReplyDAO replyDAO = new ReplyDAO();
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		boardDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		
		BoardDTO board = boardDAO.selectOne(boardDTO);
		// board가 null이면 페이지 없음 오류 발생시키기
		if (board == null) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		request.setAttribute("board", board);
		request.setAttribute("replyDatas", replyDAO.selectAll(replyDTO));
		
		ActionForward forward = new ActionForward();
		forward.setPath("board.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
