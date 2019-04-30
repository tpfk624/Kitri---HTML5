package com.kitri.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//이 작업들을 was가 대신해줘서 우리는 서비스 호출만 하면 된다
//생성자와 init()은 하나만 만들면 되는데 서비스는 계속 실행
//destory()는 서버가 끝날 때여서 사실상 할 일이 거의 없다
//서블릿의 초기화작업은 주로 init(생성자에서 잘 하지 않음)

@WebServlet("/life")
public class LifeCycleTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	public LifeCycleTest() {
		System.out.println("생성자() 호출!!!");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init() 호출!!!");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 호출!!!");
	}

	@Override
	public void destroy() {
		System.out.println("destroy() 호출!!!");
	}
}
