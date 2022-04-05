package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	// 1. 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//	기본 생성자의 접근 제어자를 private으로 바꿔 줘야 한다.
	
	// 2. ProductDAO 객체를 정적 멤버로 선언
	private static ProductDAO instance;
	
	private ProductDAO() {}
	
	// 3. 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 한다.
	public static ProductDAO getInstance() {
		
		if(instance == null) {
			instance = new ProductDAO();
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
	}

	// product 테이블 전체 품목 리스트
	public List<ProductDTO> getProductList() {
		List<ProductDTO> list = new ArrayList<>();
		
		try {
			openConn();
			
			sql = "SELECT * FROM PRODUCT ORDER BY PNO";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				
				dto.setPno(rs.getString("PNO"));
				dto.setPname(rs.getString("PNAME"));
				dto.setStock(rs.getInt("STOCK"));
				dto.setPrice(rs.getInt("PRICE"));
				dto.setCompany(rs.getString("COMPANY"));
				dto.setCno(rs.getString("CNO"));
				dto.setCname(rs.getString("CNAME"));
				
				list.add(dto);
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	}
}
