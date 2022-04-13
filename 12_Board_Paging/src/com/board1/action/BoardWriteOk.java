package com.board1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardWriteOk implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
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
			out.println("location.href='board_list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('Fail...')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
