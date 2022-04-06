package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@WebServlet("/content")
public class ContentBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContentBoard() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 작성사 상세 정보
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("no"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 조회수 증가
		dao.hitBoard(num);
		
		// 상세 내역 조회
		BoardDTO content = dao.contentBoard(num);
		
		request.setAttribute("content", content);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/boardContent.jsp");
		
		rd.forward(request, response);
	}
}
