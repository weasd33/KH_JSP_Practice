package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 답변글 페이지에서 넘어온 데이터 처리
		int no = Integer.parseInt(request.getParameter("no"));
		int group = Integer.parseInt(request.getParameter("group"));
		int step = Integer.parseInt(request.getParameter("step"));
		int indent = Integer.parseInt(request.getParameter("indent"));
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BbsDTO dto = new BbsDTO();
		dto.setNo(no);
		dto.setGroup(group);
		dto.setStep(step);
		dto.setIndent(indent);
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPwd(pwd);
		
		BbsDAO dao = BbsDAO.getInstance();
		
		// 원글에 기존에 작성했던 댓글이 존재하는 경우
		// 해당 댓글에 step을 1 증가
		dao.replyUpdate(group, step);
		
		// 댓글을 DB에 저장
		int result = dao.replyBbs(dto);
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		} else {
			out.println("<script>");
			out.println("alert('Fail..')");
			out.println("history.back()");
			out.println("<script>");
		}
		
		return forward;
	}

}
