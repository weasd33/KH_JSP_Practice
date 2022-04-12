package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardDeleteOk implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String pwd = request.getParameter("pwd");
		
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.deleteBoard(no, pwd);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.println("<script>");
			out.println("alert('Success!!')");
			out.println("location.href='select.do'");
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
