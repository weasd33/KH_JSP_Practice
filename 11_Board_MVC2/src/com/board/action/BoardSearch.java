package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearch implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String field = request.getParameter("search_field");
		String keyword = request.getParameter("search_keyword");

		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> list = dao.searchBoard(field, keyword);
		
		request.setAttribute("list", list);
	}
}
