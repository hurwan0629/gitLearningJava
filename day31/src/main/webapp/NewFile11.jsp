<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습06</title>
</head>
<body>
	<h2>구매한 상품 목록</h2>
	<ul>
		<%
			ArrayList<String> cartList = (ArrayList<String>)session.getAttribute("cartList");
			if(cartList!=null){
				for(String productName : cartList){
					// 세션에 저장되어있을때에는 인코딩을 할 필요 없고
					// 파라미터 값을 가져올때에는 인코딩이 필요함
				out.println("<li>" +productName+ " </li>");
				}	
			}
			else{
				out.println("<li>장바구니에 상품이 없습니다...</li>");
			}
			
		%>
	</ul>
</body>
</html>