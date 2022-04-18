package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글쓰기 페이지에서 넘어온 데이터들을
		// DB에 저장하는 비지니스 로직
		
		UploadDTO dto = new UploadDTO();
		
		// 첨부파일이 저장될 경로
		String saveFolder = "C:\\NCS\\java\\workspace(jsp)\\14_Board_FileUpload\\WebContent\\upload";
		
		// 첨부파일 최대 크기 지정
		int fileSize = 10 * 1024 * 1024; // 10MB
		
		// 파일 업로드를 진행 시 이진 파일 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(
				request,	// 일반적인 request 객체
				saveFolder, // 첨부파일이 저장될 경로 
				fileSize, 	// 업로드할 파일의 최대 크기
				"UTF-8",	// 문자 인코딩 방식
				new DefaultFileRenamePolicy() // 파일의 이름이 같은 경우 중복이 안되게 설정
		);
		
		// 폼 페이지에서 넘어온 데이터 받기
		String writer = multi.getParameter("writer");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String pwd = multi.getParameter("pwd");
		
		// type="file"은 getParameter()가 아닌 getFile()로 받는다.
		File file = multi.getFile("file");
		
		if(file != null) { // 첨부파일이 존재하는 경우
			// 파일 이름 받기
			String fileName = file.getName();
			
			// 날짜 객체 생성
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			// .../upload/2022-04-18
			String homedir = saveFolder + "/" + year + "-" + month + "-" + day;
			
			// 날짜 폴더 생성
			File path1 = new File(homedir);
			
			if(!path1.exists()) { // 폴더가 존재하지 않는 경우
				path1.mkdir();	// 폴더 생성
			}
			
			// .../upload/2022-04-18/작성자_파일명 
			String reFileName = writer + "_" + fileName;
			file.renameTo(new File(homedir + "/" + reFileName));
			
			// DB에 저장되는 값 ==> "/2022-04-18/작성자_파일명"
			String fileDBName = "/" + year + "-" + month + "-" + day + "/" + reFileName;
			
			dto.setFile(fileDBName);
		}
		
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPwd(pwd);
		
		UploadDAO dao = UploadDAO.getInstance();
		
		int result = dao.insertUpload(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			forward.setRedirect(true);
			forward.setPath("upload_list.do");
		} else {
			out.println("<script>");
			out.println("alert('Fail..')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
