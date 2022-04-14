<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="red">
			<h1>게시물 검색 리스트</h1>
		<hr width="30%" color="red"> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/search.do">
			<select name="search_field">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
				<option value="writer">작성자</option>
			</select>
			
			<input name="search_keyword">
			<input type="submit" value="검색">		
		</form> 
		
		<br>
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>No.</th> <th>제목</th>
				<th>조회수</th> <th>작성일</th>				
			</tr>
			
			<c:set var="list" value="${pageList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getNo() }</td>
						<td>
							<a href="<%=request.getContextPath() %>
								/board_content.do?no=${dto.getNo() }&page=${page }">${dto.getTitle() }</a>
						</td>
						<td>${dto.getHit() }</td>
						<td>${dto.getDate().substring(0, 10) }</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list }" >
				<tr>
					<td colspan="4" align="center">
						게시물이 존재하지 않습니다..
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="전체목록" onclick="location.href='board_list.do?page=1'">
					<input type="button" value="글쓰기" onclick="location.href='board_write.do'">
				</td>
			</tr>
		</table>
		
		<br>
		
		<%-- 검색 페이징 처리 --%>
		<c:if test="${page > block}">
			<a href="board_search.do?page=1&search_field=${searchField }&search_keyword=${search_keyword }">처음</a>
			<a href="board_search.do?page=${startBlock -1 }&search_field=${searchField }&search_keyword=${search_keyword }">이전</a>
		</c:if>
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="board_search.do?page=${i }&search_field=${searchField }&search_keyword=${search_keyword }">[${i }]</a></b>
			</c:if>
					
			<c:if test="${i != page }">
				<a href="board_search.do?page=${i }&search_field=${searchField }&search_keyword=${search_keyword }">[${i }]</a>
			</c:if>				
		</c:forEach>
		
		<c:if test="${endBlock < allPage }">
			<a href="board_search.do?page=${endBlock + 1 }&search_field=${searchField }&search_keyword=${search_keyword }">다음</a>
			<a href="board_search.do?page=${allPage }&search_field=${searchField }&search_keyword=${search_keyword }">끝</a>
		</c:if>		
	</div>
</body>
</html>