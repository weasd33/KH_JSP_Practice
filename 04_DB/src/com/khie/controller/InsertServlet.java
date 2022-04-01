package com.khie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

@WebServlet("/insert_ok")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertServlet() {
        super();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	// insert.jsp 페이지에서 넘어온 데이터를 DEPT 테이블에 저장 로직
    	
    	request.setCharacterEncoding("UTF-8"); // 요청에서 한글이 넘어올 수 있음.
    	response.setContentType("text/html; charset=UTF-8");
    	
    	// 1단계 : 부서등록 폼 페이지에서 넘어온 데이터 받기
    	int deptno = Integer.parseInt(request.getParameter("deptno").trim());
    	String dname = request.getParameter("dname").trim();
    	String loc = request.getParameter("loc").trim();
    	
    	// 2단계 : DTO 객체를 이용하여 DB에 전송해야 함
    	DeptDTO dto = new DeptDTO();
    	
    	dto.setDeptno(deptno);
    	dto.setDname(dname);
    	dto.setLoc(loc);
    	
    	// 3단계 : DB에 DTO 객체 전송
    	DeptDAO dao = new DeptDAO();
    	
    	int result = dao.insertDept(dto);
    	
    	PrintWriter out = response.getWriter();
    	
    	if(result > 0) { // 부서 추가 성공
    		out.println("<script>");
    		out.println("alert('Success!!')");
    		out.println("location.href='select'");
    		out.println("</script>");
    	} else { // 부서 추가 실패
    		out.println("<script>");
    		out.println("alert('Fail...')");
    		out.println("history.back()");
    		out.println("</script>");
    	}
    }
}
