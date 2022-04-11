package com.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// MEMBER10 테이블에 있는 회원 전체 리스트를 조회 후 VIEW로 이동
		MemberDAO dao = MemberDAO.getInstance();
		
		List<MemberDTO> memberList = dao.getMemberList();
		
		request.setAttribute("List", memberList);
		
		return "view/memberList.jsp";
	}

}
