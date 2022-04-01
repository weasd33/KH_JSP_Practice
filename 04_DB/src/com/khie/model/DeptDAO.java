package com.khie.model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
	DAO(Data Access Object) : 데이터 접근 객체 ==> DB에 접속(연동)하는 객체
	- DAO란 데이터베이스에 접속해서 데이터 추가, 수정, 삭제, 조회 등의 작업을 하는 클래스
	- 일반적으로 JSP 또는 Servlet에서 위의 작업들을 같이 사용할 수 있지만,
	유지보수, 코드의 모듈화를 위해서 DAO 클래스를 따로 만들어서 사용
*/

public class DeptDAO {

	Connection con = null; // DB와 연동하는 객체	
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체
	ResultSet rs = null; // SQL문을 실행한 후 결과 값을 가지고 오는 객체
	String sql = null;
	
	public DeptDAO() { 
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String user = "SHIM";
		String password = "1234";
		
		try {
			// 1단계 : 오라클 드라이버 로딩
			Class.forName(driver);
			
			// 2단계 : 오라클 DB와 연결
			con = DriverManager.getConnection(url, user, password);
			
			if(con != null) {
				System.out.println("DB 연결 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// DEPT 테이블에서 부서 목록 전체 리스트를 조회하여 해당 리스트를 반환
	public List<DeptDTO> selectList() {
		List<DeptDTO> list = new ArrayList<>(); // 다형성
		
		try {
			// 3단계 : DB에 전송하기 위한 쿼리문 작성
			sql = "SELECT * FROM DEPT ORDER BY DEPTNO";
			
			// 4단계 : SQL문을 DB 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			
			// 5단계 : SQL문을 DB에 전송 및 SQL문 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DeptDTO dto = new DeptDTO();
				
				int deptno = rs.getInt("DEPTNO");
				String dname = rs.getString("DNAME");
				String loc = rs.getString("LOC");
				
				dto.setDeptno(deptno);
				dto.setDname(dname);
				dto.setLoc(loc);
				
				list.add(dto);
			}			
			// 6단계 : 자원 종료
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	} // selectList() - End

	// 부서 테이블 등록 메서드
	public int insertDept(DeptDTO dto) {
		int result = 0;
		
		try {
			// 1. DB에 전송할 쿼리문 작성
			sql = "INSERT INTO DEPT VALUES(?, ?, ?)";
			
			// 2. 쿼리문 DB 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setString(3, dto.getLoc());
			
			// 3. 쿼리문 DB에 전송 및 실행
			result = pstmt.executeUpdate(); // 성공 시 1 반환
			
			// 4. 자원 종료
			pstmt.close(); con.close();
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	}// insertDept() - End
}












