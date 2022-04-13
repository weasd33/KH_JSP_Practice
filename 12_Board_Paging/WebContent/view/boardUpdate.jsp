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
		<c:set var="dto" value="${modify }" />
		<hr width="30%" color="lightgray">
			<h1>${dto.getWriter() }님 게시물 수정</h1> 
		<hr width="30%" color="lightgray"> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
		
		
			<input type="hidden" name="no" value="${dto.getNo() }">
			<input type="hidden" name="db_pwd" value="${dto.getPwd() }">
			<input type="hidden" name="page" value="${page }">
			
			<table border="1" cellspacing="0" width="400">
				<c:if test="${!empty dto }">
					<tr>
						<th>작성자</th>
						<td><input name="writer" value="${dto.getWriter() }" readonly></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input name="title" value="${dto.getTitle() }"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea rows="10" cols="25" name="content">${dto.getContent() }</textarea>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pwd"></td>
					</tr>
				</c:if>
				
				<c:if test="${empty dto }">
					<tr>
						<td colspan="2" align="center">
							게시물이 없습니다..
						</td>
					</tr>
				</c:if>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정하기">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form> 
	</div>
</body>
</html>