package com.kitri.jdbctest;

import java.sql.*;

public class ConnectionTest {
	
	public ConnectionTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//클래스를 읽어들여서 메모리에 올린다 
			System.out.println("Driver Loading Success!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//드라이브를 읽어들어야 실행가능함 
	private void dbConnect() {
		Connection conn = null; //커넥션을 만드는 작업
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.14.52:1521:orcl", "kitri", "kitri");
			//프로토콜,어떤 종류 db, drive 종류,ip주소, 포트번호, sid, 아이디, 비번
			System.out.println("DB Connection Success!!!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		ConnectionTest ct = new ConnectionTest();
		ct.dbConnect();
	}

}	
