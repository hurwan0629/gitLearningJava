<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.MemberDTO" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="memberDAO" class="model.dao.MemberDAO" />
<jsp:useBean id="memberDTO" class="model.dto.MemberDTO" />
<jsp:setProperty property="*" name="memberDTO" />
<jsp:useBean id="boardDAO" class="model.dao.BoardDAO" />
<jsp:useBean id="boardDTO" class="model.dto.BoardDTO" />
<jsp:setProperty property="*" name="boardDTO" />
<jsp:useBean id="replyDAO" class="model.dao.ReplyDAO" />
<jsp:useBean id="replyDTO" class="model.dto.ReplyDTO" />
<jsp:setProperty property="*" name="replyDTO" />
<%
	String command = request.getParameter("command");
	System.out.println("[로그] command : "+command);
	System.out.println("[로그] memberDTO : "+memberDTO);

	if(command.equals("LOGIN")){
		// V에게서 받아온 mid,mpw 정보를 가지고
	
		// M한테 로그인요청하기
		// == M한테 selectOne()요청하기
		memberDTO.setCondition("LOGIN");
		MemberDTO data = memberDAO.selectOne(memberDTO);
	
		// 로그인 성공하면 main.jsp로 이동
		// session에 로그인 정보 추가하고 이동
		if(data != null){
			session.setAttribute("userInfo", data.getMid());
			session.setAttribute("userNameInfo", data.getName());
			session.setAttribute("userMrole", data.getMrole());
			response.sendRedirect("controller.jsp?command=MAINPAGE");
		}
		else{
			request.setAttribute("msg", "로그인 실패...");
			request.setAttribute("location", "controller.jsp?command=MAINPAGE");
			pageContext.forward("message.jsp");
		}	
		// 로그인 실패하면 main.jsp로 이동
		// 안내창 출력하고 이동
	}
	else if(command.equals("DELETEBOARD")){
		if(boardDAO.delete(boardDTO)){
			response.sendRedirect("controller.jsp?command=MAINPAGE");
		}
		else{
			out.println("<script>alert('글 삭제 실패...');location.href='controller.jsp?command=MAINPAGE';</script>");
		}
	}
	else if(command.equals("JOIN")){
		if(memberDAO.insert(memberDTO)){
			out.println("<script>alert('회원가입 성공! 로그인해서 이용해주세요.');location.href='controller.jsp?command=MAINPAGE';</script>");
		}
		else{
			request.setAttribute("msg", "회원가입 실패... 관리자에게 문의해주세요.");
			request.setAttribute("location", "controller.jsp?command=JOINPAGE");
			pageContext.forward("message.jsp");
		}
	}
	else if(command.equals("WRITE")){
		if(boardDAO.insert(boardDTO)){
			response.sendRedirect("controller.jsp?command=MAINPAGE");
		}
		else{
			out.println("<script>alert('글 작성 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=WRITEPAGE';</script>");
		}
	}
	else if(command.equals("UPDATENAME")){
		if(memberDAO.update(memberDTO)){
			out.println("<script>alert('이름변경 성공! 로그인해서 이용해주세요.');location.href='controller.jsp?command=LOGOUT';</script>");
		}
		else{
			out.println("<script>alert('이름변경 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=MYPAGE';</script>");
		}
	}
	else if(command.equals("UPDATEREPLYCONTENT")){
		if(replyDAO.update(replyDTO)){
			response.sendRedirect("controller.jsp?command=BOARDPAGE&bid="+replyDTO.getBid());
		}
		else{
			out.println("<script>alert('댓글 내용 변경 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=BOARDPAGE&bid="+replyDTO.getBid()+"';</script>");
		}
	}
	else if(command.equals("DELETEREPLY")){
		if(replyDAO.delete(replyDTO)){
			response.sendRedirect("controller.jsp?command=BOARDPAGE&bid="+replyDTO.getBid());
		}
		else{
			out.println("<script>alert('댓글 삭제 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=BOARDPAGE&bid="+replyDTO.getBid()+"';</script>");
		}
	}
	else if(command.equals("INSERTREPLY")){
		if(replyDAO.insert(replyDTO)){
			response.sendRedirect("controller.jsp?command=BOARDPAGE&bid="+replyDTO.getBid());
		}
		else{
			out.println("<script>alert('댓글 작성 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=BOARDPAGE&bid="+replyDTO.getBid()+"';</script>");
		}
	}
	else if(command.equals("DELETEMEMBER")){
		memberDTO.setMid((String)session.getAttribute("userInfo"));
		if(memberDAO.delete(memberDTO)){
			out.println("<script>alert('회원탈퇴 성공! 다음에 다시 이용해주세요.');location.href='controller.jsp?command=LOGOUT';</script>");
		}
		else{
			out.println("<script>alert('회원탈퇴 실패... 관리자에게 문의해주세요.');location.href='controller.jsp?command=MYPAGE';</script>");
		}
	}
	else if(command.equals("UPDATETITLE")){		
		if(boardDAO.update(boardDTO)){
			response.sendRedirect("controller.jsp?command=MAINPAGE");
		}
		else{
			out.println("<script>alert('제목 변경 실패...');location.href='controller.jsp?command=MAINPAGE';</script>");
		}
	}
	else if(command.equals("MAINPAGE")){
		// datas 준비해서,
		boardDTO.setCondition("ALL");
		request.setAttribute("datas", boardDAO.selectAll(boardDTO));
		// 메인 페이지 보여줘~~
		pageContext.forward("main.jsp");
	}
	else if(command.equals("LOGOUT")){
		session.invalidate(); // session.removeAttribute("userInfo");
		
		// out.println("<script>alert('로그아웃 성공!');location.href='controller.jsp?command=MAINPAGE';</script>");
		request.setAttribute("msg", "로그아웃 성공!");
		request.setAttribute("location", "controller.jsp?command=MAINPAGE");
		pageContext.forward("message.jsp");
	}
	else if(command.equals("MYPAGE")){
		response.sendRedirect("mypage.jsp");
	}
	else if(command.equals("JOINPAGE")){
		response.sendRedirect("join.jsp");
	}
	else if(command.equals("BOARDPAGE")){
		request.setAttribute("board", boardDAO.selectOne(boardDTO));
		request.setAttribute("replyDatas", replyDAO.selectAll(replyDTO));
		pageContext.forward("board.jsp");
	}
	else if(command.equals("WRITEPAGE")){
		response.sendRedirect("write.jsp");
	}
%>