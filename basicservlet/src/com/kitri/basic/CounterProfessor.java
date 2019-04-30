package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/counterp")
public class CounterProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	int cnt = 0;
	int totalLen = 8;
	
	public void init() {
		cnt = 0;
		totalLen = 0;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int cnt = 0;//여기다가 만들면 모든 사람이 1번째 방문자가 되버려서 전역변수로 빼버린다
		cnt++;
		String cntStr = cnt + "";//193  //자릿수체크를 하려면 스트링으로 바꿔야한다
		int cntLen = cntStr.length();//3
		int zeroLen = totalLen- cntLen; //5
	
		
		response.setContentType("text/html;charset=UTF-8"); //한글깨짐고쳐줌
		PrintWriter out = response.getWriter(); 
		
		out.println("<html>");
		out.println("	<body>");
		out.println("당신은 ");
		
		for (int i = 0; i<zeroLen; i++) 
			out.println("<img src = \"/basicservlet/img/0.png\" width=\"50\">");
		
		for (int i = 0; i<cntLen; i++) 
			out.println("<img src = \"/basicservlet/img/" + cntStr.charAt(i) + ".png\" width=\"50\">");
		
		
		out.println("번째 방문자입니다.");
		
		out.println("<br>당신은 " + cnt + "번째 방문자입니다.");
		out.println("	</body>");
		out.println("</html>");
	}

	
}
