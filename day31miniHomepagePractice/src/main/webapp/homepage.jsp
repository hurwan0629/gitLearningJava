<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%
	String mid= (String)session.getAttribute("mid");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지</title>
</head>
<body>
	<h2>환영합니다"<%=mid %>"님</h2>
	<%-- POST 로 request 보내버리기  --%>
	<form action="/day31miniHomepagePractice/messageSave.jsp" method="POST">
		<textarea name="content" rows="10" cols="50" 
		placeholder="여기에 글을 입력하세요"></textarea><br>
		<input type="submit" value="글 올리기">
	</form>
	<ul>
		<%
			ArrayList<String> contentList=(ArrayList<String>)application.getAttribute("contentList");
			if(contentList!=null){
				for(String content:contentList){
					out.print("<li>"+content+"</li>");	
				}	
			}
			else{
				out.print("<li>작성된 글이 없습니다</li>");
			}
		%>
	</ul>
</body>
</html>