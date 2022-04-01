package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

@WebServlet("/select")
public class SelectMember10 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectMember10() {
        super();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 비지니스 로직
    	// MEMBER10 테이블의 전체 회원 목록을 화면에 보여달라고 요청
    	// DB에서 MEMBER10 테이블의 회원 전체 리스트를 조회하여
    	// 해당 전체 리스트를 view page로 응답
    	
    	// 1. DB 연동
    	MemberDAO dao = new MemberDAO();
    	
    	// 2. 전체 목록 조회
    	List<MemberDTO> memberList = dao.MemberList();
    	
    	// 3. DB에서 가져온 정보 view page로 이동
    	request.setAttribute("member", memberList);
    	
    	// 4. 페이지 이동
    	RequestDispatcher rd = request.getRequestDispatcher("view/memberList.jsp");
    	
    	rd.forward(request, response);
    }
}
