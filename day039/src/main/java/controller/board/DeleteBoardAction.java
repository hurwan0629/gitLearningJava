package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.BoardDAO;
import model.dto.BoardDTO;

public class DeleteBoardAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setBid(Integer.parseInt(request.getParameter("bid")));
		
		ActionForward forward = new ActionForward(); 
		if(boardDAO.delete(boardDTO)){
			request.setAttribute("msg", "글 삭제 성공!");
			request.setAttribute("location", "mainPage.do");
		}
		else{
			request.setAttribute("msg", "글 삭제 실패...");
			request.setAttribute("location", "mainPage.do");
		}
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
