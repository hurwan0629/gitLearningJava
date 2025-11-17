<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습06</title>
</head>
<body>
<%
	String mid = request.getParameter("mid"); 
	if(mid != null){
		// 세션에 로그인 정보 추가하기
		session.setAttribute("userInfo", mid);	
	}
%>

	<h1> <%=session.getAttribute("userInfo") %>님 환영합니다.</h1>
	<form action="NewFile10.jsp" method="POST">
		<select name="product">
			<option>사과</option>
			<option>바나나</option>
			<option>키위</option>
			<option>사탕</option>
		</select> <br>
		<input type="submit" value="장바구니에 상품 추가">
	</form>
	
	<hr>
	
	<a href="NewFile11.jsp">장바구니에 넣은 상품들 구매</a>
</body>
</html>