package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.service.MemberServiceImpl;
import com.kitri.util.MoveUrl;
import com.kitri.util.SiteConstance;

//사용자가 뭘 요구하는지랑 어디로가라만 처리한다
//나머지 웹 작업은 다른 컨트롤러가 처리한다

@WebServlet("/user")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String act = request.getParameter("act"); // act를 받아라

		
		String path = "/index.jsp"; //디폴트를 인덱스로 보내는걸로 한다
		
//		if(act.equals("")) { //이걸로하면 NullPoint Exception이 뜬다 이걸 사용하려면 if문에서 널이 아니라는 조건을 주고 사용해야함
		
		if ("mvjoin".equals(act)) { // 무언가 값을 받아내려면 이 방법이 더 좋다
			MoveUrl.redirect(request, response, "/user/member/member.jsp");
		} else if ("mvlogin".equals(act)) {
			MoveUrl.redirect(request, response, "/user/login/login.jsp");
		} else if ("idcheck".equals(act)) {
			String sid = request.getParameter("sid");
			String resultXML = MemberServiceImpl.getMemberService().idCheck(sid);

			response.setContentType("text/xml;charset=UTF-8"); // 나는 한글로 보냈지만 너는 xml로 받아라 형식은 utf-8
			PrintWriter out = response.getWriter();
			out.print(resultXML);

		} else if ("zipsearch".equals(act)) {
			String doro = request.getParameter("doro");
//			System.out.println("검색 도로명: " + doro);
			String resultXML = MemberServiceImpl.getMemberService().zipSearch(doro);
//			System.out.println(resultXML);
			response.setContentType("text/xml;charset=UTF-8"); // 나는 한글로 보냈지만 너는 xml로 받아라 형식은 utf-8
			PrintWriter out = response.getWriter();
			System.out.println(resultXML);
			out.print(resultXML);
			
		} else if ("register".equals(act)) {
			//멤버컨트롤 레지스터 호출
			path = MemberController.getMemberController().register(request, response);
			MoveUrl.forward(request, response, path); //가라 //sendredirect는 가기전에 다 버리고가서 우리가 넣은 거를 전달할수없다//forward는 넘어감
			
		} else if ("login".equals(act)) {
			path = MemberController.getMemberController().login(request, response);
			MoveUrl.forward(request, response, path); //가라 //sendredirect는 가기전에 다 버리고가서 우리가 넣은 거를 전달할수없다//forward는 넘어감
		} else if ("logout".equals(act)) {
			path = MemberController.getMemberController().logout(request, response);
			MoveUrl.redirect(request, response, path); //가라
		} else if ("".equals(act)) {
			
		} else if ("".equals(act)) {

		} else if ("".equals(act)) {

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(SiteConstance.ENCODE);
		doGet(request, response);
	}

}
