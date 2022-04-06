package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@WebServlet("/insert")
public class InsertBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertBoard() {
		super();
	}

	// boardWrite.jsp에서 넘어온 데이터 처리
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 넘어온 데이터 받기
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		// 데이터 DB 전송
		BoardDTO dto = new BoardDTO();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPwd(pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.insertBoard(dto);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.println("<script>");
			out.println("alert('Success!!')");
			out.println("location.href='select'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('Fail...')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
