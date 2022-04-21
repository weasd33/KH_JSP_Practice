<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Main</title>
<style type="text/css">

	ul li {
		list-style: none;
		display: inline-block;
		margin-left: 80px;
	}

</style>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<ul>
			<li><a href="<%=request.getContextPath() %>/admin_cart_input.do">[카테고리 등록]</a></li>
			<li><a href="<%=request.getContextPath() %>/admin_cart_list.do">[카테고리 목록]</a></li>
			<li><a href="<%=request.getContextPath() %>/admin_product_input.do">[상품 등록]</a></li>
		</ul>
	</div>
	
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>