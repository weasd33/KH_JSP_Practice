<%@page import="com.member.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberDTO content = (MemberDTO)request.getAttribute("modify");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="skyblue">
			<h1>MEMBER10 테이블 회원 정보 수정</h1>
		<hr width="30%" color="skyblue"> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/update">
		
			<input type="hidden" name="num" value="<%=content.getNum() %>">
			
			<table border="1" cellspacing="0" width="290">
				<tr>
					<th>회원 아이디</th>
					<td> <input type="text" name="mem_id" value="<%=content.getMemId() %>" readonly> </td>
				</tr>
				<tr>
					<th>회원 이름</th>
					<td> <input type="text" name="mem_name" value="<%=content.getMemName() %>" readonly> </td>
				</tr>
				<tr>
					<th>회원 비밀번호</th>
					<td> <input type="password" name="mem_pwd"> </td>
				</tr>
				<tr>
					<th>회원 나이</th>
					<td> <input type="text" name="mem_age" value="<%=content.getAge() %>"> </td>
				</tr>
				<tr>
					<th>회원 마일리지</th>
					<td> <input type="text" name="mem_mileage" value="<%=content.getMileage() %>"> </td>
				</tr>
				<tr>
					<th>회원 직업</th>
					<td> <input type="text" name="mem_job" value="<%=content.getJob() %>"> </td>
				</tr>
				<tr>
					<th>회원 주소</th>
					<td> <input type="text" name="mem_addr" value="<%=content.getAddr() %>"> </td>
				</tr>	
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원수정">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>