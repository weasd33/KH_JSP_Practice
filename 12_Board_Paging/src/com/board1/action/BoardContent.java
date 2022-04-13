package com.board1.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardContent implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		// board_list.do에서 page 변수를 받기 때문에
		// 작성 해 줘야한다.
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 조회수 증가
		dao.boardHit(no);
		
		// 상세내역 조회
		BoardDTO dto = dao.BoardContent(no);
		
		request.setAttribute("content", dto);
		request.setAttribute("page", nowPage);
	}

}
