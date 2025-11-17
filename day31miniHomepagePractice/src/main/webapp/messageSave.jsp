<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%
	request.setCharacterEncoding("UTF-8");
	String content = request.getParameter("content");

	// 글을 작성해야지만 저장시켜주기
	if(content!=null && !content.trim().equals("")){
		ArrayList<String> contentList = (ArrayList<String>)application.getAttribute("contentList");
		// contentList가 null이면 새로 값 할당해주기
		if(contentList==null){
			contentList = new ArrayList<>();
		}
		contentList.add(content);	
		// 어플리케이션 새로 덮어씌워주기
		application.setAttribute("contentList", contentList);
		
		String id = (String)session.getAttribute("id");
		out.print("<script>alert('"+id+"글 등록 완료!');</script>");
		
	}
	// 글이 없으면
	else {
		out.print("<script>alert('글을 작성해주세요!');</script>");
	}
	out.print("<script>location.href='homepage.jsp';</script>");
	
%>