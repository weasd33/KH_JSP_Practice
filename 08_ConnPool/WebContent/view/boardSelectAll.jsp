<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<BoardDTO> list = (List<BoardDTO>)request.getAttribute("list");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD TABLE</title>
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
			
			<%
				for(int i = 0; i < list.size(); i++) {
					BoardDTO dto = list.get(i);
			%>
				<tr>
					<td><%=dto.getNo() %></td>
					<td>
					<a href="<%=request.getContextPath() %>/content?no=<%=dto.getNo() %>"><%=dto.getTitle() %></a>
					</td>
					<td><%=dto.getWriter() %></td>
					<td><%=dto.getHit() %></td>
					<td><%=dto.getDate().substring(0, 10) %></td>
				</tr>			
			<%
				}
			%>
			<tr>
				<td colspan="5" align="center">
					<input type="button" value="글쓰기" onclick="location.href='view/boardWrite.jsp'">
				</td>
			</tr>			
		</table>
		
		<br> <hr width="30%" color="gray"> <br>
		<form method="post" action="<%=request.getContextPath() %>/search">
			<select name="find_field">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
			</select>	
			
			<input type="text" name="find_name">
			<input type="submit" value="검색">
		</form>		
	</div>
</body>
</html>














