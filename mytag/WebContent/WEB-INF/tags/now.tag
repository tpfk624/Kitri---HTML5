<%@tag import="java.util.Date"%> <!-- 태그전용 파일에서만 쓰이는 지시자 -->
<%@tag import="java.text.SimpleDateFormat"%>
<%@ tag body-content="empty" pageEncoding="UTF-8"%> <!-- empty-바디없는 태그 바디를 사용하지않겠다  -->
<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %><!-- 이 태그가 할 일  -->





