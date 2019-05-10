package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.member.model.MemberDetailDto;
import com.kitri.member.model.MemberDto;
import com.kitri.member.model.service.MemberService;
import com.kitri.member.model.service.MemberServiceImpl;
import com.kitri.util.SiteConstance;


public class MemberController {
	
	//싱글톤패턴 만들기
	private static MemberController memberController;
	
	static {
		memberController = new MemberController();
	}
	
	private MemberController() {}
	
	public static MemberController getMemberController() {
		return memberController;
	}
	
	public String register(HttpServletRequest request, HttpServletResponse response) {
		String path = "/index.jsp";
		MemberDetailDto memberDetailDto = new MemberDetailDto();
		memberDetailDto.setName(request.getParameter("name"));
		memberDetailDto.setId (request.getParameter("id"));
		memberDetailDto.setPass(request.getParameter("pass"));
		memberDetailDto.setEmailid(request.getParameter("emailid"));
		memberDetailDto.setEmaildomain(request.getParameter("emaildomain"));
		memberDetailDto.setTel1(request.getParameter("tel1"));
		memberDetailDto.setTel2(request.getParameter("tel2"));
		memberDetailDto.setTel3(request.getParameter("tel3"));
		memberDetailDto.setZipcode(request.getParameter("zipcode"));
		memberDetailDto.setAddress(request.getParameter("address"));
		memberDetailDto.setAddressDetail(request.getParameter("address_detail"));

		int cnt = MemberServiceImpl.getMemberService().registerMember(memberDetailDto);
//		System.out.println("cnt=====" + cnt);
		if(cnt != 0) {
			request.setAttribute("userInfo", memberDetailDto); //리퀘스트에 담아라
			path = "/user/member/registerok.jsp"; //담은걸가지고 저기로 가라
		}else {
			path = "/user/member/registerfail.jsp"; //갈거다라고 지정
		}
		
		return path;
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		String path = "/index.jsp";
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDto memberDto = MemberServiceImpl.getMemberService().loginMember(id, pass);
		if(memberDto != null) {

			////////////////////////////cookie///////////////////////////////////////////////
			//쿠키는 내 컴퓨터에 저장이 된다 내 컴퓨터를 벗어났는데도 보이면 db를 이용한것이다
			String idsv = request.getParameter("idsave");
			if("idsave".equals(idsv) ) {
				Cookie cookie = new Cookie("kid_inf", id);
				cookie.setDomain("localhost");
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60*60*24*365*50);//50년
				response.addCookie(cookie);
			}else { //이 쿠키의 체크를 풀었다면
				Cookie cookie[] = request.getCookies();
				if(cookie != null){
					for(Cookie c : cookie ){
						if("kid_inf".equals(c.getName())){
							c.setDomain("localhost");
							c.setPath(request.getContextPath());
							c.setMaxAge(0);//0년
							response.addCookie(c);
							break;
						}
					}	
				}

				
				
			}
			/////////////////////////////////////////////////////////////////////////////////
			
			
			
			////////////////////////////session///////////////////////////////////////////////
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", memberDto); //바구니 //request.setAttribute("userInfo", memberDto); //리퀘스트에 담아라가 아니라 세션에 담아야함
			//////////////////////////////////////////////////////////////////////////////////
			
			
			path = "/user/login/loginok.jsp"; //담은걸가지고 저기로 가라
		}else {
			path = "/user/login/loginfail.jsp"; //담은걸가지고 저기로 가라
		}
		return path;
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		보통 2번,3번 방법을 많이 쓴다
//		session.setAttribute("userInfo", null); //1.가능은 하지만 꼼수방법
//		session.removeAttribute("userInfo"); //2번방법
		session.invalidate(); //3번방법 모든걸 일괄 지워라??
		return "/user/login/login.jsp";
	}
}












