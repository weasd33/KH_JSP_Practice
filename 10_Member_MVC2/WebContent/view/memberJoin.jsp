<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER10</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="magenta">
			<h1>MEMBER10 회원 등록</h1>
		<hr width="30%" color="magenta"> <br>
	
		<form method="post" action="<%=request.getContextPath() %>/insert_ok.do">
			<table border="1" cellspacing="0" width="255">
				<tr>
					<th>아이디</th>
					<td><input name="id"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input name="name"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input name="age"></td>
				</tr>
				<tr>
					<th>마일리지</th>
					<td><input name="mileage"></td>
				</tr>
				<tr>
					<th>직업</th>
					<td><input name="job"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input name="addr"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="회원등록">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>