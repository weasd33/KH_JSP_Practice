package com.shop.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;

public class UserLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 로그아웃 시 세션 종료
		
		HttpSession session = request.getSession();
		
		session.invalidate(); // 모든 세션 정보 종료
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("index.jsp");
		
		return forward;
	}
}
