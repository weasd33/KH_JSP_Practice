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
		<c:set var="dto" value="${content }" />
		<hr width="30%" color="tomato">
			<h1>${dto.getWriter() }님 글 상세 내용</h1>
		<hr width="30%" color="tomato"> <br>
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>작성자</th>
				<td>${dto.getWriter() }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.getTitle() }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="25" readonly>${dto.getContent() }</textarea>	
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<c:if test="${dto.getPwd().length() != 0 }">
						<c:forEach begin="1" end="4">
							*
						</c:forEach>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.getHit() }</td>
			</tr>
			<tr>
				<c:if test="${empty dto.getUpdate() }">
					<th>작성일</th>
					<td>${dto.getDate() }</td>
				</c:if>
				<c:if test="${!empty dto.getUpdate() }">
					<th>최근수정일</th>
					<td>${dto.getUpdate() }</td>
				</c:if>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정하기" onclick="location.href='update.do?no=${dto.getNo() }&page=${page }'">
					<input type="button" value="삭제하기" 
						onclick="if(confirm('정말로 삭제하시겠습니까?')) {
							location.href='delete.do?no=${dto.getNo() }&page=${page }'
						} else { return; }">
					<input type="button" value="전체목록" onclick="location.href='board_list.do?page=${page}'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>