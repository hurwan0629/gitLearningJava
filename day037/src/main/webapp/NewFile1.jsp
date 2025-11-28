<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		let datas= ['red', 'lightgray', 'aqua', 'lightpink'];
		
		// each문법은 ','를 하나 찍어서 인자를 2개 넣을 수있는데
		// 1번에는 배열, 2번에는 수행 함수 적어야함 
		// 수행 함수의 첫번째 인자는 index, 두번째 인자는 value의 역할을 한다.
		$.each(datas, function(index, value){
			console.log(index+" → "+value)
		});
		
		// 객체 생성
		let obj = {
		//  key값 : value값
			name : "티모",
			grade : 13,
			score : 91
		};
		// 배열 자리에는 집합체만 넣어도 된다.
		$.each(obj,function(key, value){
			console.log(key+" → "+value)
		});
	});
</script>

</head>
<body>
	
</body>
</html>