<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.*, java.util.ArrayList" errorPage="error/error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
</head>
<body>
<script type="text/javascript">
	function del(){
		let ans = confirm('정말 삭제하시겠습니까?');
		if(ans == true){
			document.checkForm.command.value = "DELETEBOARD";
			document.checkForm.submit();
		}
		else{
			return;
		}
	}
	
	function edit(rid,bid){
		// [b] 요소 불러오기
		let elem = document.getElementById('reply_'+rid);
		
		// [c] 원래댓글내용 만들기에 활용됨
		let apple = elem.innerText;
		apple = apple.replace(" 댓글변경 댓글삭제","");
		
		/*
		[a] 새로운댓글내용
		<form action="controller.jsp" method="POST">
			<input type="hidden" name="command" value="UPDATEREPLYCONTENT">
			<input type="hidden" name="bid" value="">
			<input type="hidden" name="rid" value="">
			<input type="text" name="content" required value="기존내용">
		</form>
		*/
		// [a] txt 변수 ▶ 새로운댓글내용 만들기에 활용됨
		let txt = '<form action="controller.jsp" method="POST">';
		txt += '<input type="hidden" name="command" value="UPDATEREPLYCONTENT">';
		txt += '<input type="hidden" name="bid" value="'+bid+'">';
		txt += '<input type="hidden" name="rid" value="'+rid+'">';
		txt += '<input type="text" name="content" required value="'+apple+'">';
		txt += '<input type="submit" value="댓글변경">';
		txt += '</form>';
		
		// [b] 불러온 요소에 새로운 HTML([a]에서 만든 것) 작성
		elem.innerHTML = txt;
	}
	
	function check(rid,bid){
		let ans = confirm('정말 삭제하시겠습니까?');
		if(ans){
			location.href='controller.jsp?command=DELETEREPLY&rid='+rid+'&bid='+bid;
		}
	}
</script>

	<%
		BoardDTO board = (BoardDTO)request.getAttribute("board");
	%>
	
	<form name="checkForm" action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="UPDATETITLE">
		<input type="hidden" name="bid" value="<%=board.getBid()%>">
		<table border="1">
			<tr>
				<td>글 번호</td><td>제목</td><td>내용</td><td>작성자</td>
			</tr>
			<tr>
				<td><%=board.getBid()%></td><td><input type="text" name="title" value="<%=board.getTitle()%>" required></td><td><%=board.getContent()%></td><td><%=board.getWriter()%></td>
			</tr>
			<% if(session.getAttribute("userInfo")!=null && (session.getAttribute("userMrole").equals("ADMIN") || session.getAttribute("userInfo").equals(board.getWriter()))){ %>
			<tr>
				<td colspan="4" align="right"><input type="submit" value="제목 변경">&nbsp;<input type="button" value="글 삭제" onclick="del()"></td>
			</tr>
			<% } %>
		</table>		
	</form>
	
	<hr>
	
	<%
		if(session.getAttribute("userInfo") != null){ // 로그인했다면,
	%>
	<form action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="INSERTREPLY">
		<input type="hidden" name="bid" value="<%=board.getBid()%>">
		<input type="text" name="writer" readonly value="<%=session.getAttribute("userInfo")%>"> ▶ <input type="text" name="content" required>
		<input type="submit" value="댓글 작성">
	</form>
	<%
		}
		else{
	%>
		<input type="text" value="댓글을 작성하려면 로그인이 필요합니다." disabled size="35">
	<%
		}
	%>
	<div id="reply">
		<ul>
			<%
			for(ReplyDTO data:(ArrayList<ReplyDTO>)request.getAttribute("replyDatas")){
			%>
				<li><%=data.getWriter()%>님 <span id='reply_<%=data.getRid()%>'><%=data.getContent()%> 
				<%
				if(session.getAttribute("userInfo")!=null && (session.getAttribute("userMrole").equals("ADMIN") || session.getAttribute("userInfo").equals(data.getWriter()))){
				%>
				<button onclick='edit(<%=data.getRid()%>,<%=data.getBid()%>)'>댓글변경</button> <button onclick="check(<%=data.getRid()%>,<%=data.getBid()%>)">댓글삭제</button>
				<%
				}
				%>
				</span></li>
			<%
			}
			%>
		</ul>
	</div>
	
	<hr>
	
	<a href="controller.jsp?command=MAINPAGE">메인페이지</a>

</body>
</html>