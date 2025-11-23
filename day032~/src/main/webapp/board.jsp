<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.*, java.util.ArrayList"
    isELIgnored="false"
    %>
 <%-- <%@ page errorPage="/error/noBoardExistError.jsp" %> --%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 <%
 BoardDTO board = (BoardDTO)request.getAttribute("board");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 페이지</title>
</head>
<body>
<script type="text/javascript">
	function del(){
		let ans = confirm('정말 삭제하시겠습니까?');
		if(ans == true){
			// form요소의 command값을 DELETEBOARD로 바꿔주는 코드를 짜야함
			// 그런뒤 form요소의 submit 실행시켜달라 요청
			document.checkForm.command.value="DELETEBOARD";
			document.checkForm.submit();
		}
		else{
			
		}
	}
	function updateContent(){
		document.checkForm.command.value="UPDATECONTENTPAGE";
		document.checkForm.submit();
	}


	function updateReply(btn) {
		
		const li = btn.closest('li'); // 이 버튼이 속한 li
        const contentSpan = li.querySelector('.reply-content');
        const editForm = li.querySelector('.reply-edit-form');

        // 다른 댓글 수정 중인 거 닫고 싶으면 여기서 전체 초기화도 가능 (선택)
        // document.querySelectorAll('.reply-edit-form').forEach(f => f.style.display='none');
        // document.querySelectorAll('.reply-content').forEach(s => s.style.display='inline');

        contentSpan.style.display = 'none';
        editForm.style.display = 'block';

        // 수정 버튼 숨기고 싶으면
        btn.style.display = 'none';
		
		//btn.form.command.value = "UPDATEREPLYPAGE";
		//btn.form.submit();
	}
	function cancelEdit(cancelBtn) {
        const li = cancelBtn.closest('li');
        const contentSpan = li.querySelector('.reply-content');
        const editForm = li.querySelector('.reply-edit-form');
        const editBtn = li.querySelector('input[value="댓글변경"]');

        editForm.style.display = 'none';
        contentSpan.style.display = 'inline';
        if(editBtn) editBtn.style.display = 'inline';
    }
	function deleteReply(form) {
		const ans = confirm('정말 댓글을 삭제하시겠습니까?');
		if (ans == true) {
			form.command.value = "DELETEREPLY";
			form.submit();
		} else {

		}
	}
</script>

	
	
	<form name="checkForm" action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="UPDATETITLE">
		<input type="hidden" name="bid" value="<%=board.getBid()%>">
		
		<table border="1">
			<tr>
				<td>글 번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>글 내용</td>
			</tr>
			<tr>
				<%-- 백단에서 넘어온 정보를 이용하기 위해 getter사용 --%>
				
				<td><%=board.getBid() %></td>
				<td><input type="text" name="title" value="<%=board.getTitle() %>"></td>
				<%
				String boardWriter = board.getWriterName()!=null?board.getWriterName():"탈퇴한 사용자";
				%>
				<td><%=boardWriter %></td>
				<td><%=board.getContent() %></td>
			</tr>
			<tr>
				<td colspan="4" align="right">
				<%
				if(session.getAttribute("userInfo")!=null && (board.getWriter().equals(session.getAttribute("userInfo"))||session.getAttribute("userRoleInfo").equals("ADMIN"))){
				 %>
				 
				<input type="submit" value="제목변경">
				<input type="button" value="글 삭제" onclick="del()">
				<input type="button" value="글 내용 변경" onclick="updateContent()">
				<%	
				}
				%>
				</td>
			</tr>
		</table>
	</form>
	
	<hr>
	<form action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="WRITEREPLY">
		<input type="hidden" name="bid" value="<%=board.getBid() %>">
		<input type="text" name="content" placeholder="댓글" required>
		<input type="submit" value="댓글작성">
	</form>
	<div id="reply">
		<ul>
			<%
			System.out.println("[로그] board.jsp 댓글 출력 전");
			if(((ArrayList<ReplyDTO>)request.getAttribute("replyDatas")).size()==0){
				out.println("<li>댓글이 비어있습니다.</li>");
			}
				for(ReplyDTO data:(ArrayList<ReplyDTO>)request.getAttribute("replyDatas")){
					System.out.println("[로그] board.jsp 댓글 출력 중");
					String replyWriter =data.getWriterName()!=null?data.getWriterName():"탈퇴한 사용자" ; 
			%>
			
			
			<li><%=replyWriter %>님 : 
			    <%-- ① 읽기용 내용 영역 --%>
	<span class="reply-content"><%=data.getContent()%></span>
	 <%-- ② 수정용 폼 (처음에는 숨김) --%>
    <form class="reply-edit-form" action="controller.jsp" method="POST" style="display:none; margin-top:4px;">
        <input type="hidden" name="command" value="UPDATEREPLY">
        <input type="hidden" name="bid" value="<%=board.getBid()%>">
        <input type="hidden" name="rid" value="<%=data.getRid()%>">
        
        <input type="text" name="content" value="<%=data.getContent()%>" size="40" required>
        <input type="submit" value="수정완료">
        <input type="button" value="취소" onclick="cancelEdit(this)">
    </form>
	
	<br>
	
	
	<form name="replyChangeForm" action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="">
		<input type="hidden" name="bid" value="<%=board.getBid() %>">
		<input type="hidden" name="rid" value="<%=data.getRid() %>">
		<%
		System.out.println("userInfo: " + session.getAttribute("userInfo"));
		System.out.println("data.getWriter(): " + data.getWriter());
		System.out.println("session: " + session);
		System.out.println("session.getAttribute(userRoleInfo): " + session.getAttribute("userRoleInfo"));
		
		boolean editable = session.getAttribute("userInfo")!=null&&data.getWriter().equals(session.getAttribute("userInfo"))||"ADMIN".equals(session.getAttribute("userRoleInfo")); 
		//data(replayDTO) 와 userInfo(mid)가 같으면
		if(editable){
			%>
		<input type="button" value="댓글변경" onclick="updateReply(this)">&nbsp;
		<input type="button" value="댓글삭제" onclick="deleteReply(this.form)">
		<%
		}
		%>
	</form>
			<%
				}
			%>
			
		</ul>
	</div>
	
	<hr>
	
	<a href="controller.jsp?command=MAINPAGE">메인페이지</a>
</body>
</html>