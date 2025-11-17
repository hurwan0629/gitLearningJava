<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" %>
<%
	request.setCharacterEncoding("UTF-8");
	// 이전 페이지에서 데이터(무엇을 구매했는지) 받아오기
	String product = request.getParameter("product");
	
	// 구매한 상품을 장바구니(데이터베이스)에 추가
	ArrayList<String> cartList = (ArrayList<String>)session.getAttribute("cartList");
	if(cartList==null){
		cartList = new ArrayList<String>();
		session.setAttribute("cartList", cartList);
	}
	cartList.add(product);
	// 안내창 띄우기
	// 화면 이동
%>
<script>
	alert('<%=product %>을 장바구니에 추가했습니다!');
	location.href="NewFile9.jsp";
	// history.go(-1);
</script>