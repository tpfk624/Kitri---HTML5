package com.kitri.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveUrl {
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path); //path 앞에 루트를 붙여줌
		
	}
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);//리다이렉트는 어디든 갈 수 있지만 포워드는 내 프로젝트 안에 있는 파일로만 이동이 가능하다
		dispatcher.forward(request, response);
		
	}
}
