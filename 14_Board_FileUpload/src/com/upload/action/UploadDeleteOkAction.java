package com.upload.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;

public class UploadDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어온 글 번호와 비밀번호를 통해
		// DB에서 글 삭제
		int no = Integer.parseInt(request.getParameter("no"));
		String pwd = request.getParameter("pwd");
		
		UploadDAO dao = UploadDAO.getInstance();
		
		int result = dao.deleteUpload(no, pwd);
		
		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		
		if(result > 0) {
			forward.setRedirect(true);
			forward.setPath("upload_list.do");
		} else if (result == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("Fail..");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
