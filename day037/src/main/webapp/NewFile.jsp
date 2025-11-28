<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
	// ▼ $() 는 객체 선언부이다.
	$(document).ready(function(){
		console.log('hello, jQuery!');
		
		// ▼ 객체 불러오기
		$("#box").css("border", "1px solid red");
		
	});
</script>
</head>
<body>
	<div id="box">안녕하세요! :D</div>
</body>
</html>