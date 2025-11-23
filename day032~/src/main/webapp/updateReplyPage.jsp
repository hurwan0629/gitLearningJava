<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%
request.getParameter("bid");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글변경</title>
</head>
<body>
	<h1><%=request.getParameter("bid") %></h1>
	<h1><%=request.getAttribute("rid") %></h1>
	<form action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="UPDATEREPLY">
		<input type="hidden" name="bid" value='<%= %>'>
		<input type="hidden" name="rid" value='<%=request.getParameter("rid") %>'>
		<input type="text" name="content" placeholder="댓글을 입력해주세요" required>
		<input type="submit" value="변경하기">
	</form>
	<a href='controller.jsp?command=BOARDPAGE&bid=<%=request.getParameter("bid") %>'>수정 취소하기</a>
</body>
</html>