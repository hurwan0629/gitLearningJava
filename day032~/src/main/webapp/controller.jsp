<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.MemberDTO" %>
    
<jsp:useBean id="boardDAO" class="model.dao.BoardDAO" />
<jsp:useBean id="boardDTO" class="model.dto.BoardDTO" />
<jsp:useBean id="memberDAO" class="model.dao.MemberDAO" />
<jsp:useBean id="memberDTO" class="model.dto.MemberDTO" />
<jsp:setProperty property="*" name="memberDTO" />

<%
	
	String command = request.getParameter("command");

	System.out.println("command : " + command);	
	
	
	if(command.equals("LOGIN")){
		// V에게서 받아온 mid, mpw 정보를 가지고 
	
		// 모델에게 로그인 요청하기
		// = M한테 selectOne()요청하기
	
		MemberDTO data = memberDAO.selectOne(memberDTO);
	
		if(data!=null){
			session.setAttribute("userInfo", data.getMid());
			//response.sendRedirect("main.jsp");
			response.sendRedirect("controller.jsp?command=MAINPAGE");
		}
		else{
			out.println("<script>alert('로그인 실패...');location.href='controller.jsp?command=MAINPAGE';</script>");
		}
	
		// 로그인 성공하면 main.jsp로 이동
		// session에 로그인 정보 추가
	
		// 로그인 실패하면 main.jsp로 이동
		// 안내창 출력 후 이동	
	}
	// 로그아웃 페이지
	else if(command.equals("LOGOUT")){
		// 어짜피 센션에 userInfo 있으니까 들어온것임으로 세션확인은 생략
		// 센션 비우고
		session.setAttribute("userInfo", null);
		// controller을 통해 main.jsp로 돌아가기
		out.println("<script>alert('로그아웃');location.href='controller.jsp?command=MAINPAGE';</script>");
	}
	else if(command.equals("MAINPAGE")){
		// 메인페이지를 보여주는 로직
		// datas 준비해서
		boardDTO.setCondition("ALL");
		request.setAttribute("datas", boardDAO.selectAll(boardDTO));
		// 메인페이지 보여줘

		
		
		// 페이지 이동 방식
		// 1. 리다이랙트 - 새로운 요청을 하는것과 동일 / 기존의 요청정보인 request를 새로 만들어서 가져감
		// response.sendRedirect("main.jsp");
		// 2. 포워드 - 다음 페이지로 데이터를 넘겨줘야 하는 상황이 있을때
		pageContext.forward("main.jsp");
	}
%>