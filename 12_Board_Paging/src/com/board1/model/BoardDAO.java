package com.board1.model;

import java.sql.Connection;
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

	// 게시물 전체 갯수
	public int getBoardCount() {
		int count = 0;
		
		try {
			openConn();
			
			sql = "SELECT COUNT(*) FROM BOARD";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return count;
	} // getBoardCount() - End

	// 현재 페이지에 해당하는 게시물 조회
	public List<BoardDTO> getBoardList(int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		// 해당 페이지 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지 끝 번호
		int endNo = (page * rowsize);
		
		try {
			openConn();
			
			sql = "SELECT * FROM"
					+ " (SELECT ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) RNUM, B.* FROM BOARD B)"
					+ " WHERE RNUM >= ? AND RNUM <= ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
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
	} // getBoardList() - End

}






