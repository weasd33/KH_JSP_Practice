package com.shop.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;
import com.shop.model.CartDTO;

public class UserCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 장바구니 담기 시 해당 데이터들을 장바구니 테이블에 저장
		
		String cart_name = request.getParameter("p_name");
		int cart_price = Integer.parseInt(request.getParameter("p_price"));
		int cart_pqty = Integer.parseInt(request.getParameter("p_qty"));
		
		// hidden
		int cart_pnum = Integer.parseInt(request.getParameter("p_num"));
		String cart_spec = request.getParameter("p_spec");
		String cart_image = request.getParameter("p_image");
		String userId = request.getParameter("userId");
		
		CartDTO dto = new CartDTO();
		dto.setCart_pnum(cart_pnum);
		dto.setCart_userId(userId);
		dto.setCart_pname(cart_name);
		dto.setCart_price(cart_price);
		dto.setCart_pqty(cart_pqty);
		dto.setCart_pspec(cart_spec);
		dto.setCart_pimage(cart_image);
		
		CartDAO dao = CartDAO.getInstance();
		
		int check = dao.insertCart(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			forward.setRedirect(true);
			forward.setPath("user_cart_list.do");
		} else {
			out.println("<script>");
			out.println("alert('Error...')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}
}
