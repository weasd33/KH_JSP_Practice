package com.upload.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UploadDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;

	private static UploadDAO instance;

	private UploadDAO() {}

	public static UploadDAO getInstance() {

		if (instance == null) {
			instance = new UploadDAO();
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

	// 전체 리스트 조회
	public List<UploadDTO> getUploadList() {
		List<UploadDTO> list = new ArrayList<UploadDTO>();
		
		try {
			openConn();
			
			sql = "SELECT * FROM UPLOAD ORDER BY UPLOAD_NO DESC";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UploadDTO dto = new UploadDTO();
				
				dto.setNo(rs.getInt("UPLOAD_NO"));
				dto.setWriter(rs.getString("UPLOAD_WRITER"));
				dto.setTitle(rs.getString("UPLOAD_TITLE"));
				dto.setContent(rs.getString("UPLOAD_CONTENT"));
				dto.setPwd(rs.getString("UPLOAD_PWD"));
				dto.setFile(rs.getString("UPLOAD_FILE"));
				dto.setHit(rs.getInt("UPLOAD_HIT"));
				dto.setDate(rs.getString("UPLOAD_DATE"));
				dto.setUpdate(rs.getString("UPLOAD_UPDATE"));
				
				list.add(dto);
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	} // getUploadList() - End

	// 글 작성
	public int insertUpload(UploadDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "SELECT MAX(UPLOAD_NO) FROM UPLOAD";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO UPLOAD VALUES(?, ?, ?, ?, ?, ?, DEFAULT, SYSDATE, '')";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getPwd());
			pstmt.setString(6, dto.getFile());
			
			result = pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // insertUpload() - End

	// 조회수 증가
	public void uploadHit(int no) {
		
		try {
			openConn();
			
			sql = "UPDATE UPLOAD SET"
					+ " UPLOAD_HIT = UPLOAD_HIT + 1"
					+ " WHERE UPLOAD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
	} // uploadHit() - End

	// 상세내역
	public UploadDTO uploadContent(int no) {
		UploadDTO dto = new UploadDTO();
		
		try {
			openConn();
			
			sql = "SELECT * FROM UPLOAD WHERE UPLOAD_NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNo(rs.getInt("UPLOAD_NO"));
				dto.setWriter(rs.getString("UPLOAD_WRITER"));
				dto.setTitle(rs.getString("UPLOAD_TITLE"));
				dto.setContent(rs.getString("UPLOAD_CONTENT"));
				dto.setPwd(rs.getString("UPLOAD_PWD"));
				dto.setFile(rs.getString("UPLOAD_FILE"));
				dto.setHit(rs.getInt("UPLOAD_HIT"));
				dto.setDate(rs.getString("UPLOAD_DATE"));
				dto.setUpdate(rs.getString("UPLOAD_UPDATE"));
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return dto;
	} // uploadContent() - End
}








