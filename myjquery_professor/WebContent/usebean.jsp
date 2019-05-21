<%@page import="com.kitri.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usebean.jsp</title>
</head>
<body>


<%-- <%
//1. request의 속성(이름: "c", 타입:com.kitri.dto.Customer)얻기
Customer c = (Customer)request.getAttribute("c");

//2. 1의 속성이 null인 경우
//	Customer 객체 생성하여 c 참조변수에 대입
//	c참조변수를 request의 속성(이름:"c")로 추가
if(c == null){
	c = new Customer();
	request.setAttribute("c", c);
}
%> --%>

<!-- 위에 있는 코드와 같은 역할을 한다 -->
<jsp:useBean id="c" class= "com.kitri.dto.Customer" scope="request" />


<%-- <%
c.setId("id1");
%> --%>

<!-- 위에 있는 코드와 같은 역할을 한다 -->
<jsp:setProperty name = "c" property="id" value="id1"/>


<%-- <%
c.setName(request.getParameter("n"));
 %> --%>
 
 <!-- 위에 있는 코드와 같은 역할을 한다 -->
 <jsp:setProperty name="c" property="name" param ="n" />
 
 
<%--  <%=c.getId() %> --%>

<!-- 위에 있는 코드와 같은 역할을 한다 -->
<jsp:getProperty name="c" property="id" />
 
 
 
 
 
</body>
</html>