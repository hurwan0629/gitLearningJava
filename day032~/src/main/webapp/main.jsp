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
</head>
<body>



<div id="login">
<% 
	if(session.getAttribute("userInfo") != null){
		%>
		<h1><%=session.getAttribute("userInfo") %>님, 안녕하세요</h1>
		<a href="controller.jsp?command=LOGOUT">로그아웃</a>
		<a href="controller.jsp?command=MYPAGE">마이페이지</a>
		<%-- 
		write.jsp로 바로 가도 되는건가? 
		사용자가 userInfo가 null이면 튕겨야 하므로 controller로 가는게 맞겠네
		--%>
		<a href="controller.jsp?command=WRITEPAGE">글작성 하러 가기</a>
		
		<%
	}
	else{
		%>
		<form action="controller.jsp" method="GET">
			<input type="hidden" name="command" value="LOGIN">
			아이디 : <input type="text" name="mid" placeholder="아이디를 입력하세요." required><br>
			비밀번호 : <input type="password" name="mpw" placeholder="비밀번호를 입력하세요." required>
			<input type="submit" value="로그인">
		</form>
	  	<a href='controller.jsp?command=JOINPAGE'>회원가입</a>
	  	<%
	}
%>
</div>

<form action="controller.jsp" method="POST">
	<input type="hidden" name="command" value="MAINPAGE">
	<select name="condition">
		<option value="TITLE">제목으로 검색</option>
		<option value="WRITER">작성자로 검색</option>
	</select>
	<input type="text" name="keyword" required>
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
			<td><a href="controller.jsp?command=BOARDPAGE&bid=<%=data.getBid()%>"><%=data.getBid() %><a></td>
			<td><%=data.getTitle() %></td>
			<%-- 이게 작성자 mid가 아니라 member테이블의 mid와 같은 행의 name를 가져와야 하기 때문에 요청해야하나?해야하지. --%>
			<% 
			String writerName;
			if(data.getWriterName()==null){
				writerName= "탈퇴한 사용자";
			}
			else{
				writerName= data.getWriterName();
			}
			%>
			<td><%=writerName %></td>
			
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