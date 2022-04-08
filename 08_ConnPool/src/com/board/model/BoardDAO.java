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
import javax.naming.NamingException;
import javax.sql.DataSource;

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
	
	// DB 연동 - 커넥션풀 방식 사용
	public void openConn() {
		
		try {
			// 1. : JNDI 서버 객체 생성
			Context ctx = new InitialContext();
			
			// 2. : lookup() 메서드를 이용하여 매칭되는 커넥션 찾기
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
			
			// 3. DataSource 객체를 이용하여 커넥션 객체 하나 가져오기
			con = ds.getConnection();
			
		} catch (Exception e) { e.printStackTrace(); }
		
	} // openConn() - End

	// 테이블 전체 목록 조회
	public List<BoardDTO> selectAllBoard() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
				
		try {
			openConn();
			
			sql = "SELECT * FROM BOARD ORDER BY BOARD_NO DESC";
			
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















