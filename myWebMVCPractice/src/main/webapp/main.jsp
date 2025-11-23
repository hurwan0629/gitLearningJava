<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
</head>
<body>
	<ul>
	<c:forEach var="data" items="${datas }" varStatus="st">
		<li>${st.count }번째 요소 : ${data }</li>
	</c:forEach>
	</ul>
</body>
</html>