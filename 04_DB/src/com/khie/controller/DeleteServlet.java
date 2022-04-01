package com.khie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 삭제 버튼을 누르면 get 방식으로 넘어온 부서번호를 DB에서 삭제
		
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 삭제 버튼을 눌렀을 때 get 방식으로 넘어오는 부서번호 받기
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		
		// 2. 삭제할 부서번호 DB에 넘기기
		DeptDAO dao = new DeptDAO();
		
		int result = dao.deleteDept(deptno);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) { // 부서 삭제 성공
    		out.println("<script>");
    		out.println("alert('Delete Success!!')");
    		out.println("location.href='select'");
    		out.println("</script>");
    	} else { // 부서 삭제 실패
    		out.println("<script>");
    		out.println("alert('Delete Fail...')");
    		out.println("history.back()");
    		out.println("</script>");
    	}
	}

}
