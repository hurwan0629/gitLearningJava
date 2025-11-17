<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
	<h2>장바구니 목록</h2>
	<ul>
		<%
			ArrayList<String> cartList = (ArrayList<String>)session.getAttribute("cartList");
			if(cartList!=null){
				for(String product:cartList){
					out.println("<li>"+product+"</li>");
				}	
			}
			else{
				out.println("<li>장바구니에 상품이 존재하지 않습니다</li>");
			}
			
		%>
	</ul>
	<a href="ProductList.jsp">쇼핑몰로 돌아가기</a>
</body>
</html>