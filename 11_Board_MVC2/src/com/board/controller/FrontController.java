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
		} else if(command.equals("write.do")) { // 글 작성 폼 페이지
			// 데이터를 가지고 갈 필요 없기 때문에 action은 생략
			viewPage = "view/BoardWrite.jsp";
		} else if(command.equals("write_ok.do")) { // 글 작성
			action = new BoardWrite();
			action.execute(request, response);
		} else if(command.equals("content.do")) { // 글 상세 정보
			action = new BoardContent();
			action.execute(request, response);
			viewPage = "view/BoardContent.jsp";
		} else if(command.equals("update.do")) { // 글 수정 폼 페이지
			action = new BoardContent();
			action.execute(request, response);
			viewPage = "view/BoardUpdate.jsp";
		} else if(command.equals("update_ok.do")) { // 글 수정
			action = new BoardUpdate();
			action.execute(request, response);
		} else if(command.equals("delete.do")) { // 글 삭제 폼 페이지
			action = new BoardDelete();
			action.execute(request, response);
			viewPage = "view/BoardDelete.jsp";
		} else if(command.equals("delete_ok.do")) { // 글 삭제
			action = new BoardDeleteOk();
			action.execute(request, response);
		} else if(command.equals("search.do")) { // 글 검색
			action = new BoardSearch();
			action.execute(request, response);
			viewPage = "view/BoardSearch.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		
		rd.forward(request, response);
	}
}
