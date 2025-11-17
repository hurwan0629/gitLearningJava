<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
    
<%
	String mid = request.getParameter("mid");

	// 로그인 페이지에서 넘어왔을때 만 세션 설정
	if(mid != null){ 
		// 매번 로그인 하는것이 아니라 
		// 로그인 시도를 했니? ㅇㅇ -> 그러면 세션에 로그인 정보를 저장
		session.setAttribute("mid", mid);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습05</title>
</head>
<body>
	
	<form action="NewFile7.jsp" method="POST">
		<%-- 현재 로그인한 사람의 이름 --%>
		<%= session.getAttribute("mid") %>님 | <input type="text" name="msg"> <input type="submit" value="글 작성">
	</form>
	<hr>
	<ul>
	<%
		// .getAttribute가 Object 타입이기 때문에 다운 캐스팅을 해줘야한다.
		ArrayList<String> msgs = (ArrayList<String>)session.getAttribute("msgs");
		
		// 만약에 댓글을 추가하려 할때 msgs가 비어있으면 만들지 않음 
		// 절대 view파트에서 new ArrayList<>(); 를 하지 않는다.
		if(msgs != null){
			for(String msg : msgs){
				out.println("<li>"+msg+"</li>");
			}	
		}
		else {
			out.println("<li>출력할 메세지가 없습니다...</li>");
		}
	%>
	</ul>
</body>
</html>