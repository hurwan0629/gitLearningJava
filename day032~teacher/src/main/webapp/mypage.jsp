<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
</head>
<body>

	<form action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="UPDATENAME">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="mid" readonly value="<%=session.getAttribute("userInfo")%>"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" required value="<%=session.getAttribute("userNameInfo")%>"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="이름변경"></td>
			</tr>
		</table>
	</form>
	
	<a href="controller.jsp?command=DELETEMEMBER">회원탈퇴</a>
	
	<a href="controller.jsp?command=MAINPAGE">메인페이지</a>

</body>
</html>