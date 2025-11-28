package controller.reply;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.common.Action;
import controller.common.ActionForward;
import model.dao.ReplyDAO;
import model.dto.ReplyDTO;

public class WriteReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(request.getSession().getAttribute("userInfo") == null){
			request.setAttribute("msg", "댓글을 달려면 로그인 해야합니다.");
			request.setAttribute("location", "boardPage.do?bid="+request.getParameter("bid"));
		}
		else {
			ReplyDTO replyDTO = new ReplyDTO();
			ReplyDAO replyDAO = new ReplyDAO();
			replyDTO.setContent(request.getParameter("content"));
			replyDTO.setBid(Integer.parseInt(request.getParameter("bid")));
			replyDTO.setWriter((String)request.getSession().getAttribute("userInfo"));
			if(replyDAO.insert(replyDTO)){
				request.setAttribute("msg", "댓글 추가됨");
				request.setAttribute("location", "boardPage.do?bid="+request.getParameter("bid"));
			}
			else {
				request.setAttribute("msg", "댓글 실패...");
				request.setAttribute("location", "boardPage.do?bid="+request.getParameter("bid"));
			}
		}
		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
}
