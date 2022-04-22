<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 목록</title>
<style type="text/css">
	
	.center {
		text-align: center;
	}
	
</style>
</head>
<body>

	<jsp:include page="../include/user_top.jsp" />
	
	<table border="1" cellspacing="0" width="650" align="center">
		<tr>
			<td colspan="7" class="center">
				<h2>장바구니 목록</h2>
			</td>
		</tr>
		
		<tr>
			<th width="10%">주문번호</th> <th width="10%">상품번호</th>
			<th width="15%">상품명</th> <th width="12%">수 량</th>
			<th width="15%">단 가</th> <th width="15%">총금액</th>
			<th width="10%">삭 제</th>
		</tr>
		
		<c:set var="list" value="${cartList }" />
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="dto">
				<tr>
					<td class="center">${dto.getCart_num() }</td>
					<td class="center">${dto.getCart_pnum() }</td>
					<td class="center">
						<img src="<%=request.getContextPath() %>/upload/${dto.getCart_pimage() }"
							width="50" height="50">
					</td>
					<td class="center">${dto.getCart_pqty() }</td>
					<td class="center">
						<fmt:formatNumber value="${dto.getCart_price() }" />원
					</td>
					<td class="center">
						<c:set var="price" value="${dto.getCart_price() }" />
						<c:set var="amount" value="${dto.getCart_pqty() }" />
						<fmt:formatNumber value="${price * amount }" />원
					</td>
					<td class="center">
						<a href="<%=request.getContextPath() %>/user_cart_delete.do?num=${dto.getCart_num() }">[삭 제]</a>
					</td>
					
					<c:set var="total" value="${total + (price * amount) }" />
				</tr>			
			</c:forEach>
			
				<tr>
					<td colspan="4" align="right">
						<b><font color="red">장바구니 총액 : <fmt:formatNumber value="${total }" />원</font></b>
					</td>
					
					<td colspan="3" class="center">
						<a href="#">[구매하기]</a>&nbsp;&nbsp;&nbsp;
						<a href="javascript:history.go(-2);">[쇼핑가기]</a>
					</td>
				</tr>
		</c:if>
		
		<c:if test="${empty list }">
			<tr>
				<td colspan="7" class="center">
					<h3>장바구니가 비었습니다...</h3>
				</td>
			</tr>
		</c:if>
	</table>
	
	<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>






