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
		<table border="1">
			<tr>
				<td>제목</td><td>내용</td><td>작성자</td>
			</tr>
			<tr>
				<td><input type="text" name="title" required></td>
				<td><input type="text" name="content" required></td>
				<td><input type="text" name="writer" value="<%=session.getAttribute("userInfo")%>" readonly></td>
			</tr>
			<tr>
				<td colspan="3" align="right"><input type="submit" value="글 작성"></td>
			</tr>
		</table>		
	</form>
	
	<a href="controller.jsp?command=MAINPAGE">메인페이지</a>

</body>
</html>