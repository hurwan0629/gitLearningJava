<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습04</title>
</head>
<body>

	<h1>계산기</h1>
	<hr>
	<!-- action디폴트는 자기 자신페이지 가져오는것 -->
	<form method="POST" action="/day3/CalcServlet">
		<input type="text" name="num1">
		<select name="op">
			<option>+</option>
			<option>-</option>
		</select>
		<input type="text" name="num2">
		<input type="submit" value="계산하기">
	</form>
	<br>
	
</body>
</html>