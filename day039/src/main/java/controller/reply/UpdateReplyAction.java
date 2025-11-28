package controller.reply;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.ReplyDAO;
import model.dto.ReplyDTO;

public class UpdateReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ReplyDTO replyDTO = new ReplyDTO();
		ReplyDAO replyDAO = new ReplyDAO();
		
		replyDTO.setCondition("UPDATE_REPLY");
		replyDTO.setRid(Integer.parseInt(request.getParameter("rid")));
		replyDTO.setContent(request.getParameter("content"));
		if(replyDAO.update(replyDTO)){
			request.setAttribute("msg", "댓글 수정됨");
			request.setAttribute("location", "boardPage.do?bid="+request.getParameter("bid"));
		}
		else{
			request.setAttribute("msg", "뎃글 수정 실패");
			request.setAttribute("location", "boardPage.do?bid="+request.getParameter("bid"));
		}
		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
