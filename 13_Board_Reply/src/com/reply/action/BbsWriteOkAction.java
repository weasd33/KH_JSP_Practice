package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글 작성 폼 페이지에서 넘어온 데이터 처리
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BbsDTO dto = new BbsDTO();
		
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPwd(pwd);
		
		BbsDAO dao = BbsDAO.getInstance();
		int result = dao.insertBoard(dto);
		
		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		
		if(result > 0) {
			out.println("<script>");
			out.println("alert('Success!!')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		} else {
			out.println("<script>");
			out.println("alert('Fail...')");
			out.println("</script>");
			forward.setRedirect(false);
			forward.setPath("view/bbs_write.jsp");
		}
		
		return forward;
	}

}
