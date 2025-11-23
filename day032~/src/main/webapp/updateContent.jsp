<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.BoardDTO" isELIgnored="false"%>
<jsp:useBean id="boardDTO" class="model.dto.BoardDTO"/>
<%
boardDTO = (BoardDTO)request.getAttribute("boardDTO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>작성자 : <%=boardDTO.getWriter() %></h1>
	<h4>제목 : <%=boardDTO.getTitle() %></h4>
	<form action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="UPDATECONTENT">
		<input type="hidden" name="bid" value=<%=boardDTO.getBid() %>>
		<textarea name="content" class="text-box" ><%=boardDTO.getContent() %></textarea>
		<input type="submit" value="글 수정하기">
	</form>
	
	
</body>
</html>