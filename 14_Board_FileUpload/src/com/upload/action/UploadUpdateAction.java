package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글번호에 해당하는 게시글을 조회하여
		// 수정 폼 페이지로 이동
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		UploadDAO dao = UploadDAO.getInstance();
		UploadDTO dto = dao.uploadContent(no);
		
		request.setAttribute("modify", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/upload_update.jsp");
		
		return forward;
	}

}
