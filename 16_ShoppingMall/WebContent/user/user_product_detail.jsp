<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 정보</title>
<script type="text/javascript">

	function goCart() {
		document.frm.action = "<%=request.getContextPath() %>/user_cart_add.do";
		document.frm.submit();
	}
	
</script>
</head>
<body>
	<jsp:include page="../include/user_top.jsp" />
	<br>
	
	<table border="1" cellspacing="0" width="500">
		<c:set var="dto" value="${productCont }" />
		<c:if test="${!empty dto }">
			<tr>
				<td colspan="2" align="center">
					<b>[${dto.getPname() }] 정보</b>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center" height="30"></td>
			</tr>
			
			<tr>
				<td align="center">
					<img src="<%=request.getContextPath() %>/upload/${dto.getPimage() }"
						width="180" height="180">
				</td>
				
				<td>
					<form method="post" name="frm">
					
						<input type="hidden" name="p_num" value="${dto.getPnum() }" >
						<input type="hidden" name="p_spec" value="${dto.getPspec() }" >
						<input type="hidden" name="p_image" value="${dto.getPimage() }" >
						<input type="hidden" name="userId" value="${userId }" >
						
						<table border="0" cellspacing="0">
							<tr>
								<th>No. : </th>
								<td>${dto.getPnum() }</td>
							</tr>
							
							<tr>
								<td colspan="2" align="center" height="20"></td>
							</tr>
							
							<tr>
								<th>상품명 : </th>
								<td><input name="p_name" value="${dto.getPname() }" readonly></td>
							</tr>
							
							<tr>
								<td colspan="2" align="center" height="20"></td>
							</tr>
							
							<tr>
								<th>가격 : </th>
								<td><input name="p_price" value="${dto.getPrice() }" readonly>원</td>
							</tr>
							
							<tr>
								<td colspan="2" align="center" height="20"></td>
							</tr>
							
							<tr>
								<th>포인트 : </th>
								<fmt:formatNumber value="${dto.getPoint() }" var="commaPoint" />
								<td>[${commaPoint }]P</td>
							</tr>
							
							<tr>
								<td colspan="2" align="center" height="20"></td>
							</tr>
							
							<tr>
								<th>수량 : </th>
								<td><input type="number" name="p_qty" min="1" max="50" value="1"></td>
							</tr>
							
							<tr>
								<td colspan="2" align="center" height="20"></td>
							</tr>
						</table>
						
						<table border="0" cellspacing="0" width="90%" align="center">
							<tr>
								<td align="center">
									<a href="javascript:goCart()">
										<img src="<%=request.getContextPath() %>/uploadFile/btn_cart.png" border="0">
									</a>
								</td>
								
								<td align="center">
									<a href="javascript:buyCart()">
										<img src="<%=request.getContextPath() %>/uploadFile/btn_buy.png" border="0">
									</a>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			
			<tr height="250" valign="top">
				<td colspan="2">
					<br>
					<b>상품 소개</b>
					<br> <br>
					${dto.getPcontents() }
				</td>
			</tr>
		</c:if>
	</table>
	
	<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>