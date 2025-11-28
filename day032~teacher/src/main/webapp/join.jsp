<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
crossorigin="anonymous"></script>
<script type="text/javascript">
let idOK = false;  
let pwOK = false;
	$(function(){
		$('#mid').on("input", function(e){
			e.preventDefault(); // 이벤트 버블링 막는 방법 (submit 안되게 막기)
			
			let mid = $("#mid").val();
			
			//아무것도 입력 안했다면
			if(mid.trim() == ""){
				$('#mid-alert').html('');
				return;
			}
			$.ajax({
				url : "/day037/CheckServlet",
				type : "POST",
					// 파라미터 명 : 값
				data : { mid : mid,
					condition : "JOIN" }, // 보낼 데이터 (request.setParameter 과 유사)
				success : function(result){
					console.log(result);
					console.log(typeof result);
					if(result == 'true'){
						idOK = true;
						$('#mid-alert').html('<span style="color: red;">사용 가능한 아이디입니다!</span>');
					}
					else{
						idOK = false;
						$('#mid-alert').html('<span style="color: red;">사용 불가능한 아이디입니다!</span>');
					}
				},
				error : function(){
					alert("비동기 처리 실패....");
				}
			});
		});
		$('#password-check').on("input", function(e){
			e.preventDefault();
			
			
			
			let password = $('#password').val();
			let passwordCheck = $('#password-check').val();
			if(passwordCheck==""){
				pwOK = false;
				$('#mpw-ckeck').html('');
				return;
			}
			console.log('password : ['+password+']');
			console.log('password-check : ['+passwordCheck+']');
			if(password === passwordCheck){
				$('#mpw-ckeck').html('');
				pwOK = true;
			}
			else{
				pwOK = false;
				$('#mpw-ckeck').html('<span style="color: red;">비밀번호가 일치하지 않습니다</span>');
			};
		});
		$('form').on('submit', function(e){
	        if(!idOK || !pwOK){
	            e.preventDefault();
	            alert("아이디 또는 비밀번호 조건을 만족해야 회원가입 가능합니다.");
	        }
	    });
	});
	
</script>
</head>
<body>


	<form action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="JOIN">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" id="mid" name="mid" required><br><div id="mid-alert"></div></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="password" name="mpw" required></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" id="password-check" required></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="회원가입"></td>
			</tr>
		</table>
	</form>
	<div id="mpw-ckeck"></div>
	
	<a href="controller.jsp?command=MAINPAGE">메인페이지</a>
	

</body>
</html>