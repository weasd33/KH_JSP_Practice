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
		
		<table border="1" cellspacing="0" width="650">
			<tr>
				<th>No.</th> <th>아이디</th> <th>이름</th>
				<th>비밀번호</th> <th>나이</th> <th>마일리지</th>
				<th>직업</th> <th>주소</th> <th>날짜</th> 
			</tr>
			
			<%
				if(member.size() != 0) {
					for(int i = 0; i < member.size(); i++) {
						MemberDTO dto = member.get(i);
			%>
				<tr>
					<td><%=dto.getNum() %></td>
					<td><%=dto.getMemId() %></td>
					<td><%=dto.getMemName() %></td>
					<td><%=dto.getPwd() %></td>
					<td><%=dto.getAge() %></td>
					<td><%=dto.getMileage() %></td>
					<td><%=dto.getJob() %></td>
					<td><%=dto.getAddr() %></td>
					<td><%=dto.getRegdate() %></td>
				</tr>
			<%
					}
				} else {
			%>
				<tr>
					<td colspan="9" align="center">
						<p>검색된 회원이 없습니다...</p>
					</td>
				</tr>
			<%} %>
		</table>
	</div>
	
</body>
</html>