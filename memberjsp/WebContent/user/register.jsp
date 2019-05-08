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

request.setCharacterEncoding("UTF-8"); //한글처리
//1.data get(이름, 아이디, 비번, 이메일1, 이메일2, 전번1, 전번2, 전번3, 우편번호, 주소, 상세주소)
String name = request.getParameter("name");
String id = request.getParameter("id");
String pass = request.getParameter("pass");
String emailid = request.getParameter("emailid");
String emaildomain = request.getParameter("emaildomain");
String tel1 = request.getParameter("tel1");
String tel2 = request.getParameter("tel2");
String tel3 = request.getParameter("tel3");
String zipcode = request.getParameter("zipcode");
String address = request.getParameter("address");
String addressdetail = request.getParameter("address_detail");


//2.Logic : 1의 data를 db에 insert

//테이블이 두개니까 insert into가 아니라 insert all로 해야한다
//insert all
//	into member (id, name, pass, emailid, emaildomain,emaildomain);
//	values(?, ?, ?, ?, ?, sysdate)
//	into member_detail (id,  zipcode, address, address_detail,  tel1, tel2, tel3);
//	values(?, ?, ?, ?, ?, ?, ?)
//select * from dual;
int cnt = 0;
try {
	conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
	StringBuffer sql = new StringBuffer(); 
	sql.append("insert all ");
	sql.append("	into member (id, name, pass, emailid, emaildomain, joindate)\n");
	sql.append("	values(?, ?, ?, ?, ?, sysdate)\n");
	sql.append("	into member_detail (id,  zipcode, address, address_detail, tel1, tel2, tel3)\n");
	sql.append("	values(?, ?, ?, ?, ?, ?, ?)\n");
	sql.append("select * from dual\n");
	
	pstmt = conn.prepareStatement(sql.toString());
	int idx = 0; //중간에 뭐가 빠지거나 들어와도 소스 수정필요x
	pstmt.setString(++idx, id);
	pstmt.setString(++idx, name);
	pstmt.setString(++idx, pass);
	pstmt.setString(++idx, emailid);
	pstmt.setString(++idx, emaildomain);
	pstmt.setString(++idx, id);
	pstmt.setString(++idx, zipcode);
	pstmt.setString(++idx, address);
	pstmt.setString(++idx, addressdetail);
	pstmt.setString(++idx, tel1);
	pstmt.setString(++idx, tel2);
	pstmt.setString(++idx, tel3);
	//여기까지 쿼리문 실행을 할 값을 집어넣음
	
	//실행시점에서는 sql문장을 안가져가서 값만 검사함 statement와의 차이점으로 훨씬 빠르다
	cnt = pstmt.executeUpdate();
	
} catch (SQLException e) {
	e.printStackTrace();
}finally {
		try {
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

String root = request.getContextPath();
if(cnt != 0) {
	response.sendRedirect(root + "/user/registerok.jsp?name=" + URLEncoder.encode(name,"UTF-8")); //한글깨짐 //쿼리스트링을 만들어서 보냄
} else {
	response.sendRedirect(root + "/user/registerfail.jsp");
}		
%>
