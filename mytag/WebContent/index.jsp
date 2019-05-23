<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="my" uri="http://kitri.com/my" %>    
<%@taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customtag.jsp</title>
</head>
<body>
<my:welcome></my:welcome>
<hr>
<tf:now/> <!-- now : tag파일 이름, empty-바디없는 태그 바디를 사용하지않겠다  -->
<hr>
<tf:pageGroup current="3" start="1" end="5" url="boardlist"/>
</body>
</html>




