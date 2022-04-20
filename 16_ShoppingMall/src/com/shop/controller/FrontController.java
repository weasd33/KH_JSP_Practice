package com.shop.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI(); // "/프로젝트명/파일명"
		String path = request.getContextPath(); // "/프로젝트명"
		String command = uri.substring(path.length() + 1);
		
		Action action = null;
		ActionForward forward = null;
		
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream("C:\\NCS\\java\\workspace(jsp)\\16_ShoppingMall\\src\\com\\shop\\controller\\mapping.properties");
		
		prop.load(fis);
		
		String value = prop.getProperty(command); // prop 파일을 읽어서 파일명을 키로 삼아 그 키의 내용을 가져 옴
		
		if(value.substring(0, 7).equals("execute")) {
			StringTokenizer st = new StringTokenizer(value, "|");
			String url_1 = st.nextToken(); // "execute"
			String url_2 = st.nextToken(); // "패키지명.클래스명"
			
			try {
				Class url = Class.forName(url_2);
				
				// 동적으로 객체를 생성하는 방법
				action = (Action) url.newInstance();	
				// 비지니스 로직을 실행하는 메서드 호출
				forward = action.execute(request, response);
			} catch (Exception e) { e.printStackTrace(); }
		} else { // value가 "excute"가 아닌 경우 view page로 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath(value);
		}
		
		if(forward != null) {
			if(forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
			} else { // false
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}
	}
}
