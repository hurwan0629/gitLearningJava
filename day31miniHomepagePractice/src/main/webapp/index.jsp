<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 로그인form에서 submit를 했을때 action이 다시 이 페이지를 불러옴
	// 기본 페이지는 GET방식을 불려지기 때문에 식별 가능
	if("POST".equals(request.getMethod())){
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		if(id.equals("teemo") && pw.equals("1234")){
			session.setAttribute("id", id);
			response.sendRedirect("/day31miniHomepagePractice/homepage.jsp");
		}
		else if (id.equals("ari") && pw.equals("1234")) {
			session.setAttribute("id", id);
			response.sendRedirect("/day31miniHomepagePractice/homepage.jsp");
		}
		else {
			out.println("<script>alert('존재하지 않는 회원정보 입니다.');</script>");
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<h2>로그인 해주세요</h2>
	<form action="" method="POST">
		아이디 <input type="text" name="id" placeholder="아이디를 입력해주세요"><br>
		비밀번호 <input type="password" name="pw" placeholder="비밀번호를 입력하세요"><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>