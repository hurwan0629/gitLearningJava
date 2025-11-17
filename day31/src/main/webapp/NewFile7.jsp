<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%-- 
1. NewFile6.jsp 에서 msg를 받아와서 
2. msg를 msgs에 추가(저장) 해주고
3. NewFile6.jsp로 페이지 이동
 --%>
<%

	request.setCharacterEncoding("UTF-8");
	// 1.
	String msg = request.getParameter("msg");

	// 2.
	ArrayList<String> msgs = (ArrayList<String>)session.getAttribute("msgs");
	if(msgs==null){
		msgs = new ArrayList<>();
		session.setAttribute("msgs", msgs);
	}
	msgs.add(0, session.getAttribute("mid") + "님의 메세지 >> " + msg);
	
	// 3.
	// 페이지 이동은 "응답"에 해당하는 개념이기 때문에 response로 페이지 불러오기
	// method="GET" 로 페이지 불러오는 것과 동일하다.
	response.sendRedirect("NewFile6.jsp");
%>