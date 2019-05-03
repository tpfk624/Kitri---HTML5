package sera;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/singleparamsera")
public class SinglePareameterSera extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		1. data get
//		넘어오는값 = parameter
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		
//		2. logic
		String color = age == 10 ? "pink" : "blue";
		
//		3. response page : 안효인(java2)님 안녕하세요 
//		10대 이하이면 아이디 빨강, 10대 이상이면 아이디 파랑색
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<body>");
//		out.println(name + "(<font color = 'color'; + id + '</font>"+")" + "님 안녕하세요");
		out.println("	</body>");
		out.println("</html>");
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
