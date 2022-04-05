package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductDAO;

@WebServlet("/delete")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DeleteProduct() {
        super();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 상품 삭제 로직
    	
    	String pno = request.getParameter("pno");
    	
    	ProductDAO dao = ProductDAO.getInstance();
    	int result = dao.deleteProduct(pno);
    	
    	PrintWriter out = response.getWriter();
    	
    	if(result > 0) {
    		out.println("<script>");
    		out.println("alert('Delete Success!!')");
    		out.println("location.href='select'");
    		out.println("</script>");
    	} else {
    		out.println("<script>");
    		out.println("alert('Delete Fail...')");
    		out.println("history.back()");
    		out.println("</script>");
    	}
    }
}
