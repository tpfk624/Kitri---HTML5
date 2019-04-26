package com.kitri.jdbctest;

import java.sql.*;

//tpfk624인 사람 탈퇴
public class DeleteTest {
	
	public DeleteTest() {
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
		System.out.println("DB connect Success!!!!");
		return conn;
	}
	
	public static void main(String[] args) {
		DeleteTest dt = new DeleteTest();
		
		String id = "으아아얏츄";
		
		Connection conn = null;
		Statement stmt = null;
		int cnt = 0;
		
		try {
			conn = dt.makeConnection();
			
			String sql = "";
			sql += "delete from jdbctest \n";
			sql += "where id = '"+ id + "'";
			stmt = conn.createStatement();
			
			cnt = stmt.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null)
					conn.close();
				if(stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(cnt + "개 data delete success!!!!!");
	}
}
