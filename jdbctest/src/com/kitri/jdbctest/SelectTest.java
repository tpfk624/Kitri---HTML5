package com.kitri.jdbctest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectTest {

	public SelectTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection makeConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
		System.out.println("DB Connection Success!!!!!");
		return conn;

	}

	public List<MemberDto> memberList(String searchName) { // list<> : 모든사람의 이름을 얻어와라 () :(이 사람의 이름을 얻어와라)
		// dto는 테이블당 하나
		List<MemberDto> list = new ArrayList<MemberDto>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = makeConnection();
			String sql = "";
			sql += "select no, name, id, joindate \n";// 가능하면 \n으로 구분해주기, 적어도 한칸띄어쓰기 하거나 \n꼭 써주기 (붙어버림)
			sql += "from jdbctest \n";
			if (searchName != null)
				sql += "where name = '" + searchName + "'"; // 검색할 사람의 이름이 있으면 검색을 하고 없으면 이 구문이 실행이 안되니 모든회원이 검색됨
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);
//			MemberDto memberDto = new MemberDto(); //하나만들어서 재사용 //마지막 사람만 출력
			while (rs.next()) {
				MemberDto memberDto = new MemberDto();
//				memberDto.setNo(rs.getInt("1")); //컬럼의 인덱스(db는 1부터 시작)//숫자를 쓰면 *인 경우 테이블구조를 알아야해서 컬럼이름을 쓰는게 편하다
				memberDto.setNo(rs.getInt("no"));
				memberDto.setId(rs.getString("id"));
				memberDto.setName(rs.getString("name"));
				memberDto.setJoindate(rs.getString("joindate"));

				list.add(memberDto); // 리스트에 담아라

			}
			; // while문을 사용(이름은 동명이인이 있어 여러명일수 있으니)

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 실행순서 역순으로 닫기
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return list;

	}

	public MemberDto memberSearch(int no) {

		MemberDto memberDto = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = makeConnection();
			String sql = "";
			sql += "select no, id, name, \n";
			sql += "				decode(to_char(joindate, 'yymmdd'), to_char(sysdate, 'yymmdd'), to_char(joindate, 'hh24:mi:ss'), to_char(joindate, 'yy.mm.dd')) joindate \n";
			sql += "from jdbctest \n";
			sql += "where no =" + no;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				memberDto = new MemberDto();
				memberDto.setNo(rs.getInt("no"));
				memberDto.setId(rs.getString("id"));
				memberDto.setName(rs.getString("name"));
				memberDto.setJoindate(rs.getString("joindate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 실행순서 역순으로 닫기
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return memberDto;

	}

	public static void main(String[] args) {
		SelectTest st = new SelectTest();
//		String searchName = null; //모두 출력
		String searchName = "홍길동"; // 고세라라는 이름을 가진 사람만 출력
		List<MemberDto> list = st.memberList(searchName);
		System.out.println("회원번호\t이름\t아이디\t가입일");
		System.out.println("---------------------------");
		for (MemberDto memberDto : list) {
			System.out.println(memberDto.getNo() + "\t" + memberDto.getName() + "\t" + memberDto.getId() + "\t"
					+ memberDto.getJoindate());
		}

//		int no = 10;
		int no = 201;
//		int no = 122;
		System.out.println("\n회원 번호가 " + no + "인 회원 검색!!!!");
		MemberDto memberDto = st.memberSearch(no);

		if (memberDto != null) {
//			이름 : 홍길동
//			id : hong
//			가입일 : 10:27:24 (오늘)
//			가입일 : 19.04.25 (오늘X)
			System.out.println("이름 : " + memberDto.getName());
			System.out.println("아이디 : " + memberDto.getId());
			System.out.println("가입일 : " + memberDto.getJoindate());
		} else {
//			10번 회원은 존재하지 않습니다.
			System.out.println(no + "번 회원은 존재하지 않습니다.");
		}

//		이름: 홍길동
//		id: hong
//		가입일 : 10:27:24 (오늘   시분초)
//		가입일 : 19.04.25 (오늘x 년월일)
//		
//		10번 회원은 존재하지 않습니다.
	}

}
