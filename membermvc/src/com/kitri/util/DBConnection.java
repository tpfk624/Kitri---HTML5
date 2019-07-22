package com.kitri.util;

import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class DBConnection {
	
//	static {//생성자가 아니라 static블록 초기화를 쓰는 이유//첨에 한번만 읽어들이면 되니까
//		try {
//			Class.forName(SiteConstance.DB_DRIVER);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static Connection makeConnection() throws SQLException{
//		return DriverManager.getConnection(SiteConstance.DB_URL, SiteConstance.DB_USERNAME, SiteConstance.DB_PASSWORD);
//	}
	
	public static Connection makeConnection() throws SQLException{
		try {
			Context ictx = new InitialContext();
			Context ctx = (Context) ictx.lookup("java:comp/env");
			DataSource dataSource = (DataSource) ctx.lookup("jdbc/kitri");
			return dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
}


//A a1 = new A();
//A a2 = new A();
//A a3 = new A();
//
//생성자는 new A() 하는 시점 객체 생성 시 따로따로 호출??
//스태이틱변수나 클래스는 클래스가 로드되는 시점에서 메모리를 올림 ( A )
//			이라는 변수가 읽혀지는 시점

//생성자가 아니라 static블록 초기화를 쓰는 이유
//첨에 한번만 읽어들이면 되니까