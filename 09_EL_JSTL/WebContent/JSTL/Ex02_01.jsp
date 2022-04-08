<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 언어 사용 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 포맷팅 라이브러리 사용 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL fmt:formatNumber</title>
</head>
<body>

	<%-- Ex02.jsp에서 받은 coffee의 value 값을 coffee 변수에 저장 --%>
	<c:set var="coffee" value="${param.coffee }" />

	<c:choose>
		<%-- 저장된 변수를 가지고 조건식 처리 --%>
		<c:when test="${coffee == 1 }">
			<c:set var="coffee_name" value="아메리카노" />
			<c:set var="price" value="3000" />
		</c:when>
		
		<c:when test="${coffee == 2 }">
			<c:set var="coffee_name" value="카페라떼" />
			<c:set var="price" value="3500" />
		</c:when>
		
		<c:when test="${coffee == 3 }">
			<c:set var="coffee_name" value="커피밀크쉐이크" />
			<c:set var="price" value="4000" />
		</c:when>
		
		<c:when test="${coffee == 4 }">
			<c:set var="coffee_name" value="아인슈페너" />
			<c:set var="price" value="4500" />
		</c:when>
	</c:choose>
	
	<table border="1" cellspacing="0" width="300" align="center">
		<tr>
			<th>종류</th>
			<td> ${coffee_name } </td>
		</tr>
		
		<tr>
			<th>단가</th>
			<td> <fmt:formatNumber value="${price }" />원 </td>
		</tr>
		
		<tr>
			<th>수량</th>
			<c:set var="amount" value="${param.amount }" />
			<td>${amount }</td>
		</tr>
		
		<tr>
			<th>공급가액</th>
			<td> <fmt:formatNumber value="${amount * price }" />원 </td>
		</tr>
		
		<tr>
			<th>부가세액</th>
			<td> <fmt:formatNumber value="${(amount * price) * 0.1 }" />원 </td>
		</tr>
		
		<tr>
			<th>총 금 액</th>
			<td> <fmt:formatNumber value="${(amount * price) + ((amount * price) * 0.1) }" />원 </td>
		</tr>
	</table>

</body>
</html>