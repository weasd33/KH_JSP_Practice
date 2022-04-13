package com.board1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardUpdateOk implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정 폼 페이지에서 넘어 온 데이터 처리
		
		// type = hidden
		int no = Integer.parseInt(request.getParameter("no"));
		String db_pwd = request.getParameter("db_pwd");
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		// type = text
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BoardDTO dto = new BoardDTO();
		dto.setNo(no);
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPwd(pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		PrintWriter out = response.getWriter();
		
		if(dto.getPwd().equals(db_pwd)) {
			int result = dao.updateBoard(dto);
			
			if(result > 0) {
				out.println("<script>");
				out.println("alert('Success!!')");
				out.println("location.href='board_content.do?no=" + dto.getNo() + "&page=" + nowPage + "'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('Fail...')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
