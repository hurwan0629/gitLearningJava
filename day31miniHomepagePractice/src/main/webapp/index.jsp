<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<h2>로그인 해주세요</h2>
			<%-- command==? 과 같다 --%>
	<form action="controller_login.jsp" method="POST">
		아이디 <input type="text" name="mid" placeholder="아이디를 입력해주세요"><br>
		비밀번호 <input type="password" name="mpw" placeholder="비밀번호를 입력하세요"><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>