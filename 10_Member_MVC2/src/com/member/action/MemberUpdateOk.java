package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberUpdateOk implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("mem_id");
		String name = request.getParameter("mem_name");
		String pwd = request.getParameter("mem_pwd");
		int age = Integer.parseInt(request.getParameter("mem_age"));
		int mileage = Integer.parseInt(request.getParameter("mem_mileage"));
		String job = request.getParameter("mem_job");
		String addr = request.getParameter("mem_addr");
		
		MemberDTO dto = new MemberDTO();
		dto.setNum(num);
		dto.setMemId(id);
		dto.setMemName(name);
		dto.setPwd(pwd);
		dto.setAge(age);
		dto.setMileage(mileage);
		dto.setJob(job);
		dto.setAddr(addr);
		
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.updateMember(dto);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.println("<script>");
			out.println("alert('Success!!')");
			out.println("location.href='content.do?num=" + num + "'");
			out.println("</script>");
		} else if(result == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('Fail...')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
