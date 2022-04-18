package com.reply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BbsDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	private static BbsDAO instance;
	
	private BbsDAO() {}
	
	public static BbsDAO getInstance() {
		
		if(instance == null) {
			instance = new BbsDAO();
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
	
	// 전체 레코드 조회
	public List<BbsDTO> getBbsList() {
		List<BbsDTO> list = new ArrayList<BbsDTO>();
		
		try {
			openConn();
			
			sql = "SELECT * FROM JSP_BBS ORDER BY BOARD_GROUP DESC, BOARD_STEP ASC";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BbsDTO dto = new BbsDTO();
				
				dto.setNo(rs.getInt("BOARD_NO"));
				dto.setWriter(rs.getString("BOARD_WRITER"));
				dto.setTitle(rs.getString("BOARD_TITLE"));
				dto.setContent(rs.getString("BOARD_CONTENT"));
				dto.setPwd(rs.getString("BOARD_PWD"));
				dto.setHit(rs.getInt("BOARD_HIT"));
				dto.setDate(rs.getString("BOARD_DATE"));
				dto.setUpdate(rs.getString("BOARD_UPDATE"));
				dto.setGroup(rs.getInt("BOARD_GROUP"));
				dto.setStep(rs.getInt("BOARD_STEP"));
				dto.setIndent(rs.getInt("BOARD_INDENT"));
				
				list.add(dto);
			}
			
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	} // getBbsList() - End

	// 글 등록
	public int insertBoard(BbsDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "SELECT MAX(BOARD_NO) FROM JSP_BBS";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO JSP_BBS VALUES(?, ?, ?, ?, ?, DEFAULT, SYSDATE, '', ?, 0, 0)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getPwd());
			pstmt.setInt(6, count);
			
			result = pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // insertBoard() - End
	
	// 글 조회수 증가
	public void bbsHit(int no) {
		
		try {
			openConn();
			
			sql = "UPDATE JSP_BBS SET BOARD_HIT = BOARD_HIT + 1 WHERE BOARD_NO";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
	} // bbsHit() - End
	
	// 글 상세 정보
	public BbsDTO getBbsContent(int no) {
		BbsDTO dto = new BbsDTO();
		
		try {
			openConn();
			
			sql = "SELECT * FROM JSP_BBS WHERE BOARD_NO = ?";
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
				dto.setGroup(rs.getInt("BOARD_GROUP"));
				dto.setStep(rs.getInt("BOARD_STEP"));
				dto.setIndent(rs.getInt("BOARD_INDENT"));
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return dto;
	} // getBbsContent() - End

	// 글 수정
	public int updateBbs(BbsDTO dto) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "SELECT BOARD_PWD FROM JSP_BBS WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPwd().equals(rs.getString(1))) {
					sql = "UPDATE JSP_BBS SET"
							+ " BOARD_TITLE = ?, BOARD_CONTENT = ?,"
							+ " BOARD_UPDATE = SYSDATE WHERE BOARD_NO = ?";
					
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
	} // updateBbs() - End

	public int deleteBbs(int no, String pwd) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "SELECT BOARD_PWD FROM JSP_BBS WHERE BOARD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pwd.equals(rs.getString(1))) {
					sql = "DELETE FROM JSP_BBS WHERE BOARD_NO = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, no);
					
					result = pstmt.executeUpdate();
					
					sql = "UPDATE JSP_BBS SET BOARD_NO = BOARD_NO - 1"
							+ " WHERE BOARD_NO > ?";
					
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
	}
}











