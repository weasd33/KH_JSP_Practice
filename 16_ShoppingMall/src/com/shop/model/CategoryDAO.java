package com.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CategoryDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;

	private static CategoryDAO instance;

	private CategoryDAO() {}

	public static CategoryDAO getInstance() {

		if (instance == null) {
			instance = new CategoryDAO();
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

	// 카테고리 등록
	public int insertCategory(String cart_code, String cart_name) {
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "SELECT MAX(CATEGORY_NUM) FROM SHOP_CATEGORY";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 시퀀스 역할
				count = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO SHOP_CATEGORY VALUES(?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, cart_code);
			pstmt.setString(3, cart_name);
			
			result = pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // insertCategory() - End

	// 카테고리 전체 목록 조회
	public List<CategoryDTO> getCategoryList() {
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		
		try {
			openConn();
			
			sql = "SELECT * FROM SHOP_CATEGORY ORDER BY CATEGORY_NUM DESC";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				
				dto.setCategory_num(rs.getInt("CATEGORY_NUM"));
				dto.setCategory_code(rs.getString("CATEGORY_CODE"));
				dto.setCategory_name(rs.getString("CATEGORY_NAME"));
				
				list.add(dto);
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	} // getCategoryList() - End

	// 카테고리 삭제
	public int deleteCategory(int cart_num) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "DELETE FROM SHOP_CATEGORY WHERE CATEGORY_NUM = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			
			result = pstmt.executeUpdate();
			
			// 시퀀스
			sql = "UPDATE SHOP_CATEGORY SET CATEGORY_NUM = CATEGORY_NUM - 1 WHERE CATEGORY_NUM > ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // deleteCategory() - End
}
