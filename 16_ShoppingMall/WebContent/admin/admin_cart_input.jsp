<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 등록</title>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<hr width="80%" color="marmoon">
			<h1>카테고리 등록</h1>
		<hr width="80%" color="marmoon"> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/admin_cart_input_ok.do">
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>카테고리 코드</th>
					<td><input name="cart_code"></td>
				</tr>
				<tr>
					<th>카테고리 이름</th>
					<td><input name="cart_name"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="등록">&nbsp;&nbsp;&nbsp;
						<input type="button" value="취소" onclick="history.back()">		
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>