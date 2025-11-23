<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이런!</title>
</head>
<body>
	문제가 있는 페이지가 있는 모양입니다.<br>
요청 실패한 URI : ${pageContext.errorData.requestURI}
상태코드 : ${pageContext.errorData.statusCode }<br>
예외유형 : ${pageContext.errorData.throwable } <br>
<button onclick="history.go(-1)">메인페이지로 가기</button>
</body>
</html>