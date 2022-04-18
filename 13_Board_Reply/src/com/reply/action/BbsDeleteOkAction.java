package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;

public class BbsDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int no = Integer.parseInt(request.getParameter("no"));
		String pwd = request.getParameter("pwd");
		
		BbsDAO dao = BbsDAO.getInstance();
		int result = dao.deleteBbs(no, pwd);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		} else if(result == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('Fail..')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
