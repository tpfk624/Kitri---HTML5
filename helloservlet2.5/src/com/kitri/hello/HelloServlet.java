package com.kitri.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//html이 실행되는 곳은 만들어지는 곳은 tomcat 실행되는 곳은 client
		
		response.setContentType("text/html;charset=UTF-8"); //코드상에서 한글이 깨질 경우 해결법(4가지중 하나) //순서가 중요하다
		PrintWriter out = response.getWriter(); //츨력객체만듬
		out.println("<html>");
		out.println("	<body>");
		out.println("	hello servlet!!!<br>");
		out.println("	안녕 서블릿!!!"); //한글깨짐
		out.println("	</body>");
		out.println("</html>");
	}
}
