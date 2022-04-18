<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UPLOAD ALL LIST</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="red">
			<h1>UPLOAD 전체 리스트</h1>	
		<hr width="30%" color="red"> <br>
		
		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>No.</th> <th>제목</th>
				<th>조회수</th> <th>작성일</th>
			</tr>
			
			<c:set var="list" value="${list }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getNo() }</td>
						<td>
							<a href="<%=request.getContextPath() %>/upload_content.do?no=${dto.getNo() }">${dto.getTitle() }</a>
						</td>
						<td>${dto.getHit() }</td>
						<td>${dto.getDate() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						게시물이 없습니다..
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="4" align="center">
					<input type="button" value="글쓰기" onclick="location.href='upload_write.do'">
				</td>
			</tr>
		</table>	
	</div>
</body>
</html>