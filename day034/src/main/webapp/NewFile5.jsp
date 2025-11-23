<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>값 보내기 실습 02</title>
</head>
<body>
	
	<%=request.getParameter("apple") %>
	${param.apple }
	
	<%=request.getAttribute("banana") %>
	${banana }
	
</body>
</html>