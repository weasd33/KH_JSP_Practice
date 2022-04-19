package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어온 글 번호와 비밀번호를 통해
		// DB에서 글 삭제
		int no = Integer.parseInt(request.getParameter("no"));
		String pwd = request.getParameter("pwd");

		UploadDAO dao = UploadDAO.getInstance();

		UploadDTO dto = dao.uploadContent(no);

		// upload 폴더에 업로드된 파일 삭제
		String upload = "C:\\NCS\\java\\workspace(jsp)\\14_Board_FileUpload\\WebContent\\upload";

		// DB에서 업로드된 파일 가져오기
		String fileName = dto.getFile(); // /날짜/작성자_파일명

		int result = 0;

		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();

		if (pwd.equals(dto.getPwd())) {
			result = dao.deleteUpload(no, pwd);
			
			if (fileName != null) { // 첨부 파일이 있는 경우
				File file = new File(upload + fileName);
				file.delete(); // 기존 이진 파일 제거
			}
			
			if(result > 0) {
				forward.setRedirect(true);
				forward.setPath("upload_list.do");
			} else {
				out.println("<script>");
				out.println("alert('Fail..')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}

		return forward;
	}

}
