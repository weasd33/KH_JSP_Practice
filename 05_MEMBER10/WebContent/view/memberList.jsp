<%@page import="com.member.model.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<MemberDTO> member = (List<MemberDTO>)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="red">
			<h1>MEMBER10 테이블 전체 목록</h1>
		<hr width="30%" color="red"> <br>
		
		<table border="1" cellspacing="0" width="350">
			<tr>
				<th>No.</th> <th>아이디</th> <th>이름</th> <th>가입일</th>				  
			</tr>
			
			<%
				if(member.size() != 0) {
					for(int i = 0; i < member.size(); i++) {
						MemberDTO dto = member.get(i);
			%>
				<tr>
					<td><%=dto.getNum() %></td>
					<td><%=dto.getMemId() %></td>
					<td>
						<a href="<%=request.getContextPath() %>/content?num=<%=dto.getNum() %>">
								<%=dto.getMemName() %> </a>
					</td>
					<td><%=dto.getRegdate().substring(0, 10) %></td>
				</tr>
			<%
					}
				} else {
			%>
				<tr>
					<td colspan="4" align="center">
						<p>검색된 회원이 없습니다...</p>
					</td>
				</tr>
			<%} %>
				
				<tr>
					<td colspan="4" align="right">
						<input type="button" value="회원등록" onclick="location.href='view/memberJoin.jsp'">
					</td>
				</tr>
		</table>
	</div>
	
</body>
</html>