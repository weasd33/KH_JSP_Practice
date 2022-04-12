<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD</title>
</head>
<body>
<div align="center">
		<hr width="30%" color="red">
			<h1>전체 글 목록</h1>
		<hr width="30%" color="red"> <br>
		
		<table border="1" cellspacing="0" width="450">
			<tr>
				<th>번호</th> <th>제목</th> <th>작성자</th>
				<th>조회수</th> <th>작성일</th> 
			</tr>
			
			<c:set var="list" value="${list }" />
			
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getNo() }</td>
						<td>
						<a href="<%=request.getContextPath() %>/content.do?no=${dto.getNo() }">${dto.getTitle() }</a>
						</td>
						<td>${dto.getWriter() }</td>
						<td>${dto.getHit() }</td>
						<td>${dto.getDate().substring(0,10) }</td>
					</tr>	
				</c:forEach>		
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="5">
						검색된 글이 없습니다...
					</td>
				</tr>
			</c:if>

			<tr>
				<td colspan="5" align="center">
					<input type="button" value="글쓰기" onclick="location.href='write.do'">
				</td>
			</tr>	
		</table> <br>
			
		<form method="post" action="<%=request.getContextPath() %>/search.do">
			<select name="search_field">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
				<option value="writer">작성자</option>
			</select>	
			
			<input type="text" name="search_keyword">
			<input type="submit" value="검색">
		</form>				
	</div>
</body>
</html>