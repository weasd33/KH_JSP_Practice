<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 목록</title>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />

	<div align="center">
		<hr width="80%" color="marmoon">
			<h1>카테고리 목록</h1>
		<hr width="80%" color="marmoon"> <br>
		
		<table border="1" cellspacing="0" width="500">
			<tr bgcolor="#eeffee">
				<th>카테고리 No.</th> <th>카테고리 코드</th>
				<th>카테고리 이름</th> <th>카테고리 삭제</th>
			</tr>
			
			<c:set var="list" value="${categoryList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr align="center">
						<td>${dto.getCategory_num() }</td>
						<td>${dto.getCategory_code() }</td>
						<td>${dto.getCategory_name() }</td>
						<td><input type="button" value="삭제" onclick="check(${dto.getCategory_num() })"></td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>등록된 카테고리가 없습니다...</h3>
					</td>
				</tr>
			</c:if>
		</table>
	</div>

	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>