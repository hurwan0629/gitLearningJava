<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 
무엇을 만들어야 하냐면.... 회원 가입? 쇼핑몰?
게시판 같이 글쓰는건 이미 하고있으니까
쇼핑몰을 해야하는건가
--%>

<%
// command를 parameter로 받았겠지
String command = request.getParameter("command");

if(command != null){
	System.out.println("controller.jsp [로그] command: " + command);
	if(command.equals("MAINPAGE")){
		
		out.println("<script>location.href='main.jsp';</script>");
	}
}
else{
	// command를 받지 못했을때
	System.out.println("controller.jsp [로그] command파라미터 없음");
	out.println("<script>alert('아직 만들어지지 않은 페이지입니다!');</script>");
	out.println("<script>history.go(-1);</script>");
}




%>