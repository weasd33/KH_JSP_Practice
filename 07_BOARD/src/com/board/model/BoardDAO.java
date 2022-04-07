package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	// 1. 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//	기본 생성자의 접근 제어자를 private으로 바꿔 줘야 한다.
	
	// 2. ProductDAO 객체를 정적 멤버로 선언
	private static BoardDAO instance;
	
	private BoardDAO() {}
	
	// 3. 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 한다.
	public static BoardDAO getInstance() {
		
		if(instance == null) {
			instance = new BoardDAO();
		}
		
		return instance;
	}
	
	// DB 연동
	public void openConn() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String user = "SHIM";
		String password = "1234";
		
		try {
			// 1단계 : 오라클 드라이버 로딩
			Class.forName(driver);	
			
			// 2단계 : 오라클 DB와 연결
			con = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) { e.printStackTrace(); }
	} // openConn() - End

	// 게시판 목록 전체 조회
	public List<BoardDTO> selectBoard() {
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
	} // selectBoard() - End

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
			
			sql = "INSERT INTO BOARD VALUES(?, ?, ?, ?, ?, default, SYSDATE, '')";
			
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
	public void hitBoard(int num) {
		
		try {
			openConn();
			
			sql = "UPDATE BOARD SET BOARD_HIT = BOARD_HIT + 1 WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();			
		} catch (SQLException e) { e.printStackTrace(); }
	} // hitBoard() - End
		
	// 해당 게시글 정보
	public BoardDTO contentBoard(int num) {
		BoardDTO dto = new BoardDTO();
		
		try {
			openConn();
			
			sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
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
	} // contentBoard() - End

	// 게시글 수정
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 수정 폼에서 입력한 비밀번호가 DB의 비밀번호와 같다면
				if(dto.getPwd().equals(rs.getString("BOARD_PWD"))) {
					sql = "UPDATE BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ?, BOARD_UPDATE = SYSDATE WHERE BOARD_NO = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getTitle());
					pstmt.setString(2, dto.getContent());
					pstmt.setInt(3, dto.getNo());
					
					result = pstmt.executeUpdate();
				} else { // 비밀번호가 일치하지 않는 경우
					result = -1;
				}
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // updateBoard() - End

	// 게시글 삭제
	public int deleteBoard(int no, String pwd) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "SELECT BOARD_PWD FROM BOARD WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pwd.equals(rs.getString("BOARD_PWD"))) { // 비밀번호가 일치하는 경우 삭제
					sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					
					result = pstmt.executeUpdate();
				} else { // 비밀번호가 틀린 경우
					result = -1;
				}
			}
			
			if(result == 1) { // 삭제 성공 시 글 번호 재정렬
				sql = "UPDATE BOARD SET BOARD_NO = BOARD_NO - 1 WHERE BOARD_NO > ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // deleteBoard() - End
}















