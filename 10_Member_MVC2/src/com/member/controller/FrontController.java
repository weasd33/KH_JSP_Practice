package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.action.*;

// Servlet - FrontController
public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// getRequestURI() : "/프로젝트명/파일명(*.do)" 라는 문자열을 반환
		String uri = request.getRequestURI();
		System.out.println("URI >> " + uri);
		
		// getContextPath() : 현재 프로젝트명을 문자열로 반환
		String path = request.getContextPath();
		System.out.println("Path >> " + path);
		
		String command = uri.substring(path.length() + 1);
		System.out.println("Command >> " + command);
		
		Action action = null;
		
		if(command.equals("select.do")) { // 전체 목록 조회
			action = new MemberListAction();
		} else if(command.equals("insert.do")) {
			action = new MemberJoinAction();
		} else if(command.equals("insert_ok.do")) { // 회원 등록
			action = new MemberJoinOkAction();
		}
		
		String path1 = action.execute(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher(path1);
		
		rd.forward(request, response);
	}
}
