<%@page import="com.product.model.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ProductDTO dto = (ProductDTO)request.getAttribute("modify");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="yellow">
			<h1>상품 수정 페이지</h1>
		<hr width="30%" color="yellow"> <br>
		
		<form action="<%=request.getContextPath() %>/update" method="post">
			<table border="1" cellspacing="0" width="500">			
				<tr>
					<th>제품번호</th>
					<td><input type="text" name="pno" value="<%=dto.getPno() %>" readonly></td>
				</tr>
				<tr>
					<th>제품명</th>
					<td><input type="text" name="pname" value="<%=dto.getPname() %>"></td>
				</tr>
				<tr>
					<th>재고량</th>
					<td><input type="text" name="stock" value="<%=dto.getStock() %>"></td>
				</tr>
				<tr>
					<th>가격</th>
					<td><input type="text" name="price" value="<%=dto.getPrice() %>"></td>
				</tr>
				<tr>
					<th>출판사</th>
					<td><input type="text" name="company" value="<%=dto.getCompany() %>"></td>
				</tr>
				<tr>
					<th>카테고리번호</th>
					<td><input type="text" name="cno" value="<%=dto.getCno() %>"></td>
				</tr>
				<tr>
					<th>카테고리명</th>
					<td><input type="text" name="cname" value="<%=dto.getCname() %>"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="상품수정">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>	
		</form>
	</div>
</body>
</html>