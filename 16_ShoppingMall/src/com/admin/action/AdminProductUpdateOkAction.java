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

public class AdminProductUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정 폼 페이지에서 넘어온 데이터 처리

		// 첨부파일이 저장될 위치(경로)를 설정
		// 이 경로는 이미지 등록 시 새로고침 없이 바로 화면에 출력
		String saveFolder = "C:\\NCS\\java\\workspace(jsp)\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\16_ShoppingMall\\upload";

		// 첨부파일 용량(크기) 제한 - 파일 업로드 최대 크기
		int fileSize = 10 * 1024 * 1024; // 10MB

		// 이진파일 업로드를 위한 객체 생성
		MultipartRequest multi = new MultipartRequest(request, // 일반적인 request 객체
				saveFolder, // 첨부파일이 저장될 경로
				fileSize, // 업로드할 파일의 최대 크기
				"UTF-8", // 문자 인코딩 방식
				new DefaultFileRenamePolicy() // 파일의 이름이 같은 경우 중복이 안되게 설정
		);
		
		// 데이터 받기
		int p_num = Integer.parseInt(multi.getParameter("p_num"));
		String p_category = multi.getParameter("p_category");
		String p_name = multi.getParameter("p_name");
		String p_company = multi.getParameter("p_company");
		int p_qty = Integer.parseInt(multi.getParameter("p_qty").trim());
		int p_price = Integer.parseInt(multi.getParameter("p_price").trim());
		String p_spec = multi.getParameter("p_spec");
		String p_content = multi.getParameter("p_content");
		int p_point = Integer.parseInt(multi.getParameter("p_point").trim());
		
		ProductDTO dto = new ProductDTO();

		// 이미지 받기
		String p_image_new = multi.getFilesystemName("p_image_new");
		
		if(p_image_new == null) { // 기존 이미지를 사용할 때
			String p_image_old = multi.getParameter("p_image_old");
			
			dto.setPimage(p_image_old);
		} else { // 새로운 이미지를 사용할 때
			dto.setPimage(p_image_new);
		}
		
		dto.setPnum(p_num);
		dto.setPname(p_name);
		dto.setPcategory_fk(p_category);
		dto.setPcompany(p_company);
		dto.setPqty(p_qty);
		dto.setPrice(p_price);
		dto.setPspec(p_spec);
		dto.setPcontents(p_content);
		dto.setPoint(p_point);
		
		ProductDAO dao = ProductDAO.getInstance();
		int check = dao.updateProduct(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("admin_product_list.do");
		} else {
			out.println("<script>");
			out.println("alert('Update Error...')");
			out.println("history.back()");
			out.println("<script>");
		}
		
		return forward;
	}

}
