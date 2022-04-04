package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

@WebServlet("/delete")
public class DeleteMember10 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteMember10() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 넘겨 받은 번호에 해당하는 회원을 삭제
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.deleteMember(num);
		
//		dao.updateNum(num);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.println("<script>");
			out.println("alert('Delete Success!!')");
			out.println("location.href='select'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('Delete Fail...')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}
