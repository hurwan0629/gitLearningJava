<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습07</title>
</head>
<body>
<%
	// 기존에 application에 저장되어있던 값 + 1;
	int cnt = (Integer)application.getAttribute("cnt")+1;
	application.setAttribute("cnt",cnt);
%>

	투데이 방문자수 : [<%=application.getAttribute("cnt") %>명]
</body>
</html>