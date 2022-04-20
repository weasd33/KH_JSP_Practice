package com.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.CustomerDAO;
import com.member.model.CustomerDTO;

@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력 폼에서 넘어온 데이터들을
		// DB에 저장하는 비지니스 로직
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();

		String user_id = request.getParameter("id").trim();
		String user_name = request.getParameter("name").trim();
		int user_age = Integer.parseInt(request.getParameter("age").trim());
		String user_phone = request.getParameter("phone").trim();
		String user_addr = request.getParameter("addr").trim();
		
		CustomerDTO dto = new CustomerDTO();
		
		dto.setId(user_id);
		dto.setName(user_name);
		dto.setAge(user_age);
		dto.setPhone(user_phone);
		dto.setAddr(user_addr);
		
		CustomerDAO dao = CustomerDAO.getInstance();
		
		int check = dao.insertCustomer(dto);
		
		// 결과 값을 클라이언트(Ajax)에 보냄
		out.println(check);
	}

}
