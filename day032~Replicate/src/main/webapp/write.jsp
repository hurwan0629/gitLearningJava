<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
</head>
<body>
	
	<form action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="WRITE">
		<input type="text" name="title" placeholder="제목"><br>
		<textarea name="content" rows="10" cols="50" 
		placeholder="여기에 글을 입력하세요" required></textarea><br>
		<input type="submit" value="글 작성">
	</form>
	
	<a href="controller.jsp?command=MAINPAGE">메인페이지로 돌아가기</a>
	
</body>
</html>