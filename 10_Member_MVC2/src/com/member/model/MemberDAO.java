package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = null;

	// 1. 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	// 기본 생성자의 접근 제어자를 private으로 바꿔 줘야 한다.

	// 2. ProductDAO 객체를 정적 멤버로 선언
	private static MemberDAO instance;

	private MemberDAO() {}

	// 3. 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 한다.
	public static MemberDAO getInstance() {

		if (instance == null) {
			instance = new MemberDAO();
		}

		return instance;
	}

	// DB 연동 - 커넥션풀 방식 사용
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

	// 전체 목록 조회
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			openConn();
			
			sql = "SELECT * FROM MEMBER10 ORDER BY NUM DESC";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
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
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return list;		
	} // getMemberList() - End

	// 회원 등록
	public int insertMember(MemberDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			
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
			
			rs.close(); pstmt.close(); con.close();			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // insertMember() - End

	// 회원 상세 정보
	public MemberDTO contentMember(int num) {
		MemberDTO dto = new MemberDTO();
		
		try {
			openConn();
			
			sql = "SELECT * FROM MEMBER10 WHERE NUM = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
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
	} // contentMember() - End

	// 회원 정보 수정
	public int updateMember(MemberDTO dto) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "SELECT PWD FROM MEMBER10 WHERE NUM = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPwd().equals(rs.getString("PWD"))) { // 비밀번호가 일치 할 경우
					sql = "UPDATE MEMBER10 SET"
							+ " AGE = ?,"
							+ " MILEAGE = ?,"
							+ " JOB = ?,"
							+ " ADDR = ?"
							+ " WHERE NUM = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, dto.getAge());
					pstmt.setInt(2, dto.getMileage());
					pstmt.setString(3, dto.getJob());
					pstmt.setString(4, dto.getAddr());
					pstmt.setInt(5, dto.getNum());
					
					result = pstmt.executeUpdate();
				} else { // 비밀번호가 일치하지 않을 경우
					result = -1;
				}
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) { e.printStackTrace(); }
		
		return result;
	} // updateMember - End

}















