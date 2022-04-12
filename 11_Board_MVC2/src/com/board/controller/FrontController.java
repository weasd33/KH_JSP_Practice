package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.*;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();	// "/현재 프로젝트명/파일명"
		String path = request.getContextPath(); // "/현재 프로젝트명"
		String command = uri.substring(path.length() + 1);
		
		Action action = null;
		String viewPage = null;
		
		if(command.equals("select.do")) { // 글 전체 목록 조회
			action = new BoardSelectAll();
			action.execute(request, response);
			viewPage = "view/BoardSelectAll.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		
		rd.forward(request, response);
	}
}
