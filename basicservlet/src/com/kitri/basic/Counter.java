package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/counter")
public class Counter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	int cnt = 0;

	public void init() {
		cnt = 0;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int cnt = 0;//여기다가 만들면 모든 사람이 1번째 방문자가 되버려서 전역변수로 빼버린다
		cnt++;
	
		String str = "" + cnt;//자릿수체크를 하려면 스트링으로 바꿔야한다
		
		while(str.length() < 8) {//8이 될때까지 cnt앞에 0을 계속 붙인다
			str = "0" + str;
		}
	
		response.setContentType("text/html;charset=UTF-8"); //한글깨짐고쳐줌
		PrintWriter out = response.getWriter(); 
		
		out.println("<html>");
		out.println("	<body>");
		out.println("당신은 ");
		
		for (int i = 0; i < str.length(); i++) {
//			//charAt() -> 인수번째의 문자를 읽어 냅니다  str.charAt(i) -> i번째의 문자를 읽어낸다
			out.println("<img src = \"/basicservlet/img/" + str.charAt(i) + ".png\" width=\"50\">");
		}
		out.println("번째 방문자입니다.");
		
		out.println("<br>당신은 " + cnt + "번째 방문자입니다.");
		out.println("	</body>");
		out.println("</html>");
	}

	
}
