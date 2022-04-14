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

	// 글 등록
	public int insertBoard(BoardDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "SELECT MAX(BOARD_NO) FROM BOARD";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO BOARD VALUES(?, ?, ?, ?, ?, DEFAULT, SYSDATE, '')";
			
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
	} // insertBoard() - End

	// 조회수 증가
	public void boardHit(int no) {
		
		try {
			openConn();
			
			sql = "UPDATE BOARD SET BOARD_HIT = BOARD_HIT + 1 WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
	} // boardHit() - End

	// 글 상세정보
	public BoardDTO BoardContent(int no) {
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
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return dto;
	} // BoardContent() - End

	// 글 수정
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "UPDATE BOARD SET BOARD_TITLE = ?,"
					+ " BOARD_CONTENT = ?,"
					+ " BOARD_UPDATE = SYSDATE"
					+ " WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNo());
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();			
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
					
					sql = "UPDATE BOARD SET BOARD_NO = BOARD_NO - 1 WHERE BOARD_NO > ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					
					pstmt.executeUpdate();
				} else {
					result = -1;
				}
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // deleteBoard() - End

	// 검색어에 해당하는 게시물의 수
	public int searchListCount(String field, String keyword) {
		int count = 0;
		
		try {
			openConn();
			
			if(field.equals("title")) { // 제목으로 검색
				sql = "SELECT COUNT(*) FROM BOARD WHERE BOARD_TITLE LIKE ?";
			} else if(field.equals("content")) { // 내용으로 검색
				sql = "SELECT COUNT(*) FROM BOARD WHERE BOARD_CONTENT LIKE ?";
			} else if(field.equals("title_content")) { // 제목 + 내용으로 검색
				sql = "SELECT COUNT(*) FROM BOARD WHERE BOARD_TITLE || BOARD_CONTENT LIKE ?";
			} else { // 작성자로 검색
				sql = "SELECT COUNT(*) FROM BOARD WHERE BOARD_WRITER LIKE ?";
			}
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return count;
	} // searchListCount() - End

	// 검색된 내용을 가지고 페이징 처리
	public List<BoardDTO> searchBoardList(String field, String keyword, int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지에서 끝 번호 
		int endNo = (page * rowsize);
		
		try {
			openConn();
			
			if(field.equals("title")) { // 제목으로 검색
				sql = "SELECT * FROM"
						+ " (SELECT ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) RNUM, B.* FROM BOARD B"
						+ " WHERE BOARD_TITLE LIKE ?)"
						+ " WHERE RNUM >= ? AND RNUM <= ?";
			} else if(field.equals("content")) { // 내용으로 검색
				sql = "SELECT * FROM"
						+ " (SELECT ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) RNUM, B.* FROM BOARD B"
						+ " WHERE BOARD_CONTENT LIKE ?)"
						+ " WHERE RNUM >= ? AND RNUM <= ?";
			} else if(field.equals("title_content")) { // 제목 + 내용으로 검색
				sql = "SELECT * FROM"
						+ " (SELECT ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) RNUM, B.* FROM BOARD B"
						+ " WHERE BOARD_TITLE || BOARD_CONTENT LIKE ?)"
						+ " WHERE RNUM >= ? AND RNUM <= ?";
			} else { // 작성자로 검색
				sql = "SELECT * FROM"
						+ " (SELECT ROW_NUMBER() OVER(ORDER BY BOARD_NO DESC) RNUM, B.* FROM BOARD B"
						+ " WHERE BOARD_WRITER LIKE ?)"
						+ " WHERE RNUM >= ? AND RNUM <= ?";
			}
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startNo);
			pstmt.setInt(3, endNo);
			
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
	}

}






