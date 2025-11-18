<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.MemberDTO, model.MemberDAO"%>
<jsp:useBean id="memberDAO" class="model.MemberDAO" />
<jsp:useBean id="memberDTO" class="model.MemberDTO" />
<jsp:setProperty property="*" name="memberDTO" />

<%
	
	//로그인form에서 submit를 했을때 action이 다시 이 페이지를 불러옴
	// 기본 페이지는 GET방식을 불려지기 때문에 식별 가능
	// if("POST".equals(request.getMethod())){
	// String mid = request.getParameter("mid");
	// String mpw = request.getParameter("mpw");
	// memberDTO.setMid(mid);
	// memberDTO.setMpw(mpw);
	
	//MemberDAO memberDAO = new MemberDAO();
	// MemberDTO memberDTO = new MemberDTO();
	
	// 회원 존재여부
	System.out.println(memberDTO);
	MemberDTO data = memberDAO.selectOne(memberDTO);
	
	if (data!=null) {
		session.setAttribute("mid", memberDTO.getMid());
		response.sendRedirect("/day31miniHomepagePractice/homepage.jsp");
	}
	else {
		out.println("<script>alert('존재하지 않는 회원정보 입니다.');location.href='/day31miniHomepagePractice/index.jsp';</script>");
	}
	// }
	
%>