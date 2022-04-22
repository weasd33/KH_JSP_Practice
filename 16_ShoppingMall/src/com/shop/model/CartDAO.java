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

public class CartDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;

	private static CartDAO instance;

	private CartDAO() {}

	public static CartDAO getInstance() {

		if (instance == null) {
			instance = new CartDAO();
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

	// 장바구니 담기 - SHOP_CART 테이블에 상품 정보 저장
	public int insertCart(CartDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "SELECT MAX(CART_NUM) FROM SHOP_CART";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO SHOP_CART VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setInt(2, dto.getCart_pnum());
			pstmt.setString(3, dto.getCart_userId());
			pstmt.setString(4, dto.getCart_pname());
			pstmt.setInt(5, dto.getCart_pqty());
			pstmt.setInt(6, dto.getCart_price());
			pstmt.setString(7, dto.getCart_pspec());
			pstmt.setString(8, dto.getCart_pimage());
			
			result = pstmt.executeUpdate();
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // insertCart() - End

	// 회원 아이디에 해당하는 장바구니 목록
	public List<CartDTO> getCartList(String userId) {
		List<CartDTO> list = new ArrayList<CartDTO>();
		
		try {
			openConn();
			
			sql = "SELECT * FROM SHOP_CART WHERE CART_USERID = ? ORDER BY CART_NUM DESC";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartDTO dto = new CartDTO();
				
				dto.setCart_num(rs.getInt("CART_NUM"));
				dto.setCart_pnum(rs.getInt("CART_PNUM"));
				dto.setCart_userId(rs.getString("CART_USERID"));
				dto.setCart_pname(rs.getString("CART_PNAME"));
				dto.setCart_pqty(rs.getInt("CART_PQTY"));
				dto.setCart_price(rs.getInt("CART_PRICE"));
				dto.setCart_pspec(rs.getString("CART_PSPEC"));
				dto.setCart_pimage(rs.getString("CART_PIMAGE"));
				
				list.add(dto);
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	} // getCartList() - End
}





















