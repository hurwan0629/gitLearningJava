<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int result=0; // 스크립틀릿
	
	// 너 포스트 요청 했니?
	if(request.getMethod().equals("POST")) {
		// 계산하자~
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		String op = request.getParameter("op");
		
		if(op.equals("+")){
			result=num1+num2;
		}
		else{
			result=num1-num2;
		}
	}
%>
	<h1>계산기</h1>
	<hr>
	<form method="POST">
		<input type="text" name="num1">
		<select name="op">
			<option>+</option>
			<option>-</option>
		</select>
		<input type="text" name="num2">
		<input type="submit" value="계산하기">
	</form>
	<br>
	<h3>계산결과 : <%= "hellr" + result %></h3>
</body>
</html>