package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbcounterp")
public class DBCounterprofessor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int cnt;
	int totalLen;
	
	public void init() {
		cnt = 0;
		totalLen = 8;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			String sql = "";
			sql += "select no \n";
			sql += "from counter";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			cnt = rs.getInt(1);
			
			sql = "update counter \n";
			sql += "set no = no + 1 \n";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		String cntStr = cnt + "";//137
		int cntLen = cntStr.length();//3
		int zeroLen = totalLen - cntLen;//5
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
		out.println("당신은<br>");
		
		for(int i=0;i<zeroLen;i++)
			out.println("<img src=\"/basicservlet/img/0.png\" width=\"50\">");
		
		for(int i=0;i<cntLen;i++)
			out.println("<img src=\"/basicservlet/img/" + cntStr.charAt(i) + ".png\" width=\"50\">");
		
		out.println("<br>번째 방문자입니다.");
		out.println("	</body>");
		out.println("</html>");
	}

}
