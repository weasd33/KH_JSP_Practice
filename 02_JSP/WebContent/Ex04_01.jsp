<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
	// Ex04.jsp 페이지에서 넘어온 데이터들을 받아 주어야 한다.
	String userId = request.getParameter("id");
	String userPwd = request.getParameter("pwd");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="blue">
			<h2>로그인 결과</h2>
		<hr width="50%" color="blue">
		
		<br>
		
		<table border="1" cellspacing="0">
			<tr>
				<th>아이디</th>
				<td><%=userId %></td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td><%=userPwd %></td>
			</tr>
		</table>
	</div>

</body>
</html>