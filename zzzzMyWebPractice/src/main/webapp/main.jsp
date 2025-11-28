<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
	<a href="writeContentPage.do">글쓰러 가기</a>
	<table border="1">
		<thead>
			<tr>
				<th>글 번호</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="data" items="${datas}">
			<tr>
				<td><a href="viewContentPage.do?cpk=${data.cpk}">${data.cpk}</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>