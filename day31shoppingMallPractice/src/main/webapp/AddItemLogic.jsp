<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%	
	request.setCharacterEncoding("UTF-8");
	String isAdd = "상품0개";
	String[] products = request.getParameterValues("product");
	// 1. 상품을 0개 추가할때
	// 2. ~ 상품을 3개 추가할 때
	ArrayList<String> cartList = (ArrayList<String>)session.getAttribute("cartList");
	if(cartList==null){
		cartList=new ArrayList<>();
		session.setAttribute("cartList",cartList);	
	}
	// 만약에 추가할 상품이 존재하면
	if(products!=null){
		isAdd="상품"+products.length+"개";
		for(String product:products){
			// 상품이 이미 존재하지 않는다면
			if(!cartList.contains(product)){
				cartList.add(product);	
			}
		}
	}
%>
<script>
	alert('<%=isAdd%>가 추가되었습니다!');
	location.href="ProductList.jsp";
</script>