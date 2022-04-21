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

public class ProductDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;

	private static ProductDAO instance;

	private ProductDAO() {}

	public static ProductDAO getInstance() {

		if (instance == null) {
			instance = new ProductDAO();
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

	// 상품 등록
	public int insertProduct(ProductDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "SELECT MAX(PNUM) FROM SHOP_PRODUCTS";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 시퀀스 역할
				count = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO SHOP_PRODUCTS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getPname());
			pstmt.setString(3, dto.getPcategory_fk());
			pstmt.setString(4, dto.getPcompany());
			pstmt.setString(5, dto.getPimage());
			pstmt.setInt(6, dto.getPqty());
			pstmt.setInt(7, dto.getPrice());
			pstmt.setString(8, dto.getPspec());
			pstmt.setString(9, dto.getPcontents());
			pstmt.setInt(10, dto.getPoint());
			
			result = pstmt.executeUpdate();
					
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // insertProduct() - End

	// 상품 전체 목록 조회
	public List<ProductDTO> getProductList() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		try {
			openConn();
			
			sql = "SELECT * FROM SHOP_PRODUCTS ORDER BY PNUM DESC";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				
				dto.setPnum(rs.getInt("PNUM"));
				dto.setPname(rs.getString("PNAME"));
				dto.setPcategory_fk(rs.getString("PCATEGORY_FK"));
				dto.setPcompany(rs.getString("PCOMPANY"));
				dto.setPimage(rs.getString("PIMAGE"));
				dto.setPqty(rs.getInt("PQTY"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setPspec(rs.getString("PSPEC"));
				dto.setPcontents(rs.getString("PCONTENTS"));
				dto.setPoint(rs.getInt("POINT"));
				dto.setPinputdate(rs.getString("PINPUTDATE"));
				
				list.add(dto);
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	} // getProductList() - End
}
