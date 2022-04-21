<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="blue">
			<h1>User Login</h1>
		<hr width="30%" color="blue"> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/user_login_ok.do">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>회원 아이디</th>
					<td><input name="user_id"></td>
				</tr>
				<tr>
					<th>회원 비밀번호</th>
					<td><input type="password" name="user_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인">&nbsp;&nbsp;&nbsp;
						<input type="button" value="취소" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>