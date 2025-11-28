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
	  let index = 0;

	  function move(){
	    $("#images").css("transform", `translateX(-${index * 300}px)`);
	  }

	  $("#goleft").click(function(){
	    if(index === 0){
	      index = 2;   // 왼쪽 끝 → 마지막으로
	    } else {
	      index--;	
	    }
	    move();
	  });

	  $("#goright").click(function(){
	    if(index === 2){
	      index = 0;   // 오른쪽 끝 → 처음으로
	    } else {
	      index++;
	    }
	    move();
	  });

	});
</script>
<style type="text/css">
	#gallery{
		display: flex;
		flex-direction : column;
		align-items : center;
	}
	#image {
		display: flex;
		width : 300px;
		overflow : hidden;
		border : 1px solid black;
	}
	#images {
	  display: flex;
	  border : 1px solid black;
	  transition: transform 0.5s ease;   /* ← 여기! 부드러운 이동 */
	}
	#images img {
	  width: 300px;
	  flex-shrink: 0;
	}
</style>
</head>
<body>
	<div id="gallery">
		<div id="button">
			<button id="goleft">왼쪽으로</button>
			&nbsp;&nbsp;
			<button id="goright">오른쪽으로</button>
		</div>
		<div id="image">
			<div id="images">
				<img src="images/book01.jpg" alt="책 01">
				<img src="images/book02.jpg" alt="책 02">
				<img src="images/book03.jpg" alt="책 03">
			</div>
		</div>
	</div>
</body>
</html>