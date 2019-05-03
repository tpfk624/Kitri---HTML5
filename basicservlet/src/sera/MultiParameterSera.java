package sera;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/multiparamsera")
public class MultiParameterSera extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.data get
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		String[] fruit = request.getParameterValues("fruit");
	
//		2.logic
		String color = age == 10 ? "red" : "blue";
		
		
//		3.response page
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
		
		if(fruit == null) {
			out.println("좋아하는 과일이 없습니다.");
		}else {
			out.println("당신이 좋아하는 과일은");
			for (int i = 0; i < fruit.length; i++) {
				if(fruit.length - 1 == i) {
					out.print(fruit[i]);
				}else {
					out.println(fruit[i] + ",");
				}
			}out.println("입니다");
		}
		
		
		
		out.println("	</body>");
		out.println("</html>");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
