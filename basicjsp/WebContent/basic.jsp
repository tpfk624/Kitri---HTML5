<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! 
String name;
int age;

public void init(){
	name = "고세라";
	age = 24;
}
%>
<%
String color = age >= 18 ? "red" : "blue";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align = "center">
<% out.print(name);%>(<font color="<%out.print(color);%>"><%out.print(age); %></font>님 안녕하세요.<br>
<%=name%>(<font color= "<%=color%>"><%=age%></font>)님 안녕하세요. 
</div>
</body>
</html>