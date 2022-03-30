<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<h2>회원 가입 화면</h2>
		
		<form method="POST" action="join">
			<table border="1" cellspacing="0">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id"></td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input type="text" name="name"></td>
				</tr>
				
				<tr>
					<th>연락처</th>
					<td><input type="text" name="phone"></td>
				</tr>
				
				<tr>
					<th>주소</th>
					<td><input type="text" name="addr"></td>
				</tr>
				
				<tr>
					<th>취미</th>
					<td>
						<input type="checkbox" name="hobby" value="여행">여행
						<input type="checkbox" name="hobby" value="독서">독서
						<input type="checkbox" name="hobby" value="운동">운동
						<input type="checkbox" name="hobby" value="게임">게임
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="가입">
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>