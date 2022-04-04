package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

@WebServlet("/update")
public class UpdateMember10 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UpdateMember10() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정 폼 페이지
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		
		MemberDTO content = dao.getContentMember(num);
		
		request.setAttribute("modify", content);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/memberUpdate.jsp");
		
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 수정 로직
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num")); // Primary Key
		String id = request.getParameter("mem_id");
		String name = request.getParameter("mem_name");
		String pwd = request.getParameter("mem_pwd");
		int age = Integer.parseInt(request.getParameter("mem_age"));
		int mileage = Integer.parseInt(request.getParameter("mem_mileage"));
		String job = request.getParameter("mem_job");
		String addr = request.getParameter("mem_addr");
		
		MemberDTO dto = new MemberDTO();
		dto.setNum(num);
		dto.setMemId(id);
		dto.setMemName(name);
		dto.setPwd(pwd);
		dto.setAge(age);
		dto.setMileage(mileage);
		dto.setJob(job);
		dto.setAddr(addr);
		
		MemberDAO dao = new MemberDAO();
		int result = dao.updateMember(dto);
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('Update Success!!')");
			out.println("location.href='content?num=" + dto.getNum() + "'");
			out.println("</script>");
		} else if(result == -1) {
			out.println("<script>");
			out.println("alert('비밀번호를 확인해 주세요.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('Update Fail...')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}








