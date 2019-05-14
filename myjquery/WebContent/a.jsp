<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%> <!-- 크롬에서는 한글이깨지지x 인터넷익스플로어에서는 깨지니 넣어준다 -->
<%String id = 
		request.getParameter("id");
	String name =
		request.getParameter("name");
%>
<%
Thread.sleep(10);
//Thread.sleep(10*1000);
%><%= id %>(<%=name %>)님 반갑습니다. <!-- 줄바꿈이나 들여쓰기가 그대로 적용되니 되도록 안하는게 좋다 -->