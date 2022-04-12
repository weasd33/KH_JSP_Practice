package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		request.setAttribute("no", no);
	}
}
