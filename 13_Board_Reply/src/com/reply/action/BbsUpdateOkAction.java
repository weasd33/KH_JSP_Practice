package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정 페이지에서 넘어온 데이터 처리
		int no = Integer.parseInt(request.getParameter("no"));
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
	
		BbsDTO dto = new BbsDTO();
		dto.setNo(no);
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPwd(pwd);
		
		BbsDAO dao = BbsDAO.getInstance();
		int result = dao.updateBbs(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			forward.setRedirect(true);
			forward.setPath("bbs_content.do?no=" + no);
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
