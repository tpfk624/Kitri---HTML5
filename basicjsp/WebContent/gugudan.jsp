<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align = "center">


<h3>***구구단***</h3>
<table width = "800" height = "700" border="1">   
<%
for(int dan = 2; dan < 10; dan++) {
	String color = dan % 2 == 0 ? "pink" : "steelblue";
	out.println(			"<tr align = \"center\" bgcolor=\"" + color + "\">");
	for(int i = 1; i < 10; i++) {
		out.println("<td>" + dan + " * " + i + " = " + dan * i + "</td>");
	}
	out.println("</tr>");
}
%>
</table>


<hr>
<h3>*** 구구단 ***</h3>
<table width="800" height="700" border="1">
<%
for(int dan=2;dan<10;dan++) {
	String color = dan % 2 == 0 ? "pink" : "steelblue";
%>
<tr align="center" bgcolor="<%=color%>">
<%
	for(int i=1;i<10;i++) {
%>
	<td><%= dan + " * " + i + " = " + dan * i %></td>
<%
	}
%>
</tr>
<%
}
%>
</table>
<hr>
<h3>*** 구구단 ***</h3>
<table width="800" height="700" border="1">
<%
for(int dan=2;dan<10;dan++) {
	String color = dan % 2 == 0 ? "pink" : "steelblue";
%>
<tr align="center" bgcolor="<%=color%>">
<%
	for(int i=1;i<10;i++) {
%>
	<td><%= dan %> * <%= i %> = <%= dan * i %></td>
<%
	}
%>
</tr>
<%
}
%>
</table>
<hr>
</div>
</body>
</html>