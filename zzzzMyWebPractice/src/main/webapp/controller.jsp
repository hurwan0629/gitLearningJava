<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String command = request.getParameter("command");

if(command.equals("MAINPAGE")){
	pageContext.forward("main.jsp");
}


%>