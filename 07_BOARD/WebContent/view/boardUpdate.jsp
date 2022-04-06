<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardDTO dto = (BoardDTO)request.getAttribute("modify");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD TABLE</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="green">
			<h1>게시글 수정</h1>
		<hr width="30%" color="green"> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/update">
			
			<input type="hidden" name="no" value="<%=dto.getNo() %>">
			
			<table border="1" cellspacing="0" width=400>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" value="<%=dto.getWriter() %>" readonly></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="<%=dto.getTitle() %>"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="7" cols="20" name="content"><%=dto.getContent() %></textarea>
					</td>
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