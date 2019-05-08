<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*,java.net.URLEncoder" %>
 <%!
public void init(){
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}
 %>
 <%
 Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;//셀렉트할거니까 resultset
	
	request.setCharacterEncoding("utf-8");
	
//	1.data get(아이디, 비번)
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	
	
//	2.Logic : 1의 data를 select 
//	select name
//	from member
//	where id = ? and pass = ?
	String name = null;
	try {
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");//db연결
		StringBuffer sql = new StringBuffer(); 			
		sql.append("select name \n");
		sql.append("from member \n");				
		sql.append("where id = ? and pass = ? \n");		
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, id);
		pstmt.setString(2, pass);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			//이 안에 왔다는건 아이디 비번을 얻어왔다는것
			name = rs.getString("name");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}


String root = request.getContextPath();
if(name != null) {
	response.sendRedirect(root + "/user/loginok.jsp?name=" + URLEncoder.encode(name,"UTF-8"));
} else {
	response.sendRedirect(root + "/user/loginfail.jsp");
}		

%>
