package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductDAO;
import com.product.model.ProductDTO;

@WebServlet("/update")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public UpdateProduct() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 수정 페이지
    	String pno = request.getParameter("pno");
    	
    	ProductDAO dao = ProductDAO.getInstance();
    	ProductDTO dto = dao.contentProduct(pno);
    	
    	request.setAttribute("modify", dto);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("view/productUpdate.jsp");
    	
    	rd.forward(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 수정 로직
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
		int result = dao.updateProduct(dto);
		
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.println("<script>");
			out.println("alert('Update Success!!')");
			out.println("location.href='content?pno=" + dto.getPno() + "'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('Update Fail...')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}












