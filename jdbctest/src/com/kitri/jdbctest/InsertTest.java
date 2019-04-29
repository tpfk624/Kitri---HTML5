package com.kitri.jdbctest;

import java.sql.*;

public class InsertTest {
	
	public InsertTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection makeConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri","kitri");
		System.out.println("DB Connection Success!!!!!");
		return conn;
	}
	
	public static void main(String[] args) {
		InsertTest it = new InsertTest();
		String name = "고세라";
		String id = "tpfk624";
		
		Connection conn = null;
		Statement stmt = null;
		int cnt = 0;
		try {
			
//			2.db연결
			conn = it.makeConnection();//2.데이터베이스 연결?
//			insert into jdbctest (no, name, id, joindate)
//			values(jdbctest_no_seq.nextval, '고세라', 'tpfk624', sysdate);
			
//			3. sql실행준비 (나중에는 길어지니 버퍼사용하기)
			String sql = "";		
			sql += "insert into jdbctest (no, name, id, joindate) \n";
			sql += "values (jdbctest_no_seq.nextval, '" + name +"' , '"+ id + "', sysdate)"; //자바에서는 ;찍으면 안됨
//			System.out.println(sql);
			stmt = conn.createStatement();
			
//			4. sql문 실행
			cnt = stmt.executeUpdate(sql);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			5.역순으로 닫기
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(cnt != 0 )//0이 아닌건 들어갔는다는 소리
			System.out.println("date insert success!!!!!");
		else
			System.out.println("data insert fail!!!!!!!!!!");
		
		
	}
}
