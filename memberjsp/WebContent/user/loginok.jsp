<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/template/header.jsp" %> 
<%
String name = request.getParameter("name");
%>
<strong><%=name%></strong>님 안녕하세요.<br>

<%@ include file="/template/footer.jsp" %> 