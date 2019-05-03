package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ggd")
public class Gugudan extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	
	@Override
	public void init() throws ServletException {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
//		for(int i = 1; i <= 9; i++) {
//			for(int dan = 2; dan <= 9; dan++) {
//			System.out.print(dan + " * " + i + " = " + dan * i + "    ");
//			}
//			System.out.println();
//		}
		
		
		
		out.println("<html>");
		out.println("	<body>");
		out.println("		<table border=\"1\">");
		out.println("			<caption>***구구단***</caption>");		
		for(int i = 1; i <= 9; i++) {
			out.println(			"<tr>");
			for(int dan = 2; dan <= 9; dan++) {
				String gbbresult = dan + " * " + i + " = " + dan * i;
//				out.println("<td bgcolor=" +  (dan % 2 == 0 ? "pink" : "steelblue") +  gbbresult + "</td>");
				out.println("<td>" +  gbbresult + "</td>");
				
			}
			out.println("</tr>");
		}
		out.println("	</body>");
		out.println("</html>");
		
	}

	

}
