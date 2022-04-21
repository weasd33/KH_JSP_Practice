package com.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;

	private static UserDAO instance;

	private UserDAO() {}

	public static UserDAO getInstance() {

		if (instance == null) {
			instance = new UserDAO();
		}

		return instance;
	} // getInstance() - End

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

	// 회원 로그인 확인
	public int userCheck(String user_id, String user_pwd) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "SELECT * FROM MEMBER10 WHERE MEMID = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 아이디가 존재하고 비밀번호도 일치할 경우
				if(user_pwd.equals(rs.getString("PWD"))) {
					result = 1;
				} else {
					// 비밀번호가 틀린 경우
					result = -1;
				}
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // userCheck() - End

	// 특정 회원 정보 조회
	public UserDTO getMember(String user_id) {
		UserDTO dto = new UserDTO();
		
		try {
			openConn();
			
			sql = "SELECT * FROM MEMBER10 WHERE MEMID = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNum(rs.getInt("NUM"));
				dto.setMemId(rs.getString("MEMID"));
				dto.setMemName(rs.getString("MEMNAME"));
				dto.setPwd(rs.getString("PWD"));
				dto.setAge(rs.getInt("AGE"));
				dto.setMileage(rs.getInt("MILEAGE"));
				dto.setJob(rs.getString("JOB"));
				dto.setAddr(rs.getString("ADDR"));
				dto.setRegdate(rs.getString("REGDATE"));
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return dto;
	} // getMember() - End
}
