package com.kitri.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/gbwrite")
public class GuestBookWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//data ��������
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
	
		
		int cnt =0;
		//logic
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.1.15:1521:orcl","kitri","kitri");
			StringBuffer sql = new StringBuffer();
			
			sql.append("insert into guestbook(seq, name, subject, content, logtime) \n");
			sql.append("values(guestbook_seq.nextval, ?, ?, ?, sysdate)" );
			
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(pstmt!=null)
					pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		//request page
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("</html>");
		out.println("<body>");
		
		if(cnt!=0) {
		out.println("����� �Ϸ�Ǿ����ϴ�.<br>");
		out.println("�� �� �������� <br>");
		out.println("<a href=\"/guestbookservlet/gblist\">�� ���</a>");
		}else if(cnt==0) {
		out.println("���� ������ ���� ��ϵ��� �ʾҽ��ϴ�.<br>");
		out.println("������ �ٽ� �õ����ּ���");
		}
		
		out.println("</body>");
		out.println("<html>");
		
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
