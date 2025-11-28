<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*, java.util.ArrayList"
	isELIgnored="false"%>
<%-- <%@ page errorPage="/error/noBoardExistError.jsp" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세 페이지</title>
</head>
<body>
	<script type="text/javascript">
	function del(form){
		let ans = confirm('정말 삭제하시겠습니까?');
		if(ans == true){
			// form요소의 command값을 DELETEBOARD로 바꿔주는 코드를 짜야함
			// 그런뒤 form요소의 submit 실행시켜달라 요청
			
		form.action = "deleteBoard.do";
	     form.submit();
		}
		else{
			
		}
	}
	function updateContent(form){
		form.action = "updateContentPage.do";
	     form.submit();
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
			 form.action = "deleteReply.do";
		     form.submit();
		} else {

		}
	}
</script>
	<form name="checkForm" action="updateTitle.do" method="POST">
		<input type="hidden" name="bid" value="${board.bid }">

		<table border="1">
			<tr>
				<td>글 번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>글 내용</td>
			</tr>
			<tr>
				<%-- 백단에서 넘어온 정보를 이용하기 위해 getter사용 --%>

				<td>${board.bid }</td>
				<td><input type="text" name="title"
					value="${requestScope.board.title }"></td>
				<td>${empty requestScope.board.writerName ? '탈퇴한 사용자' : requestScope.board.writerName}</td>
				<td>${board.content}</td>
			</tr>
			<tr>
				<td colspan="4" align="right"><c:if
						test="${not empty userInfo and (board.writer==userInfo or userRoleInfo=='ADMIN' )}">
						<input type="submit" value="제목변경">
						<input type="button" value="글 삭제" onclick="del(this.form)">
						<input type="button" value="글 내용 변경" onclick="updateContent(this.form)">
					</c:if></td>
			</tr>
		</table>
	</form>

	<hr>
	<form action="writeReply.do" method="POST">
		<input type="hidden" name="bid" value="${requestScope.board.bid }">
		<input type="text" name="content" placeholder="댓글" required> <input
			type="submit" value="댓글작성">
	</form>
	<div id="reply">
		<ul>
			<c:if test="${empty requestScope.replyDatas }">
				<li>댓글이 비어있습니다.</li>
			</c:if>
			<c:forEach var="data" items="${requestScope.replyDatas }">


				<li>${ empty data.writerName ? "탈퇴한 사용자" : data.writerName}님: 
				<%-- ① 읽기용 내용 영역 --%>
					<span class="reply-content">${data.content}</span> 
					<%-- ② 수정용 폼 (처음에는 숨김) --%>
					<form class="reply-edit-form" action="updateReply.do" method="POST"
						style="display: none; margin-top: 4px;">
						<input type="hidden" name="bid" value="${requestScope.board.bid }">
						<input type="hidden" name="rid" value="${data.rid }"> <input
							type="text" name="content" value="${data.content }" size="40"
							required> <input type="submit" value="수정완료"> <input
							type="button" value="취소" onclick="cancelEdit(this)">
					</form> <br>


					<form name="replyChangeForm" method="POST">
						<input type="hidden" name="bid" value="${requestScope.board.bid }">
						<input type="hidden" name="rid" value="${data.rid }">

						<c:if
							test="${(not empty sessionScope.userInfo) and (data.writer==sessionScope.userInfo or sessionScope.userRoleInfo=='ADMIN')}">
							<input type="button" value="댓글변경" onclick="updateReply(this)">&nbsp;
			<input type="button" value="댓글삭제" onclick="deleteReply(this.form)">
						</c:if>

					</form>
				</li>
			</c:forEach>

		</ul>
	</div>

	<hr>

	<a href="mainPage.do">메인페이지</a>
</body>
</html>