<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, model.dto.BoardDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="login">
<% 
	if(session.getAttribute("userInfo") != null){
		%>
		<a href="controller.jsp?command=LOGOUT">로그아웃</a>
		<a href="마이페이지 페이지로 가렴">마이페이지</a>
		<a href="글작성 페이지로 가렴">글작성</a>
		
		<%
	}
	else{
		%>
		<form action="controller.jsp" method="POST">
			<input type="hidden" name="command" value="LOGIN">
			아이디 : <input type="text" name="mid" placeholder="아이디를 입력하세요." required><br>
			비밀번호 : <input type="password" name="mpw" placeholder="비밀번호를 입력하세요." required>
			<input type="submit" value="로그인">
		</form>
	  	<a href='회원가입 페이지로 가렴'>회원가입</a>
	  	<%
	}
%>
</div>

<form action="검색">
	제목/이름
	<input type="submit" value="글 검색">
</form>

<table border="1">
	<caption>전체 글 출력</caption>
	<tr>
		<td>글 번호</td>
		<td>제목</td>
		<td>작성자</td>
	</tr>
	<%
		// 액션태그 useBean board
		// 액션태그 useBean
		// ArrayList<BoardDTO> datas = boardDAO.selectAll(boardDTO);
		// datas
		for(BoardDTO data:(ArrayList<BoardDTO>)request.getAttribute("datas")){
	%>
		<tr>
			<td><a href="글상세 페이지로 가렴"><%=data.getBid() %><a></td>
			<td><%=data.getTitle() %></td>
			<td><%=data.getWriter() %></td>
		</tr>
	<%
		}
	%>
	</tr>
</table>
검색 : 제목/이름
<>글상세</a>
 
 
</body>
</html>