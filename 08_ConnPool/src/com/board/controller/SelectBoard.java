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

@WebServlet("/select")
public class SelectBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectBoard() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> list = dao.selectAllBoard();
		
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/boardSelectAll.jsp");
		
		rd.forward(request, response);
	}

}
