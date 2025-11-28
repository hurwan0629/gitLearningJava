package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.BoardDAO;
import model.dao.MemberDAO;
import model.dao.ReplyDAO;
import model.dto.BoardDTO;
import model.dto.MemberDTO;
import model.dto.ReplyDTO;

public class DeleteMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setCondition("UPDATE_DELMEM");
		boardDTO.setWriter((String) session.getAttribute("userInfo"));
		boardDAO.update(boardDTO);
		
		
		ReplyDAO replyDAO = new ReplyDAO();
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setCondition("UPDATE_DELMEM");
		replyDTO.setWriter((String) session.getAttribute("userInfo"));
		replyDAO.update(replyDTO);
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMid((String) session.getAttribute("userInfo"));

		if (memberDAO.delete(memberDTO)) {
			request.setAttribute("msg", "회원탈퇴 성공! 다음에 다시 이용해주세요..");
			request.setAttribute("location", "logout.do");
		}
		else{
			request.setAttribute("msg", "회원탈퇴 실패... 관리자에게 문의해주세요.");
			request.setAttribute("location", "myPage.do");
		}
		ActionForward forward = new ActionForward();
		forward.setPath("message.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
