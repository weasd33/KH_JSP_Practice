package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberJoinAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 회원 등록 폼 페이지로 이동
		
		return "view/memberJoin.jsp";
	}

}
