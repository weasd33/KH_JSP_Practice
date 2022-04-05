<%@page import="com.product.model.ProductDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<ProductDTO> category = (List<ProductDTO>)request.getAttribute("cList");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="gray">
			<h1>PRODUCT 테이블 제품 등록</h1>
		<hr width="30%" color="gray">
		
		<form method="post" action="<%=request.getContextPath() %>/insert">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>카테고리</th>
					<td>
						<select name="cno">
							<%
								if(category.size() == 0) {
							%>
								<option value="">:::카테고리:::</option>
							<%} else { 
								for(int i = 0; i < category.size(); i++) {
									ProductDTO dto = category.get(i);
							%>
									<option value="<%=dto.getCno() %>"><%=dto.getCname() %></option>
							<%	}
							  } %>
						</select>
					</td>
				</tr>
				<tr>
					<th>제품번호</th>
					<td><input type="text" name="pno"></td>
				</tr>
				<tr>
					<th>제품명</th>
					<td><input type="text" name="pname"></td>
				</tr>
				<tr>
					<th>제품수량</th>
					<td><input type="text" name="stock"></td>
				</tr>
				<tr>
					<th>제품가격</th>
					<td><input type="text" name="price"></td>
				</tr>
				<tr>
					<th>출판사</th>
					<td><input type="text" name="company"></td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td><input type="text" name="cname"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="제품등록">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>