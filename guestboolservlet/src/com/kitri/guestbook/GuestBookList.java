package com.kitri.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gblist")
public class GuestBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.data 얻어오기 (db에서) & logic
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name = null;
		String logtime = null;
		int seq = 0;
		String subject = null;
		String content = null;
		
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
		ArrayList<String> row = null; 
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.15:1521:orcl","kitri","kitri");
			StringBuffer sql = new StringBuffer();
			sql.append("select * \n");
			sql.append("from guestbook");
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				row = new ArrayList<String>();
				
				seq = rs.getInt("seq");
				row.add(seq+"");
				name = rs.getString("name");
				row.add(name);
				subject = rs.getString("subject");
				row.add(subject);
				content = rs.getString("content");
				row.add(content);
				logtime = rs.getString("logtime");
				row.add(logtime);
				
				table.add(row);
			}
			System.out.println(table.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs!=null)
					rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		//2.request page
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("<title>글목록</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\">");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
		out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\"></script>");
		out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\"></script>");
		out.println("<script type=\"text/javascript\">");
		out.println("</script>");
		out.println("</head>");
		out.print("<body>");
		out.print("<div class=\"container\" align=\"center\">");
		out.print("<div class=\"col-lg-8\" align=\"center\">");
		out.print("<h2>글목록</h2>");
		out.print("<table class=\"table table-borderless\">");
		out.print("<tr>");
		out.print("<td align=\"right\"><a href=\"/guestbookservlet/guestbook/write.html\">글쓰기</a></td>");
		out.print("</tr>");
		out.print(" </table>");
		out.print(" <table class=\"table table-active\">");
		out.print("<tbody>");
		
		for (int j = 0; j < table.size(); j++) {
			int i = 0;
			out.print(" <tr>");
			out.print(" <td>작성자 : " + table.get(j).get(i+1) +"</td>");
			out.print(" <td style=\"text-align: right;\">작성일 :"+ table.get(j).get(i+4) +"</td>");
			out.print("</tr>");
			out.print(" <tr>");
			out.print("<td colspan=\"2\"><strong>" + table.get(j).get(i)+"."+ table.get(j).get(i+2) +"</strong></td>");
			out.print(" </tr>");
			out.print("<tr>");
			out.print("<td colspan=\"2\">" + table.get(j).get(i+3) +"</td>");
			out.print("</tr>");
		}
		out.print(" </tbody>");
		out.print(" </table>");
		out.print("</div>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		
	}


	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	

}
