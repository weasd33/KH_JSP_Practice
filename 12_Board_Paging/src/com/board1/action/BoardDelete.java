package com.board1.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제할 글 번호를 가지고 삭제 폼 페이지 이동
		int no = Integer.parseInt(request.getParameter("no"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		request.setAttribute("no", no);
		request.setAttribute("page", nowPage);
	}

}
