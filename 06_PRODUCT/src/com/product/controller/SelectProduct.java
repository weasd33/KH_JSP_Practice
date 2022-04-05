package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductDAO;
import com.product.model.ProductDTO;

@WebServlet("/select")
public class SelectProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SelectProduct() {
        super();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 제품 전체 목록 요청에 대한 응답
    	// DB에서 PRODUCT 테이블의 전체 제품 목록 조회 후
    	// view page로 넘겨줌
    	
//    	ProductDAO dao = new ProductDAO();
    	
    	ProductDAO dao = ProductDAO.getInstance();
    	
    	List<ProductDTO> productList = dao.getProductList();
    	
    	// view page로 해당 정보 이동
    	request.setAttribute("pList", productList);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("view/productList.jsp");
    	
    	rd.forward(request, response);
    }
}
