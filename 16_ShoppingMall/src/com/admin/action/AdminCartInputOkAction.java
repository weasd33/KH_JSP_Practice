package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;

public class AdminCartInputOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 카테고리 등록 폼에서 넘어온 데이터 처리
		String cart_code = request.getParameter("cart_code").trim();
		String cart_name = request.getParameter("cart_name").trim();		
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		int check = dao.insertCategory(cart_code, cart_name);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			// 카테고리 등록 후 카테고리 목록으로 이동
			forward.setRedirect(true);
			forward.setPath("admin_cart_list.do");
		} else {
			out.print("<script>");
			out.print("alert('Error...')");
			out.print("history.back()");
			out.print("<script>");
		}
		
		return forward;
	}

}
