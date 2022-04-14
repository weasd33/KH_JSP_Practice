package com.board1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;

public class BoardDeleteOk implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// DB에서 해당 게시글 삭제
		
		String pwd = request.getParameter("pwd");
		
		int no = Integer.parseInt(request.getParameter("no"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int result = dao.deleteBoard(no, pwd);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.println("<script>");
			out.println("alert('Success!!')");
			out.println("location.href='board_list.do?page="+ nowPage + "'");
			out.println("</script>");
		} else if(result == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('Fail...')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
