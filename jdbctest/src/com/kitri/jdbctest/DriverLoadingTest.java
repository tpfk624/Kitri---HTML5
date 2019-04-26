package com.kitri.jdbctest;

public class DriverLoadingTest {
	
	public DriverLoadingTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//클래스를 읽어들여서 메모리에 올린다 
			System.out.println("Driver Loading Success!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new DriverLoadingTest();
	}
}
