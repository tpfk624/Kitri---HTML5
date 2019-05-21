package com.kitri.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kitri.dto.*;
import com.kitri.exception.AddException;

public class OrderDAO {
	public void insert(OrderInfo info) throws AddException{
		Connection con = null;
		try {
			//1)JDBC드라이버로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2)DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);//자동커밋 해제
			
			insertInfo(con, info);//주문기본추가하기		
			List<OrderLine> lines = info.getLines();
			insertLine(con, lines);
			con.commit();
		}catch(Exception e) {			
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new AddException("주문추가 오류:"+ e.getMessage());
		}finally {
			//DBClose.close(con, null);
			//DB닫기
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public void insertInfo(Connection con, OrderInfo info)
	 throws SQLException{
		PreparedStatement pstmt = null;
		String insertInfoSQL = 
				"INSERT INTO order_info(order_no, order_id) " + 
				" VALUES (order_seq.NEXTVAL, ?)";
		try {
			pstmt = con.prepareStatement(insertInfoSQL);
			pstmt.setString(1, info.getCustomer().getId());
			pstmt.executeUpdate();
			System.out.println("End insertInfo");
		//}catch(SQLException e) {
		//	e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	public void insertLine(Connection con, List<OrderLine> lines) 
	    throws SQLException{ //???
		PreparedStatement pstmt =null;
		String insertLineSQL = 
				"INSERT INTO order_line(order_no, order_prod_no, order_quantity) " + 
				" VALUES (order_seq.CURRVAL, ?, ?)";
		try {
			pstmt = con.prepareStatement(insertLineSQL);
			for(OrderLine line: lines) {
				//String prod_no = line.getProder_prod_no();
				String prod_no = line.getProduct().getProd_no();
				pstmt.setString(1, prod_no);
				
				int quantity = line.getOrder_quantity();
				pstmt.setInt(2, quantity);
				//pstmt.executeUpdate();
				pstmt.addBatch();//일괄처리에 추가
			}
			pstmt.executeBatch();//일괄처리
			System.out.println("End insertLine");
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public List<OrderInfo>selectById(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderInfo> list = new ArrayList<>();
		try {
			//1)JDBC드라이버로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2)DB연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);
			
			//3)SQL송신
			String selectByIdSQL = "SELECT info.order_no, order_dt," + 
					"prod_no, prod_name, prod_price," + 
					" order_quantity" + 
					" FROM order_info info JOIN order_line line ON info.order_no = line.order_no" + 
					" JOIN product p ON p.prod_no = line.ORDER_PROD_NO" + 
					" WHERE order_id = ?" + 
					" ORDER BY order_no DESC, prod_no";			
			pstmt = con.prepareStatement(selectByIdSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			OrderInfo info=null;
			OrderLine line=null;
			List<OrderLine> lines=null;
			int old_order_no = -1;
			while( rs.next() ) {
				int order_no = rs.getInt("order_no");
				if(old_order_no != order_no) { //주문번호가 다를 때 
					info = new OrderInfo();
					list.add(info); 
					info.setOrder_no(order_no);
					info.setOrder_dt(rs.getDate("order_dt"));
					lines = new ArrayList<>();
					info.setLines(lines);
					old_order_no = order_no;
				}
				line = new OrderLine();
				String prod_no = rs.getString("prod_no");//상품번호,명,가격
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				Product p = new Product();
				p.setProd_no(prod_no);
				p.setProd_name(prod_name);
				p.setProd_price(prod_price);
				
				line.setProduct(p);
				line.setOrder_quantity(rs.getInt("order_quantity"));
				
				lines.add(line);//???
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}	  
	public static void main(String[] args) {
		OrderDAO dao = new OrderDAO();
		List<OrderInfo> list = dao.selectById("test");
		System.out.println(list.size());
		for(OrderInfo info:list) {
			System.out.println(info.getOrder_no());
			System.out.println(info.getOrder_dt());
			List<OrderLine>lines = info.getLines();
			for(OrderLine line:lines) {
				System.out.println(line.getProduct());
				System.out.println(line.getOrder_quantity());
			}
			System.out.println("------------");
		}
	}
}
