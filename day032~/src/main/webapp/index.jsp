<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="boardDAO" class="model.dao.BoardDAO" />
<jsp:useBean id="boardDTO" class="model.dto.BoardDTO" />
<%
	// 컨트롤러의 파트
	// GET로 요청하는것과 같음
	response.sendRedirect("controller.jsp?command=MAINPAGE");
	// command라는 파라미터에 "MAINPAGE" 넣어주어서 보내기
	
%>