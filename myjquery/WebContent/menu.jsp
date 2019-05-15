<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <ul>
<%String id =(String)session.getAttribute("loginInfo");
	System.out.println(id);
  if(id == null){ //로그인 안한 경우
%>
    <li><a href="user/login.html">로그인</a></li>
    <li><a href="user/signup.html">가입</a></li>  
<%}else{ //로그인한 경우 %>
    <li><a href="logout">로그아웃</a></li>
<%} %>
  </ul>