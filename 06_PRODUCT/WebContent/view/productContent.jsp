<%@page import="com.product.model.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ProductDTO content = (ProductDTO)request.getAttribute("content");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="skyblue">
			<h1>PRODUCT 테이블 제품 상세 정보</h1>
		<hr width="30%" color="skyblue"> <br>
		
		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>제품번호</th>
				<td><%=content.getPno() %></td>
			</tr>
			<tr>
				<th>제품명</th>
				<td><%=content.getPname() %></td>
			</tr>
			<tr>
				<th>재고량</th>
				<td><%=content.getStock() %></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><%=content.getPrice() %></td>
			</tr>
			<tr>
				<th>출판사</th>
				<td><%=content.getCompany() %></td>
			</tr>
			<tr>
				<th>카테고리번호</th>
				<td><%=content.getCno() %></td>
			</tr>
			<tr>
				<th>카테고리명</th>
				<td><%=content.getCname() %></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="상품수정" onclick="location.href='update?pno=<%=content.getPno() %>'">
					<input type="button" value="상품목록" onclick="location.href='select'">
					<input type="button" value="상품삭제" onclick="if(confirm('삭제하시겠습니까?')) 
						{location.href='delete?pno=<%=content.getPno() %>'}	else {return;}">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>