package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class MemberDAO {
	
	Connection con = null; // DB와 연동하는 객체	
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체
	ResultSet rs = null; // SQL문을 실행한 후 결과 값을 가지고 오는 객체
	String sql = null;
	
	public MemberDAO() {
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

	// MEMBER10 테이블 전체 목록 조회
	public List<MemberDTO> MemberList() {
		List<MemberDTO> list = new ArrayList<>();
		
		try {
			// 1. 쿼리문 작성
			sql = "SELECT * FROM MEMBER10 ORDER BY NUM";
			
			// 2. 전송 객체 저장
			pstmt = con.prepareStatement(sql);
			
			// 3. DB에 쿼리문 전송
			rs = pstmt.executeQuery();
			
			// 4. 결과 값 DTO 객체에 저장하여 list 참조변수에 추가
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setNum(rs.getInt("NUM"));
				dto.setMemId(rs.getString("MEMID"));
				dto.setMemName(rs.getString("MEMNAME"));
				dto.setPwd(rs.getString("PWD"));
				dto.setAge(rs.getInt("AGE"));
				dto.setMileage(rs.getInt("MILEAGE"));
				dto.setJob(rs.getString("JOB"));
				dto.setAddr(rs.getString("ADDR"));
				dto.setRegdate(rs.getString("REGDATE"));
				
				list.add(dto);
			}
			
			// 5. 자원 종료
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;
	} // MemberList() - End

	public int insertMember(MemberDTO dto) {
		int result = 0;
		int count = 0;
		
		try {
			sql = "SELECT MAX(NUM) FROM MEMBER10";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "INSERT INTO MEMBER10 VALUES(?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getMemId());
			pstmt.setString(3, dto.getMemName());
			pstmt.setString(4, dto.getPwd());
			pstmt.setInt(5, dto.getAge());
			pstmt.setInt(6, dto.getMileage());
			pstmt.setString(7, dto.getJob());
			pstmt.setString(8, dto.getAddr());
			result = pstmt.executeUpdate();
			
			pstmt.close(); rs.close(); con.close();			
		} catch (SQLException e) { e.printStackTrace(); }
	
		return result;
	} // insertMember() - End
}


















