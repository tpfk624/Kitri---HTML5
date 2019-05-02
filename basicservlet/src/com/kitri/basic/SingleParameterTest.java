package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/singleparam")
public class SingleParameterTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.data get 
		//넘어오는 값 = parameter //파라미터의 이름은 name으로 넘어간다(id x)
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));//string으로 받아야해서 형변환해준다
		
//		2.logic
		String color = age == 10 ? "pink" : "cyan";
		
		
//		3.response page : 안효인(java2)님 안녕하세요 
//		10대 이하이면 아이디 빨강, 10대 이상이면 아이디 파랑색
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
		out.println(name +"(<font color=\"" + color + "\">"+ id + "</font>)님 안녕하세요");
		out.println("	</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.data get 
		request.setCharacterEncoding("utf-8");//한글깨짐을 고쳐줌//포스트에서만 가능
		
		//넘어오는 값 = parameter //파라미터의 이름은 name으로 넘어간다(id x)
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));//string으로 받아야해서 형변환해준다
		
//		2.logic
		String color = age == 10 ? "pink" : "cyan";
		
		
//		3.response page : 안효인(java2)님 안녕하세요 
//		10대 이하이면 아이디 빨강, 10대 이상이면 아이디 파랑색
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
		out.println(name +"(<font color=\"" + color + "\">"+ id + "</font>)님 안녕하세요");
		out.println("	</body>");
		out.println("</html>");
	}

}
