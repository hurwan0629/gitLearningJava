<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 페이지</title>
</head>
<body>

<script>
	alert('<%=request.getAttribute("msg")%>');
	location.href='<%=request.getAttribute("location")%>';
</script>

</body>
</html>