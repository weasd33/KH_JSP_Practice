<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardDTO content = (BoardDTO)request.getAttribute("content");    
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD TABLE</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="tomato">
			<h1>게시글 상세 정보</h1>
		<hr width="30%" color="tomato"> <br>
		
		<table border="1" cellspacing="0" width="450">
			<%
				if(content != null) {
			%>
				<tr>
					<th colspan="2">
						<h2><%=content.getWriter() %>님 글 정보</h2>
					</th>
				</tr>			
				<tr>
					<th>작성자</th>
					<td><%=content.getWriter() %></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><%=content.getTitle() %></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="7" cols="20" readonly><%=content.getContent() %></textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<%
							for(int i = 1; i <= content.getPwd().length(); i++) {
						%>
								*
						<%} %>
					</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td><%=content.getHit() %></td>
				</tr>
				<tr>
					<%
						if(content.getUpdate() == null) {
					%>
						<th>작성일</th>
						<td><%=content.getDate() %></td>			
					<%		
						} else {
					%>
						<th>수정일</th>
						<td><%=content.getUpdate() %></td>
					<%											
						}
					%>
				</tr>
			<% } else { // 게시글이 없는 경우 %>
				<tr>
					<td colspan="2" align="center">
						게시글 상세 내역이 없습니다..
					</td>
				</tr>
			<%} %> 
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정하기" onclick="location.href='update?no=<%=content.getNo() %>'">
					<input type="button" value="삭제하기" 
						onclick="if(confirm('정말로 삭제하시겠습니까?')) {location.href='view/boardDelete.jsp?no=<%=content.getNo() %>'} else { return; }">
					<input type="button" value="전체목록" onclick="location.href='select'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>