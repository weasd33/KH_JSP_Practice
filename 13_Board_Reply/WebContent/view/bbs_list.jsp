<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="marmoon">
			<h1>JSP_BBS 전체 리스트</h1>
		<hr width="30%" color="marmoon"> <br>
		
		<table border="1" cellspacing="0" width="650">
			<tr>
				<th>No.</th> <th>제목</th> <th>조회수</th> 
				<th>작성일</th> <th>Group</th> <th>Step</th> <th>Indent</th> 
			</tr>
			
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getNo() }</td>
						<td>
							<a href="<%=request.getContextPath() %>/bbs_content.do?no=${dto.getNo() }">${dto.getTitle() }</a>	
						</td>
						<td>${dto.getHit() }</td>
						<td>${dto.getDate().substring(0, 10) }</td>
						<td>${dto.getGroup() }</td>
						<td>${dto.getStep() }</td>
						<td>${dto.getIndent() }</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="7" align="center">
						게시글이 없습니다...
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="7" align="right">
					<input type="button" value="글쓰기" onclick="location.href='bbs_write.do'">
				</td>
			</tr>
		</table>		
	</div>
</body>
</html>