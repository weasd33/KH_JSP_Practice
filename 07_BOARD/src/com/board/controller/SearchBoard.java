package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@WebServlet("/search")
public class SearchBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchBoard() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 검색 폼 창에서 넘어 온 검색어를 가지고 DB에서
		// 검색어에 해당하는 모든 게시물을 반환 받아서 view로 이동
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String find_field = request.getParameter("find_field");
		String find_name = request.getParameter("find_name");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		List<BoardDTO> search = dao.searchBoard(find_field, find_name);
		
		request.setAttribute("search", search);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/boardSearch.jsp");
		
		rd.forward(request, response);
	}
}
