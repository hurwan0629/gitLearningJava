<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
	<ul>
	<c:forEach var="data" items="${datas }" varStatus="st">
		<li>${st.count }번쨰 요소 : ${data }</li>
	</c:forEach>
	</ul>
	<a href="errorPage.jsp">에러 페이지로 가기</a>
</body>
</html>