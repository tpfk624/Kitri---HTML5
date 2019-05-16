package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.Product;
import com.kitri.dto.ProductCategory;

public class ProductDAO {
	
	//////////////////리스트 뽑아오는 dao/////////////////////////////////////////
	public List<Product> selectAll(){
		List<Product> list = new ArrayList<>();		
		Connection con=null;		
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		
		try {
			//1)JDBC드라이버로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2)DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			//3)SQL송신
			String selectALLSQL = "SELECT cate_no, cate_name," + 
					" prod_no, prod_name, prod_price, prod_detail" + 
					" FROM product p JOIN product_category pc " + 
					" ON  p.prod_cate=pc.cate_no " + 
					" ORDER BY cate_no, prod_name";
			pstmt = con.prepareStatement(selectALLSQL);
			//4)결과수신
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_detail = rs.getString("prod_detail");
				
				String cate_no = rs.getString("cate_no");
				String cate_name = rs.getString("cate_name");
				
				ProductCategory pc = new ProductCategory(cate_no, cate_name);
				Product p = new Product(prod_no, prod_name, prod_price, prod_detail, pc);
				list.add(p);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5)연결닫기
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return list;
	}
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Product selectByNo(String no){
		Connection con=null;		
		PreparedStatement pstmt = null;		
		ResultSet rs = null;
		
		try {
			//1)JDBC드라이버로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2)DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			//3)SQL송신
			String selectByNo = "select prod_name, prod_detail, prod_no, prod_price" + 
					" FROM product p JOIN product_category pc  " + 
					" ON p.prod_cate = pc.cate_no " + 
					" where prod_no = ? ";
			pstmt = con.prepareStatement(selectByNo);
			pstmt.setString(1, no);
			
			//4)결과수신
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_detail = rs.getString("prod_detail");
				
				String cate_no = rs.getString("cate_no");
				String cate_name = rs.getString("cate_name");
				
				
				ProductCategory pc = new ProductCategory(cate_no, cate_name);
				Product p = new Product(prod_no, prod_name, prod_price, prod_detail, pc);
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5)연결닫기
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return Product;
	}

}
