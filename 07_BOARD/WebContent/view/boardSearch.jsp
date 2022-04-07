<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<BoardDTO> search = (List<BoardDTO>)request.getAttribute("search");
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
			<h1>검색된 글 목록</h1>
		<hr width="30%" color="red"> <br>
		
		<table border="1" cellspacing="0" width="450">
			<tr>
				<th>번호</th> <th>제목</th> <th>작성자</th>
				<th>조회수</th> <th>작성일</th> 
			</tr>
			
			<%
				for(int i = 0; i < search.size(); i++) {
					BoardDTO dto = search.get(i);
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
					<input type="button" value="전체목록" onclick="location.href='select'">
				</td>
			</tr>			
		</table>		
	</div>
</body>
</html>