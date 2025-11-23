<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, model.dto.BoardDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>

<div id="login">
<%
	if(session.getAttribute("userInfo") != null){ // 로그인했다면,
%>
	<h1><%=session.getAttribute("userInfo")%>님, 안녕하세요! :D</h1>
	<a href="controller.jsp?command=LOGOUT">로그아웃</a>
	<a href="controller.jsp?command=MYPAGE">마이페이지</a>
	<a href="controller.jsp?command=WRITEPAGE">글작성</a>
<%
	}
	else{
%>
	<form action="controller.jsp" method="POST">
		<input type="hidden" name="command" value="LOGIN">
		<input type="text" name="mid" placeholder="아이디를 입력하세요." required>
		<input type="password" name="mpw" placeholder="비밀번호를 입력하세요." required>
		<input type="submit" value="로그인">
	</form>
	<a href="controller.jsp?command=JOINPAGE">회원가입</a>
<%
	}
%>
</div>

<form action="검색">
	제목/이름
	<input type="submit" value="글 검색">
</form>
<table border="1">
	<tr>
		<td>글 번호</td>
		<td>제목</td>
		<td>작성자</td>
	</tr>
	<%
	for(BoardDTO data:(ArrayList<BoardDTO>)request.getAttribute("datas")){
	%>
	<tr>
		<td><a href="controller.jsp?command=BOARDPAGE&bid=<%=data.getBid()%>"><%=data.getBid()%></a></td>
		<td><%=data.getTitle()%></td>
		<td><%=data.getWriter()%></td>
	</tr>
	<%
	}
	%>
</table>

</body>
</html>