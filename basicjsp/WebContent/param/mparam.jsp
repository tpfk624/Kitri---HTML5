<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String name = request.getParameter("name");//��ȿ��
String id = request.getParameter("id");
int age = Integer.parseInt(request.getParameter("age")); // string으로 받아야해서 형변환해준다
String[] fruit = request.getParameterValues("fruit");

//2.logic
String color = age == 10 ? "pink" : "cyan";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=name%>(<font color="<%=color %>"> <%=id%></font>)님 안녕하세요.<br>
<% 
if (fruit == null) {
	out.println("좋아하는 과일이 없습니다");
} else {
	out.print("당신이 좋아하는 과일은 ");
	for (int i = 0; i < fruit.length; i++) {
		if (fruit.length - 1 == i) {//마지막
			out.print(fruit[i]);
		} else {
			out.print(fruit[i] + ",");
		}
	}
	out.println("입니다.");
}
%>
</body>
</html>