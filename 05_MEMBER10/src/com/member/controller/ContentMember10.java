package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

@WebServlet("/content")
public class ContentMember10 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContentMember10() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전체리스트에서 회원의 이름을 클릭했을 때 넘어 온 
		// 회원번호에 해당하는 정보를 DB에서 조회 후 정보 가져오기
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		
		MemberDTO content = dao.getContentMember(num);
		
		request.setAttribute("content", content);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/memberContent.jsp");
		
		rd.forward(request, response);
	}	
}
