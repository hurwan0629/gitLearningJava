<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, model.dto.BoardDTO"
    isELIgnored="false"
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#search-text").on("input",function(e){
			e.preventDefault();
			console.log("[로그] 게시판 키워드 검색 감지됨");
			
			let keyword = $("#search-text").val();
			
			$.ajax({
				url : "/day032~Replicate/MainBoardServlet",
				type :"POST",
				data : {
					keyword : keyword,
					condition : $("#search-type").val()
				},
				dataType : "json",
				success : function(result){
					console.log("[로그] 게시판 불러오기 성공");
					console.log(result)
					
					// ★ 테이블 갱신
	                let html = "";
	                $.each(result,function(index, value){
	                		html += '<tr>';
	                    	html += '  <td><a href="boardPage.do?bid=' 
	                          + value.bid + '">' + value.bid + '</a></td>';
	                    	html += '  <td>' + value.title + '</td>';
	                    	html += '  <td>' + (value.writerName ? value.writerName : '탈퇴한 사용자') + '</td>';
	                    	html += '</tr>';
	                });
					// id=board-body 부분 안쪽 전부 설정
	                $("#board-body").html(html);
				},
				error : function(errorInfo){
					console.log("[로그] 게시판 불러오기 실패");
					console.log(errorInfo.status);
					console.log(errorInfo.errorText);
				}
			});
		});
	});
</script>
</head>
<body>



<div id="login">
	<c:choose>
		<c:when test="${not empty sessionScope.userInfo }">
			<h1>${sessionScope.userNameInfo }님, 안녕하세요</h1>
			<a href="logout.do">로그아웃</a>
			<a href="myPage.do">마이페이지</a>
			<%-- 
			write.jsp로 바로 가도 되는건가? 
			사용자가 userInfo가 null이면 튕겨야 하므로 controller로 가는게 맞겠네
			--%>
			<a href="writePage.do">글작성 하러 가기</a>
		</c:when>
		<c:otherwise>
			<form action="login.do" method="GET">
				아이디 : <input type="text" name="mid" placeholder="아이디를 입력하세요." required><br>
				비밀번호 : <input type="password" name="mpw" placeholder="비밀번호를 입력하세요." required>
				<input type="submit" value="로그인">
			</form>
		  	<a href='joinPage.do'>회원가입</a>
	  	</c:otherwise>
	</c:choose>
</div>

<form action="mainPage.do" method="POST">
	<select name="condition" id="search-type">
		<option value="TITLE">제목으로 검색</option>
		<option value="WRITER">작성자로 검색</option>
	</select>
	<input type="text" id="search-text" name="keyword">
	<input type="submit" value="글 검색">
</form>

<table border="1">
	<caption>전체 글 출력</caption>
	<tr>
		<td>글 번호</td>
		<td>제목</td>
		<td>작성자</td>
	</tr>
	<tbody id="board-body">
		<c:forEach var="data" items="${requestScope.datas }">
		<tr>
			<td><a href="boardPage.do?bid=${data.bid }">${data.bid }</a></td>
			<td>${data.title }</td>
			<%-- 이게 작성자 mid가 아니라 member테이블의 mid와 같은 행의 name를 가져와야 하기 때문에 요청해야하나?해야하지. --%>
			<td>${ empty data.writerName ? "탈퇴한 사용자" : data.writerName }</td>
		</tr>	
	</c:forEach>
	</tbody>
	</tr>
</table>
</body>
</html>