<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비동기 처리 : $.ajax()</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
crossorigin="anonymous"></script>
<script type="text/javascript">
	$.ajax({
		type : "GET", // GET POST FETCH 등등
		url : "data.json", // 요청내용
		dataType : "json", // 문자열, json (나머지는 잘 안쓰임)
		success : function(result){
			console.log(result);
			
			let elem="<ul>";
			$.each(result, function(index, value){
				elem +="<li>" + value.name+"님 " + value.score+"점 " + value.grade+"등급입니다" + "</li>";
			});
			elem+="</ul>"
			$("body").append(elem);
		},
		error : function(errorInfo){
			console.log(errorInfo.status);
			console.log(errorInfo.errorText);
		}
	});
</script>
</head>
<body>
	
</body>
</html>