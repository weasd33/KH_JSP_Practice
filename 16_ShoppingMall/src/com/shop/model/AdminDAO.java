package com.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;

	private static AdminDAO instance;

	private AdminDAO() {}

	public static AdminDAO getInstance() {

		if (instance == null) {
			instance = new AdminDAO();
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

	// 관리자 로그인
	public int adminCheck(String admin_id, String admin_pwd) {
		int result = 0; // 관리자 아이디가 존재하지 않는 경우
		
		try {
			openConn();
			
			sql = "SELECT ADMIN_ID, ADMIN_PWD FROM ADMIN_SHOP WHERE ADMIN_ID = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 비밀번호 확인 전 아이디가 존재하는지 확인
				if(admin_pwd.equals(rs.getString("ADMIN_PWD"))) {
					// 아이디가 존재하고 비밀번호가 일치 할 경우
					result = 1;
				} else {
					// 아이디는 존재하지만 비밀번호가 틀릴 경우
					result = -1;
				} 
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // adminCheck() - End

	// 관리자 정보 조회
	public AdminDTO getAdmin(String admin_id) {
		AdminDTO dto = new AdminDTO();
		
		try {
			openConn();
			
			sql = "SELECT * FROM ADMIN_SHOP WHERE ADMIN_ID = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setAdmin_id(rs.getString("ADMIN_ID"));
				dto.setAdmin_pwd(rs.getString("ADMIN_PWD"));
				dto.setAdmin_name(rs.getString("ADMIN_NAME"));
				dto.setAdmin_email(rs.getString("ADMIN_EMAIL"));
				dto.setAdmin_date(rs.getString("ADMIN_DATE"));
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return dto;
	} // getAdmin() - End
}






