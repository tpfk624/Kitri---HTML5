<%@page import="com.kitri.util.MoveUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.member.model.MemberDto"%>
<%@ include file="/template/header.jsp" %> 
<%
MemberDto memberDto = (MemberDto)session.getAttribute("userInfo"); //사용자의 정보를 request에 담으면 안된다 session에 담아야함 //세션은 시간이 지나도 내 정보를 담고 있는??
if(memberDto != null){
%>
<script type ="text/javascript">
function deleteMember(){
	if(confirm("정말로 탈퇴???")){
		document.location.href = "<%=root%>/user?act=deletemember";	
	}
}

</script>
<strong><%=memberDto.getName()%>(<%=memberDto.getId()%>)</strong>님 안녕하세요.<br>
<a href="<%=root%>/user?act=logout">로그아웃</a>
<a href="<%=root%>/user?act=mvmodify">정보수정</a>
<a href="#" onclick="javascript:deleteMember();">회원탈퇴</a>
<%
	if("tpfk624".equals(memberDto.getId())){
%>	
<a href="<%=root%>/admin?act=memberlist">관리자</a>
<% 
	}
}else{
	MoveUrl.redirect(request, response, "/user?act=mvlogin");
}
%>
<%@ include file="/template/footer.jsp" %> 

<!-- 
회원탈퇴
t{
	conn = ...;
	conn.setAC(false);
	
	d
	pstmt.exc...();
	pstmt.close...();
	
	
	
	d
	pstmt.conn.pr...
	pstmt.e..
	conn.commit();
}c {
	conn.rollback();

}

 -->