<%@page import="com.member.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberDTO content = (MemberDTO)request.getAttribute("content");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="tomato">
			<h1>Member10 테이블 회원 상세 정보</h1> 
		<hr width="30%" color="tomato"> <br>
		
		<table border="1" cellspacing="0" width="400">
			<%
				if(content != null) {
			%>
				<tr>
					<th>회원No.</th>
					<td><%=content.getNum() %></td>
				</tr>
				<tr>
					<th>회원 아이디</th>
					<td><%=content.getMemId() %></td>
				</tr>
				<tr>
					<th>회원 이름</th>
					<td><%=content.getMemName() %></td>
				</tr>
				<tr>
					<th>회원 비밀번호</th>
					<td>
						<%
							if(content.getPwd().length() != 0) {
								for(int i = 1; i < content.getPwd().length(); i++) {
						%>
									*
						<%									
								}
							}
						%>
					</td>
				</tr>
				<tr>
					<th>회원 나이</th>
					<td><%=content.getAge() %></td>
				</tr>
				<tr>
					<th>회원 마일리지</th>
					<td><%=content.getMileage() %></td>
				</tr>
				<tr>
					<th>회원 직업</th>
					<td><%=content.getJob() %></td>
				</tr>
				<tr>
					<th>회원 주소</th>
					<td><%=content.getAddr() %></td>
				</tr>
				<tr>
					<th>회원 가입일</th>
					<td><%=content.getRegdate() %></td>
				</tr>
			
			<% } else { %>
				<tr>
					<td colspan="2" align="center">
						검색된 회원 정보가 없습니다..
					</td>
				</tr>
			<%} %>
		</table> 
	</div>
</body>
</html>