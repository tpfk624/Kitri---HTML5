package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ggdp")
public class GugudanProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	
	@Override
	public void init() throws ServletException {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("	<body>");
		out.println("		<div align=\"center\">");
		out.println("		<h3>***구구단***</h3>");
		out.println("		<table widht=\"800\" height=\"700\" border=\"1\">");		
		for(int dan = 2; dan < 10; dan++) {
			String color = dan % 2 == 0 ? "pink" : "steelblue";
			out.println(			"<tr align = \"center\" bgcolor=\"" + color + "\">");
			for(int i = 1; i < 10; i++) {
				out.println("<td>" + dan + " * " + i + " = " + dan * i + "</td>");
			}
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("	</div>");
		out.println("	</body>");
		out.println("</html>");
	}
}
