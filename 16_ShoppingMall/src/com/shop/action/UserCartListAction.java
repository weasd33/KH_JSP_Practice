package com.shop.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CartDAO;
import com.shop.model.CartDTO;

public class UserCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// shop_cart 테이블에 사용자 아이디에 해당하는 장바구니
		// 목록을 조회하여 View Page로 이동
		
		HttpSession session = request.getSession();
		
		// 로그인 할 때 바인딩 해 놓은 아이디 세션 가져옴. 
		String userId = (String)session.getAttribute("userId");
		
		CartDAO dao = CartDAO.getInstance();
		List<CartDTO> list = dao.getCartList(userId);
		
		request.setAttribute("cartList", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("user/user_cart_list.jsp");
		
		return forward;
	}
}
