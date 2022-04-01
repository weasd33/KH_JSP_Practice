package com.khie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비지니스 로직
		// index.jsp 페이지에서 요청 ==> 전체 부서 목록을 화면에 보여달라고 요청
		// 요청에 대해서 응답
		
		// 1단계 : DB와 연결
		// 준비과정 : DAO(Data Access Object) 객체 생성
		//			DTO(Data Transfer Object) 객체 생성
		DeptDAO dao = new DeptDAO();
		
		// 2단계 : DB에서 DEPT 테이블의 전체 목록 조회
		List<DeptDTO> deptList = dao.selectList();
		
		// 3단계 : 페이지 이동 시 데이터를 같이 넘김
		request.setAttribute("List", deptList);
		
		// 4단계 : 페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher("select.jsp");
		
		rd.forward(request, response);
	}
}
