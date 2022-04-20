package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CustomerDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;

	private static CustomerDAO instance;

	private CustomerDAO() {}

	public static CustomerDAO getInstance() {

		if (instance == null) {
			instance = new CustomerDAO();
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

	// customer 테이블 전체 조회
	public String getCustomerList() {
		String result = "";
		
		try {
			openConn();
			
			sql = "SELECT * FROM CUSTOMER ORDER BY NO DESC";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			result += "<customers>";
			
			while(rs.next()) {
				result += "<customer>";
				result += "<no>" + rs.getInt("no") + "</no>";
				result += "<id>" + rs.getString("id") + "</id>";
				result += "<name>" + rs.getString("name") + "</name>";
				result += "<age>" + rs.getInt("age") + "</age>";
				result += "<phone>" + rs.getString("phone") + "</phone>";
				result += "<addr>" + rs.getString("addr") + "</addr>";
				result += "</customer>";
			}
			
			result += "</customers>";
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // getCustomerList() - End

	// 아이디 중복확인
	public String idCheck(String user_id) {
		String result = "사용 가능한 아이디입니다.";
		
		try {
			openConn();
			
			sql = "SELECT ID FROM CUSTOMER WHERE ID = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // true : 중복인 경우
				result = "중복된 아이디입니다.";
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // idCheck() - End

	// Customer 회원 등록
	public int insertCustomer(CustomerDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "SELECT MAX(NO) FROM CUSTOMER";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO CUSTOMER VALUES(?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAge());
			pstmt.setString(5, dto.getPhone());
			pstmt.setString(6, dto.getAddr());
			
			result = pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // insertCustomer() - End

	// Customer 회원 삭제
	public int deleteCustomer(int no) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "DELETE FROM CUSTOMER WHERE NO = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
			sql = "UPDATE CUSTOMER SET NO = NO - 1 WHERE NO > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

			pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	}
}











