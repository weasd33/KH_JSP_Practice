package com.board1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.action.Action;
import com.board1.action.BoardListAction;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI(); // "/프로젝트명/파일명"
		String path = request.getContextPath(); // "/프로젝트명"
		String command = uri.substring(path.length() + 1);
		
		Action action = null;
		String viewPage = null;
		
		if(command.equals("board_list.do")) {
			action = new BoardListAction();
			action.execute(request, response);
			viewPage = "view/boardList.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		
		rd.forward(request, response);
	}
}
