<%@page import="com.shop.model.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.shop.model.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	CategoryDAO dao = CategoryDAO.getInstance();

	List<CategoryDTO> list = dao.getCategoryList();
	
	request.setAttribute("categoryList", list);
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	a {
		text-decoration: none;
	}

</style>
</head>
<body>
	<div align="center">
		<table border="1" cellspacing="0" width="800">
			<tr>
				<td colspan="2" align="center">
					<a href="<%=request.getContextPath() %>/user_main.do">홈 바로가기</a>&nbsp;&nbsp;&nbsp;
					<a href="<%=request.getContextPath() %>/user_cart_list.do">장바구니</a>&nbsp;&nbsp;&nbsp;
					<a href="#">${userName }님 환영합니다.</a>&nbsp;&nbsp;&nbsp;
					<a href="<%=request.getContextPath() %>/user_logout.do">로그아웃</a>&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td width="300" align="center" valign="top">
					<table cellspacing="0">
						<c:set var="list" value="${categoryList }" />
						<tr>
							<th>카테고리 코드</th>
						</tr>
						
						<c:if test="${!empty list }">
							<c:forEach items="${list }" var="dto">
								<tr>
									<td>
										<a href="<%=request.getContextPath() %>/user_category_list.do?code=${dto.getCategory_code() }">
											${dto.getCategory_name() } [${dto.getCategory_code() }]
										</a>
									</td>
								</tr>
							</c:forEach>					
						</c:if>
					</table>
				</td>
				<td> <%-- 이하의 영역은 본문 영역이 된다. --%>
