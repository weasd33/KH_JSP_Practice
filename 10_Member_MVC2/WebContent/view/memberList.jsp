<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER10</title>
</head>
<body>

	<div align="center">
		<hr width="30%" color="red">
			<h1>MEMBER10 테이블 전체 목록</h1>
		<hr width="30%" color="red"> <br>
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>No.</th> <th>아이디</th> <th>이름</th> <th>가입일</th>
			</tr>
			
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getNum() }</td>
						<td>${dto.getMemId() }</td>
						<td>
						<a href="<%=request.getContextPath()%>/content.do?num=${dto.getNum() }">${dto.getMemName() }</a>	
						</td>
						<td>${dto.getRegdate().substring(0, 10) }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						검색된 회원 리스트가 없습니다...
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="4" align="center">
					<input type="button" value="회원등록" onclick="location.href='insert.do'">
				</td>
			</tr>
		</table>
	</div>

</body>
</html>