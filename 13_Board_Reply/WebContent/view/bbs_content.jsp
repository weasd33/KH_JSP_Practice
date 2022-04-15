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
		<c:set var="dto" value="${content }" />
		<hr width="30%" color="marmoon">
			<h1>${dto.getWriter() }님 글 정보</h1>
		<hr width="30%" color="marmoon"> <br>
		
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
					<textarea rows="10" cols="20" readonly>${dto.getContent() }</textarea>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<c:if test="${!empty dto.getPwd() }">
						<c:forEach begin="1" end="${dto.getPwd().length() }">
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
					<th>수정일</th>
					<td>${dto.getUpdate() }</td>
				</c:if>
			</tr>
			
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						게시물이 없습니다..
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정하기" onclick="location.href='bbs_update.do?no=${dto.getNo() }'">
					<input type="button" value="삭제하기" onclick="if(confirm('삭제하시겠습니까?')) {
						location.href='bbs_delete.do?no=${dto.getNo() }'
					} else { return; }">
					<input type="button" value="댓글달기" onclick="location.href='bbs_reply.do?no=${dto.getNo() }'">
					<input type="button" value="전체목록" onclick="location.href='bbs_list.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>