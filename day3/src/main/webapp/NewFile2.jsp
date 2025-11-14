<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class="test.CalcBean" id="cb" />

<%--
<jsp:setProperty property="num1" name="cb" />
<jsp:setProperty property="num2" name="cb" />
<jsp:setProperty property="op" name="cb" />
 
 property에 *을 붙여주면 모든 파라미터를 가져와준다.
 ->
 name과 일치하는 property들은 모두 매칭시켜줌줌
 --%>
 <jsp:setProperty property="*" name="cb"/>
 
<%--
html의 name값과 위의 setProperty의 property의 이름이 같으면 자동 매핑 시켜준다.
위의 코드는 
CalcBean cb = new CalcBean() // 기본 생성자만 사용
cb.setNum1() //    <- setter안에 property의 값과 같은 name을 매칭시켜준다.
cb.setNum2()
cb.setOp()
 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습03</title>
</head>
<body>
<%
	cb.calc();
%>
	<h1>계산기</h1>
	<hr>
	<!-- action디폴트는 자기 자신페이지 가져오는것 -->
	<form method="POST">
		<input type="text" name="num1">
		<select name="op">
			<option>+</option>
			<option>-</option>
		</select>
		<input type="text" name="num2">
		<input type="submit" value="계산하기">
	</form>
	<br>
	<%-- <h3>계산결과 : <jsp:getProperty property="result" name="cb" /></h3>  --%>
	<h3>계산결과 : <%=cb.getResult() %></h3>
</body>
</html>