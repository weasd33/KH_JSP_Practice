package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글 번호에 해당하는 게시글의 상세내역
		// 조회 후 view page로 이동
		
		int no = Integer.parseInt(request.getParameter("no"));
		UploadDAO dao = UploadDAO.getInstance();
		
		// 조회수 증가
		dao.uploadHit(no);
		
		// 상세내역
		UploadDTO dto = dao.uploadContent(no);
		
		request.setAttribute("content", dto);
		
		ActionForward forward = new ActionForward();
				
		forward.setRedirect(false);
		forward.setPath("view/upload_content.jsp");
		
		return forward;
	}

}
