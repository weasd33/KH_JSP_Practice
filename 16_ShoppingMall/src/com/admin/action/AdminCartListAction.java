package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;
import com.shop.model.CategoryDTO;

public class AdminCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 카테고리 테이블 전체 리스트 조회 후 View Page로 이동
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		List<CategoryDTO> list = dao.getCategoryList();
		
		request.setAttribute("categoryList", list);
		
		ActionForward forward = new ActionForward();
		
		// View Page 이동
		forward.setRedirect(false);
		forward.setPath("admin/admin_cart_list.jsp");
		
		return forward;
	}

}
