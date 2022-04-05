package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductDAO;
import com.product.model.ProductDTO;

@WebServlet("/insert")
public class InsertProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertProduct() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB에서 카테고리 테이블의 전체 리스트를 조회 후
		// 카테고리 전체 리스트를 제품 등록 폼 페이지로 이동
		
		// 싱글턴 방식이기에 객체를 여러번 생성해도 주소 값이 같음 (메모리 절약)
		ProductDAO dao = ProductDAO.getInstance();
		
		List<ProductDTO> categoryList = dao.getCategotyList();
		
		request.setAttribute("cList", categoryList);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/productInsert.jsp");
		
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 제품 폼 페이지에서 넘어온 데이터를 PRODUCT 테이블에 저장
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String pno = request.getParameter("pno");
		String pname = request.getParameter("pname");
		int stock = Integer.parseInt(request.getParameter("stock"));
		int price = Integer.parseInt(request.getParameter("price"));
		String company = request.getParameter("company");
		String cno = request.getParameter("cno");
		String cname = request.getParameter("cname");
		
		ProductDTO dto = new ProductDTO();
		dto.setPno(pno);
		dto.setPname(pname);
		dto.setStock(stock);
		dto.setPrice(price);
		dto.setCompany(company);
		dto.setCno(cno);
		dto.setCname(cname);
		
		ProductDAO dao = ProductDAO.getInstance();
		int result = dao.insertProduct(dto);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.println("<script>");
			out.println("alert('Insert Success!!')");
			out.println("location.href='select'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('Insert Fail...')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}
