package com.kitri.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/multiparam")
public class MultiParameterTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		1.data get 
		// 넘어오는 값 = parameter //파라미터의 이름은 name으로 넘어간다(id x) //servlet request의 메소드
		// getParameter사용
		String name = request.getParameter("name");//��ȿ��
//		톰캣8.5에서는 한글이 깨지지않지만 낮은 버전에서는 한글이 깨진다 이럴 경우 해결법(알아만두기)
//		System.out.println("1 name ==== " + name);
//		byte b[] = name.getBytes("iso-8859-1");//%BE%C8%C8%BF%C0%CE
//		name = new String(b, "euc-kr");
//		
//		System.out.println("2 name ==== " + name);
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age")); // string으로 받아야해서 형변환해준다
		String[] fruit = request.getParameterValues("fruit");

//		2.logic
		String color = age == 10 ? "pink" : "cyan";

//		3.response page : 안효인(java2)님 안녕하세요 
//		안효인(java2)님 안녕하세요.
//		당신이 좋아하는 과일은 사과입니다.
//		당신이 좋아하는 과일은 사과, 수박입니다.
//		당신이 좋아하는 과일은 없습니다.
//		10대 이하이면 아이디 빨강, 10대 이상이면 아이디 파랑색
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
		out.println(name + "(<font color=\"" + color + "\">" + id + "</font>)님 안녕하세요 <br>");

		if (fruit == null) {
			out.println("좋아하는 과일이 없습니다");
		} else {
			out.print("당신이 좋아하는 과일은 ");
			for (int i = 0; i < fruit.length; i++) {
				if (fruit.length - 1 == i) {//마지막
					out.print(fruit[i]);
				} else {
					out.print(fruit[i] + ",");
				}
			}
			out.println("입니다.");
		}
		out.println("	</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//한글깨짐을 고쳐줌//포스트에서만 가능
		doGet(request, response);
	}
}



//http://localhost/basicservlet/multiparam?name=%EC%95%88%ED%9A%A8%EC%9D%B8&id=java2&age=30

//   euc-kr				     utf-8
//안효인		%BE%C8%C8%BF%C0%CE	 		��ȿ��



