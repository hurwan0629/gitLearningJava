<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지01</title>
</head>
<body>

<h1>500에러</h1>
<hr>
요청 실패한 URI : ${pageContext.errorData.requestURI} <br>
상태코드 : ${pageContext.errorData.statusCode} <br>
예외유형 : ${pageContext.errorData.throwable }

</body>
</html>