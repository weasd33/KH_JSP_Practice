<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UPLOAD MODIFY</title>
</head>
<body>
	<div align="center">
		<c:set var="dto" value="${modify }" />
		<hr width="30%" color="blue">
			<h1>${dto.getWriter() }님 글 수정</h1>
		<hr width="30%" color="blue"> <br>
		
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/upload_update_ok.do">
			<input type="hidden" name="no" value="${dto.getNo() }">
			<table border="1" cellspacing="0" width="400">
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
						<textarea rows="10" cols="30" name="content">${dto.getContent() }</textarea>
					</td>
				</tr>
				<tr>
					<th>파일첨부</th>
					<td><input type="file" name="file" ></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
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