<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="blue">
			<h1>JSP_BBS 글 삭제</h1>
		<hr width="30%" color="blue"> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/bbs_delete_ok.do">
			<input type="hidden" name="no" value="${param.no }">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="삭제하기">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>