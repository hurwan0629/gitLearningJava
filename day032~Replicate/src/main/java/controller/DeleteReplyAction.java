package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ReplyDAO;
import model.dto.ReplyDTO;

public class DeleteReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ReplyDTO replyDTO = new ReplyDTO();
		ReplyDAO replyDAO = new ReplyDAO();
		replyDTO.setRid(Integer.parseInt(request.getParameter("rid")));
		
		
		if(replyDAO.delete(replyDTO)){
			request.setAttribute("msg", "뎃글 삭제 성공");
			request.setAttribute("location", "boardPage.do?bid="+request.getParameter("bid"));
		}
		else{
			request.setAttribute("msg", "뎃글 삭제 실패");
			request.setAttribute("location", "boardPage.do?bid="+request.getParameter("bid"));
		}
		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
