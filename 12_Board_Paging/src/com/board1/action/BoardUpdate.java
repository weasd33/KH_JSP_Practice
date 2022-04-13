package com.board1.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardUpdate implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 넘어온 글 번호를 가지고 DB에서 상세내역 조회 후
		// 수정 폼 페이지로 해당 상세내역을 넘겨 준다.
		int no = Integer.parseInt(request.getParameter("no"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = dao.BoardContent(no);
		
		request.setAttribute("modify", dto);
		request.setAttribute("page", nowPage);
	}
}
