<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습 03</title>
</head>
<body>

request : <%=request %>

<hr>

input text : <%=request.getParameter("apple") %><br>
select : <%=request.getParameter("banana") %><br>
<%=request.getParameter("kiwi") %>

</body>
</html>