package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dbcounter")
public class DBCounter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int cnt = 0;

	@Override
	public void init() throws ServletException {
		cnt = 0;
		
		// 1.드라이버로딩
		try {
			Class.forName("orcle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 2.DB연결
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			String sql = "";
			sql += "select no \n";
			sql += "from counter";
			sql += "";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		String cntStr = cnt + ""; // cnt를 string로 바꿔줌

		while (cntStr.length() < 8) {
			cntStr = "0" + cntStr;
		}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<body>");
		out.println("당신은 ");

		for (int i = 0; i < cntStr.length(); i++) {
			out.println("<img src = \"/basicservlet/img/" + cntStr.charAt(i) + ".png\" width=\"50\">");
		}

		out.println("번째 방문자입니다");
		out.println("	</body>");
		out.println("</html>");
	}

}
