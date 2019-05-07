<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!

int cnt;
int totalLen;

public void init() {
	cnt = 0;
	totalLen = 8;
}
%>

<%
cnt++;
String cntStr = cnt + "";//193  //자릿수체크를 하려면 스트링으로 바꿔야한다
int cntLen = cntStr.length();//3
int zeroLen = totalLen- cntLen; //5
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align = "center">
당신은<br>
<%
for (int i = 0; i<zeroLen; i++){
%>
<img src="/basicjsp/img/0.png" width="50">
<%
}
for (int i = 0; i<cntLen; i++){ 
%>
<img src="/basicjsp/img/<%= cntStr.charAt(i) %>.png" width="50">
<%
}
%>
<br>번째 방문자입니다.
</div>
</body>
</html>