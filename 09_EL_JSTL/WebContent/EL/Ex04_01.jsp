<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");

	/* String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	int userAge = Integer.parseInt(request.getParameter("age")); */
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 값 받는 방식</title>
</head>
<body>
	<div align="center">
		<h1>JSP 표현식을 사용한 방식</h1>
		<table border="1" cellspacing="0" width="300">
			<tr>
				<th>아이디</th>
				<td><%=request.getParameter("id") %></td>
			</tr>
			<tr>
				<th>이 름</th>
				<td><%=request.getParameter("name") %></td>
			</tr>
			<tr>
				<th>나 이</th>
				<td><%=Integer.parseInt(request.getParameter("age")) %></td>
			</tr>
		</table>
	</div> <br>
	
	<hr width="30%" align="center" color="gray">
	
	<div align="center">
		<h1>JSP 표현언어(EL)를 사용한 방식</h1>
		<table border="1" cellspacing="0" width="300">
			<tr>
				<th>아이디</th>
				<td>${param.id }</td>
			</tr>
			<tr>
				<th>이 름</th>
				<td>${param.name }</td>
			</tr>
			<tr>
				<th>나 이</th>
				<td>${param.age }</td>
			</tr>
		</table>
	</div>
</body>
</html>