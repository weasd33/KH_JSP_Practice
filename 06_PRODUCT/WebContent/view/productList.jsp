<%@page import="java.util.List"%>
<%@page import="com.product.model.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<ProductDTO> product = (List<ProductDTO>)request.getAttribute("pList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="red">
			<h1>PRODUCT 테이블 전체 리스트</h1>
		<hr width="30%" color="red"> <br>
		
		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>제품No.</th> <th>카테고리</th>
				<th>제품명</th> <th>출판사</th>
			</tr>
			
			<%
				if(product.size() > 0) {
					for(int i = 0; i < product.size(); i++) {
						ProductDTO dto = product.get(i);
			%>
						<tr>
							<td><%=dto.getPno() %></td>
							<td><%=dto.getCname() %></td>
							<td><%=dto.getPname() %></td>
							<td><%=dto.getCompany() %></td>
						</tr>
			<%		}
				} else {
			%>
					<tr>
						<td colspan="4" align="center">
							검색된 제품 목록이 없습니다...
						</td>
					</tr>
			<%  } %>							
		</table>
	</div>
</body>
</html>