package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductInputOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상품 등록 폼에서 넘어온 데이터 처리
		
		// 첨부파일이 저장될 위치(경로)를 설정
		// 이 경로는 이미지 등록 시 새로고침 없이 바로 화면에 출력
		String saveFolder = "C:\\NCS\\java\\workspace(jsp)\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\16_ShoppingMall\\upload";
		
		// 첨부파일 용량(크기) 제한 - 파일 업로드 최대 크기
		int fileSize = 10 * 1024 * 1024; // 10MB
		
		// 이진파일 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(
				request,	// 일반적인 request 객체
				saveFolder, // 첨부파일이 저장될 경로 
				fileSize, 	// 업로드할 파일의 최대 크기
				"UTF-8",	// 문자 인코딩 방식
				new DefaultFileRenamePolicy() // 파일의 이름이 같은 경우 중복이 안되게 설정
		);
		
		String p_name = multi.getParameter("p_name").trim();
		String p_company = multi.getParameter("p_company").trim();
		int p_qty = Integer.parseInt(multi.getParameter("p_qty").trim());
		int p_price = Integer.parseInt(multi.getParameter("p_price").trim());
		String p_spec = multi.getParameter("p_spec").trim();
		String p_category = multi.getParameter("p_category");
		String p_content = multi.getParameter("p_content").trim();
		int p_point = Integer.parseInt(multi.getParameter("p_point").trim());
		
//		String p_image = multi.getParameter("p_image").trim();
		
		// getFilesystemName() : 서버 상에 실제로 업로드될 파일의 이름을 문자열로 반환
		String p_image = multi.getFilesystemName("p_image");
		
		ProductDTO dto = new ProductDTO();
		dto.setPname(p_name);
		dto.setPcompany(p_company);
		dto.setPqty(p_qty);
		dto.setPrice(p_price);
		dto.setPspec(p_spec);
		dto.setPcontents(p_content);
		dto.setPoint(p_point);
		dto.setPimage(p_image);
		dto.setPcategory_fk(p_category);
		
		ProductDAO dao = ProductDAO.getInstance();
		// 상품 등록 메서드
		int check = dao.insertProduct(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_product_list.do"); // 상품 목록으로 이동
		} else {
			out.println("<script>");
			out.println("alert('Insert Error..')");
			out.println("history.back()");
			out.println("<script>");
		}
		
		return forward;
	}

}
