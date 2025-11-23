<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%-- 
taglib지시어를 추가해서 라이브러리의 주소를 받을 수 있음
prefix="c" <- corefix의 줄임말 
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL</title>
</head>
<body>
	<%-- 태그 라이브러리 - JSTL  --%>
	<%-- Java Standard Tag Library --%>
	
	<a></a>
	<c:if test="${empty datas }">
		APPLE
	</c:if>
	<c:if test="${not empty datas }">
		BANANA
	</c:if>
	<hr>
	
	<%-- HTML의 if-else if 사용방법 --%>
	<c:choose>
		<%-- if와 비슷 --%>
		<c:when test="${empty datas }">
			if, else if, else if...
		</c:when>
		<%-- else와 매치 --%>
		<c:otherwise>
			else
		</c:otherwise>
	</c:choose>
	<hr>
	<ul>
		<c:forEach var="data" items="${datas }">
			<li>목록${data}</li>
		</c:forEach>
	</ul>
</body>
</html>