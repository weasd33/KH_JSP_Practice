package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	private static BoardDAO instance;
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		
		if(instance == null) {
			instance = new BoardDAO();
		}
		
		return instance;
	}
	
	// DB 연동
	public void openConn() {

		try {
			// 1. : JNDI 서버 객체 생성
			Context ctx = new InitialContext();

			// 2. : lookup() 메서드를 이용하여 매칭되는 커넥션 찾기
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");

			// 3. DataSource 객체를 이용하여 커넥션 객체 하나 가져오기
			con = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // openConn() - End

	// 글 전체 목록
	public List<BoardDTO> selectAllBoard() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			openConn();
			
			sql = "SELECT * FROM BOARD ORDER BY 1 DESC";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();

				dto.setNo(rs.getInt("BOARD_NO"));
				dto.setWriter(rs.getString("BOARD_WRITER"));
				dto.setTitle(rs.getString("BOARD_TITLE"));
				dto.setContent(rs.getString("BOARD_CONTENT"));
				dto.setPwd(rs.getString("BOARD_PWD"));
				dto.setHit(rs.getInt("BOARD_HIT"));
				dto.setDate(rs.getString("BOARD_DATE"));
				dto.setUpdate(rs.getString("BOARD_UPDATE"));
				
				list.add(dto);				
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	} // selectAllBoard() - End
}














