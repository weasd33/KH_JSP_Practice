<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UPLOAD DELETE</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="gray">
			<h1>UPLOAD 글 삭제</h1>
		<hr width="30%" color="gray"> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/upload_delete_ok.do">
			<input type="hidden" name="no" value="${param.no }">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="삭제하기">&nbsp;&nbsp;
						<input type="button" value="취소" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>