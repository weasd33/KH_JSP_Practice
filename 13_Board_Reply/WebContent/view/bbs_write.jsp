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
			<h1>JSP_BBS 글쓰기</h1>
		<hr width="30%" color="blue"> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/bbs_write_ok.do">
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>작성자</th>
					<td><input name="writer"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input name="title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="10" cols="25" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="작성하기">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>