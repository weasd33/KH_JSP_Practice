<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<BoardDTO> list = (List<BoardDTO>)request.getAttribute("selectAll");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD TABLE</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="lightgray">
			<h1>전체 글 목록</h1>
		<hr width="30%" color="lightgray"> <br>
		
		<table border="1" cellspacing="0">
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
					<td><%=dto.getTitle() %></td>
					<td><%=dto.getWriter() %></td>
					<td><%=dto.getHit() %></td>
					<td><%=dto.getDate().substring(0, 10) %></td>
				</tr>			
			<%
				}
			%>
			
		</table>
	</div>
</body>
</html>