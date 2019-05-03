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

@WebServlet("/register")
public class MemberRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}



	//회원가입은 양은 적지만 비밀번호가 있으니까 post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		request.setCharacterEncoding("UTF-8"); //한글처리
//		1.data get(이름, 아이디, 비번, 이메일1, 이메일2, 전번1, 전번2, 전번3, 우편번호, 주소, 상세주소)
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
		
		
//		2.Logic : 1의 data를 db에 insert
		
//		테이블이 두개니까 insert into가 아니라 insert all로 해야한다
//		insert all
//			into member (id, name, pass, emailid, emaildomain,emaildomain);
//			values(?, ?, ?, ?, ?, sysdate)
//			into member_detail (id,  zipcode, address, address_detail,  tel1, tel2, tel3);
//			values(?, ?, ?, ?, ?, ?, ?)
//		select * from dual;
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
		
		
//		3.response page : 2의 결과에 따라
//			3-1. !0 : 홍길동님 회원가입을 환영합니다.     //0이 아니면 성공
//			3-2.  0 : 서버문제로 회원가입이 실패하였습니다. //0이 나오면 에러 //나중에는 따로 에러잡는 페이지를 만드는게 좋다
//					   다음에 다시 시도하세요.
		response.setContentType("text/html;charset=UTF-8"); //코드상에서 한글이 깨질 경우 해결법(4가지중 하나) //순서가 중요하다
		PrintWriter out = response.getWriter(); //츨력객체만듬
		out.println("<html>");
		out.println("	<body>");
		if(cnt != 0) {
			out.println(name + " 님 회원가입을 환영합니다.");			
			out.println("로그인 후 모든 서비스를 이용할 수 있습니다.<br>");			
			out.println("<a href=\"/memberservlet/user/login.html\">로그인</a>");			
		}else {
			out.println("<font size = \"13\" color = \"red\">");		
			out.println("서버문제로 회원가입이 실패하였습니다.");		
			out.println("다음에 다시 시도하세요.");		
			out.println("");		
		}
		
		out.println("	</body>");
		out.println("</html>");
		
		
	
		
		
	}
}
