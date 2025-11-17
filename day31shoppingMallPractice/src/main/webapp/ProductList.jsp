<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String nickname = request.getParameter("nickname");
	if(nickname != null){
		session.setAttribute("nickname", nickname);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑몰 페이지</title>
</head>
<body>
	<h1>쇼핑몰</h1>
	<h3><%=session.getAttribute("nickname") %>님 환영합니다!</h3>
	<form action="AddItemLogic.jsp" method="POST">
		<!-- 
		<select name="product">
			<option>키보드</option>
			<option>마우스</option>
			<option>노트북</option>
		</select>
		 -->
		 <input type="checkbox" name="product" value="키보드"> 키보드<br>
		 <input type="checkbox" name="product" value="마우스"> 마우스<br>
		 <input type="checkbox" name="product" value="노트북"> 노트북<br>
		<input type="submit" value="장바구니에 추가하기">
	</form>
	
	<a href="CartList.jsp"><%=session.getAttribute("nickname") %>님의 장바구니</a>
</body>
</html>