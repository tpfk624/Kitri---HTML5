package com.kitri.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	//드라이버 로딩안해도 실행이 된다(init)
	//이유: 실직적으로 sever.xml과 web.xml을 읽어들이는데 일단 읽는 순간 서버에 올라가니까 이미 우리가 실행할때에는 드라이브가 로딩 된 상태이다
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	//로그인은 양은 적지만 비밀번호가 있어서 post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;//셀렉트할거니까 resultset
		
		request.setCharacterEncoding("utf-8");
		
//		1.data get(아이디, 비번)
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		
//		2.Logic : 1의 data를 select 
//		select name
//		from member
//		where id = ? and pass = ?
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
		
			

//		3.response page : 2의 결과에 따라
//			3-1. name != null 
//				홍길동님 안녕하세요
//			3-2. name == null
//				아이디또는 비밀번호를 확인하세요
//				등록되지않은 아이디이거나, 아이디또는 비밀번호를 잘못 입력하셨습니다
		response.setContentType("text/html;charset=UTF-8"); //코드상에서 한글이 깨질 경우 해결법(4가지중 하나) //순서가 중요하다
		PrintWriter out = response.getWriter(); //츨력객체만듬
		out.println("<html>");
		out.println("	<body>");
		if(name != null) {
			out.println("<strong>" + name + "</strong>님 안녕하세요.<br>");			
			
		}else {
			out.println("<font size = \"13\" color = \"red\">");		
			out.println("아이디또는 비밀번호를 확인하세요<br>");		
			out.println("등록되지않은 아이디이거나, 아이디또는 비밀번호를 잘못 입력하셨습니다");		
			out.println("</font>");		
			out.println("<a href=\"/memberservlet/user/login.html\">로그인</a>");	
		}
		
		out.println("	</body>");
		out.println("</html>");
	}

}
