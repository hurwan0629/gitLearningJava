<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습04</title>
</head>
<body>

	<%
		// 현재 세션이 새것이라면
		// 세션의 
		if(session.isNew()){
			out.println("<script>alert('세션을 새로 설정합니다.');</script>");
			session.setAttribute("userName", "홍길동");	
		}
	%>
	<%=session.isNew() %>	
	<h1><%=session.getAttribute("userName") %> 님, 환영합니다! :D</h1>

</body>
</html>