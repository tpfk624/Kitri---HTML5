package com.kitri.dao;

import java.sql.*;

import com.kitri.dto.RepBoard;
import com.kitri.exception.AddException;

public class RepBoardDAO {
	public void insert(RepBoard repBoard) throws AddException {

		Connection con = null;
		PreparedStatement pstmt = null;
		
//		1.jdbc드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//		2.db연결
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "kitri";
			String password = "kitri";
			con = DriverManager.getConnection(url, user, password);

//		3.sql구문 db서버로 송신: excutequery()
//		4.결과수신: rs
			String insertSQL = "insert into repboard("
					+ "BOARD_SEQ, PARENT_SEQ, BOARD_SUBJECT, BOARD_WRITER, BOARD_CONTENTS, BOARD_DATE, BOARD_PASSWORD, BOARD_VIEWCOUNT)"
					+ " values(BOARD_SEQ.NEXTVAL, ?,       ?, 			?, 				?, systimestamp,  			?, 				0)";
			
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, repBoard.getParent_seq());
			pstmt.setString(2, repBoard.getBoard_subject());
			pstmt.setString(3, repBoard.getBoard_writer());
			pstmt.setString(4, repBoard.getBoard_contents());
			pstmt.setString(5, repBoard.getBoard_password());

			pstmt.executeUpdate();
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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

	}
	
	public static void main(String[] args) {
		RepBoardDAO dao = new RepBoardDAO();
		RepBoard repBoard = new RepBoard();
		repBoard.setBoard_subject("테스트제목");
		repBoard.setBoard_writer("test");	
		repBoard.setBoard_contents("테스트내용");
		repBoard.setBoard_password("testp");
		repBoard.setParent_seq(1);//답글쓰기용 테스트
		try {
			dao.insert(repBoard);//글쓰기용 테스트
		} catch (AddException e) {
			e.printStackTrace();
		} 
	}
	
}