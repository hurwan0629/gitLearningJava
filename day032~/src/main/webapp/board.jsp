<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.ReplyDTO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 페이지</title>
</head>
<body>
	
	
	
	<form action="제목변경" method="POST">
		<input type="submit" value="제목 변경">
	</form>
	
	<hr>
	<form action="댓글 작성" method="POST">
		<input type="submit" value="댓글작성">
	</form>
	<div id="reply">
		<ul>
			<%
				for(ReplyDTO data:(ArrayList<ReplyDTO>)request.getAttribute("replyDatas")){
			%>
	<li><%=data.getWriter() %>님 <%=data.getContent() %><a href="댓글변경">댓글변경</a><a href="댓글 삭제">댓글 삭제</a></li>
			<%
				}
			%>
			
		</ul>
	</div>
	
	<hr>
	
	<a href="글 삭제">글 삭제</a>
	<a href="메인 페이지로 가렴">메인페이지</a>
</body>
</html>