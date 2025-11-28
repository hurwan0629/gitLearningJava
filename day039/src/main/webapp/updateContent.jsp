<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.BoardDTO" isELIgnored="false"%>
<jsp:useBean id="boardDTO" class="model.dto.BoardDTO"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>작성자 : ${requestScope.boardDTO.writer}</h1>
	<h4>제목 : ${requestScope.boardDTO.title}</h4>
	<form action="updateContent.do" method="POST">
		<input type="hidden" name="bid" value="${requestScope.boardDTO.bid}">
		<textarea name="content" class="text-box" >${requestScope.boardDTO.content}</textarea>
		<input type="submit" value="글 수정하기">
	</form>
	
	
</body>
</html>