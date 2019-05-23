<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- c를 사용하려고 선언?  -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl.jsp</title>
</head>
<body>


<hr>
<!-- 원래 코드였다면  null이었겠지만 여기서는 빈문자열로 바뀌어서 그게 문자 0으로 처리됨? -->	
<c:set var="num" value="${param.num}" />    <%-- int num=Integer.parseInt(request.getParameter("num")); --%>
요청전달데이터 num=${num}<br>
<c:if test="${num%2==0}" >	      <%--if(){} --%>          <!-- jstl에서 값이나 조건문은 el표기로 써야함  -->
짝수입니다.<br>
</c:if>





<hr>
<c:choose>
	<c:when test="${num%2==0}">
		짝수입니다
	</c:when>
	<c:otherwise>
		홀수입니다
	</c:otherwise>
</c:choose>




<hr>
<%--for(int i=1; i<=10; i++){} --%>	<!-- 반복문  -->
<c:forEach begin="1" end="10" step="1" var="i"> 	
${i}	
</c:forEach>




<hr>
<c:set var="total" value="0"/>
<c:forEach begin="1" end="10" var="i">
	<c:set var="total" value="${total+i}"/> <!-- 새로 선언니 아니고 저 위에 있는걸 재사용  -->
</c:forEach>
1~10합 : ${total}





<hr>
<%
List<String>list = new ArrayList<>();
list.add("one"); list.add("two");list.add("three");
request.setAttribute("list", list);
%>



<!--자바for문과 같은 효과 --> 
<%--for(String e:(String)request.getAttribute("list")){} --%>
<c:forEach var="e" items="${requestScope.list }">
${e}<br>
</c:forEach>


<c:forEach var="e" items="${requestScope.list}" varStatus="obj">
${obj.index} - ${e} : ${obj.count}회<br>
</c:forEach>




<hr><!-- map형태 다루는 예제  -->
<%
Map<String, Integer>map = new HashMap<>();
map.put("one", 1);
map.put("two", 2);
map.put("three", 3);
request.setAttribute("map", map);
%>
<c:forEach var="e1" items="${requestScope.map}">
	${e1.key} : ${e1.value}<br> 
</c:forEach>



</body>
</html>