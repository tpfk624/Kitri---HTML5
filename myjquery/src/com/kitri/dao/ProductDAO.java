package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.Product;
import com.kitri.dto.ProductCategory;

public class ProductDAO {

	public Product selectByNo(String id) {
		System.out.println("DAO들어옴");
		Product p = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// jdbc드라이버로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 드라이버 connect

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kitri", "kitri");
			// sql
			String sql = "select cate_no, cate_name, " + 
					"prod_no, prod_name, prod_price, prod_detail\r\n" +
					"from product p JOIN product_category c \r\n" +
					"ON p.prod_cate = c.cate_no \r\n"+
					"where prod_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				String prod_detail = rs.getString("prod_detail");
				String cate_no = rs.getString("cate_no");
				String cate_name = rs.getString("cate_name");

				ProductCategory pc = new ProductCategory(cate_no, cate_name);
				p = new Product(prod_no, prod_name, prod_price, prod_detail, pc);

			}
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 연결닫기
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return p;
		}

	}

	public List<Product> selectAll() {
		List<Product> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// jdbc드라이버로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 드라이버 connect

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "kitri", "kitri");
			// sql
			String sql = "select cate_no, cate_name," + "prod_no, prod_name, prod_price, prod_detail\r\n"
					+ "from product p JOIN product_category c \r\n" + "ON p.prod_cate = c.cate_no \r\n"
					+ "Order by cate_no, prod_name ";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 연결닫기
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}
}
