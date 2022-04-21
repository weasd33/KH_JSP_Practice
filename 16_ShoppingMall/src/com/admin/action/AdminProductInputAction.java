package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;
import com.shop.model.CategoryDTO;

public class AdminProductInputAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 카테고리 전체 리스트를 조회 후 
		// 상품 등록 View Page로 이동
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		List<CategoryDTO> categoryList = dao.getCategoryList();
		
		request.setAttribute("categoryList", categoryList);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("admin/admin_product_input.jsp");
		
		return forward;
	}
}
