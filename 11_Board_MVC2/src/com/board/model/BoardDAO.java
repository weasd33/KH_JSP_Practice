package com.board.model;

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

	// 글 작성
	public int writeBoard(BoardDTO dto) {
		int result = 0, count = 0;
		
		try {			
			openConn();
			
			sql = "SELECT MAX(BOARD_NO) FROM BOARD";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO BOARD VALUES (?, ?, ?, ?, ?, DEFAULT, SYSDATE, '')";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getPwd());
			result = pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // writeBoard - End

	// 글 상세 정보
	public BoardDTO contentBoard(int no) {
		BoardDTO dto = new BoardDTO();
		
		try {
			openConn();
			
			sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("BOARD_NO"));
				dto.setWriter(rs.getString("BOARD_WRITER"));
				dto.setTitle(rs.getString("BOARD_TITLE"));
				dto.setContent(rs.getString("BOARD_CONTENT"));
				dto.setPwd(rs.getString("BOARD_PWD"));
				dto.setHit(rs.getInt("BOARD_HIT"));
				dto.setDate(rs.getString("BOARD_DATE"));
				dto.setUpdate(rs.getString("BOARD_UPDATE"));
			}
			
			sql = "UPDATE BOARD SET BOARD_HIT = BOARD_HIT + 1 WHERE BOARD_NO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return dto;
	} // contentBoard() - End

	// 글 수정
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "SELECT BOARD_PWD FROM BOARD WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPwd().equals(rs.getString(1))) {
					sql = "UPDATE BOARD SET"
							+ " BOARD_TITLE = ?,"
							+ " BOARD_CONTENT = ?"
							+ " WHERE BOARD_NO = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getTitle());
					pstmt.setString(2, dto.getContent());
					pstmt.setInt(3, dto.getNo());
					
					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // updateBoard() - End

	// 글 삭제
	public int deleteBoard(int no, String pwd) {
		int result = 0;
				
		try {
			openConn();
			
			sql = "SELECT BOARD_PWD FROM BOARD WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pwd.equals(rs.getString(1))) {
					sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					
					result = pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // deleteBoard() - End
}














