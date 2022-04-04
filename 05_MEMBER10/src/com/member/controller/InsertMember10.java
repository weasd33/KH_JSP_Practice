package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

@WebServlet("/insert")
public class InsertMember10 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertMember10() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// memberJoin.jsp 페이지에서 넘어온 정보들을 DB에 넘김
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 데이터 받기
		String id = request.getParameter("mem_id");
		String name = request.getParameter("mem_name");
		String pwd = request.getParameter("mem_pwd");
		int age = Integer.parseInt(request.getParameter("mem_age"));
		int mileage = Integer.parseInt(request.getParameter("mem_mileage"));
		String job = request.getParameter("mem_job");
		String addr = request.getParameter("mem_addr");
		
		MemberDTO dto = new MemberDTO();
		dto.setMemId(id);
		dto.setMemName(name);
		dto.setPwd(pwd);
		dto.setAge(age);
		dto.setMileage(mileage);
		dto.setJob(job);
		dto.setAddr(addr);
		
		MemberDAO dao = new MemberDAO();
		int result = dao.insertMember(dto);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.println("<script>");
			out.println("alert('Insert Success!!')");
			out.println("location.href='select'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('Insert Fail...')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
