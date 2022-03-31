<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String addr = request.getParameter("addr");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="30%" color="gray">
			<h2>회원 가입 결과</h2>
		<hr width="30%" color="gray"> <br>
	 
		<table border="1" cellspacing="0">
			<tr>
				<th>아이디</th>
				<td><%=id %></td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td><%=pwd %></td>
			</tr>
			
			<tr>
				<th>이 름</th>
				<td><%=name %></td>
			</tr>
			
			<tr>
				<th>성별</th>
				<td><%=gender %></td>
			</tr>
			
			<tr>
				<th>거주지</th>
				<td><%=addr %></td>
			</tr>
			
			<tr>
				<th>연락처</th>
				<td><%=phone %></td>
			</tr>
			
			<tr>
				<th>이메일</th>
				<td><%=email %></td>
			</tr>
		</table>
	</div>
</body>
</html>