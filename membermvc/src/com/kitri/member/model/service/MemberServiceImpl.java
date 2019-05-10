package com.kitri.member.model.service;

import java.util.HashMap;
import java.util.List;

import com.kitri.member.model.*;
import com.kitri.member.model.dao.MemberDaoImpl;
import com.sun.javafx.collections.MappingChange.Map;

public class MemberServiceImpl implements MemberService{

//	서비스는 하나만 만든다(싱글 톤 패턴 -  객체를 하나만 만든다)
//	1.private 생성자만들기
//	2.static 전역변수만들기?
	
	private static MemberService memberService;
	
	static {
		memberService = new MemberServiceImpl();
	}
	
	private MemberServiceImpl() {}
	public static MemberService getMemberService() {
		return memberService;
	}

	
	@Override
	public String idCheck(String id) {
		int cnt = MemberDaoImpl.getMemberDao().idCheck(id);
//		System.out.println("cnt : " + cnt);
		String result = "";
		result += "<idcount>\n";
		result += "	<cnt>" + cnt + "</cnt>\n";
		result += "</idcount>";
		return result;
	}

	@Override
	public String zipSearch(String doro) {
		String result = "";
		List<ZipcodeDto> list = MemberDaoImpl.getMemberDao().zipSearch(doro);
		
		result += "<ziplist>\n";
		for(ZipcodeDto zipDto : list) {
			result += "	<zip>\n";
			result += "		<zipcode>" + zipDto.getZipcode() + "</zipcode>\n";
			result += "		<address><![CDATA[" + zipDto.getSido() + " " + zipDto.getGugun() + " " + zipDto.getUpmyon() + " " + zipDto.getDoro()  + " " + zipDto.getBuildingNumber() + " " + zipDto.getSigugunBuildingName() + "]]></address>\n";
			result += "	</zip>\n";
		}
		result += "</ziplist>";
		return result;
	}

	@Override
	public int registerMember(MemberDetailDto memberDetailDto) {
		return MemberDaoImpl.getMemberDao().registerMember(memberDetailDto);
	}

	@Override
	public MemberDto loginMember(String id, String pass) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", id);
		map.put("userpwd", pass);		
		return  MemberDaoImpl.getMemberDao().loginMember(map);
	}

	@Override
	public MemberDetailDto getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyMember(MemberDetailDto MemberDetailDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
