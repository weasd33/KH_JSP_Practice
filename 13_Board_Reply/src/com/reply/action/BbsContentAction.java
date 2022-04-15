package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		BbsDAO dao = BbsDAO.getInstance();
		
		// 조회수 증가
		dao.bbsHit(no);
		
		// 상세 내역 조회
		BbsDTO dto = dao.getBbsContent(no);
		
		request.setAttribute("content", dto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("view/bbs_content.jsp");
		
		return forward;
	}

}
