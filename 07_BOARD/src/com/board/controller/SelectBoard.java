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
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	
    	BoardDAO dao = BoardDAO.getInstance();
    	List<BoardDTO> dto = dao.selectBoard();
    	
    	request.setAttribute("selectAll", dto);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("view/boardSelectAll.jsp");
    	
    	rd.forward(request, response);
    }
}
