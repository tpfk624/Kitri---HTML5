package com.kitri.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hs")//이 부분을 추가하면 2.5에서 xml한 부분을 대체가능함
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html;charset=UTF-8"); //코드상에서 한글이 깨질 경우 해결법(4가지중 하나) //순서가 중요하다
		PrintWriter out = response.getWriter(); //츨력객체만듬
		out.println("<html>");
		out.println("	<body>");
		out.println("	hello servlet!!!<br>");
		out.println("	안녕 서블릿 3.0 !!!"); //한글깨짐
		out.println("	</body>");
		out.println("</html>");
	}

	

}
