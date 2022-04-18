<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UPLOAD WRITE</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="lightgray">
			<h1>UPLOAD 글쓰기</h1>
		<hr width="30%" color="lightgray"> <br>
		
		<%-- enctype : 이진 파일을 업로드하기 위한 속성 --%>
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/upload_write_ok.do">
			<table border="1" cellspacing="0" width="400">
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
						<textarea rows="10" cols="30" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<th>파일첨부</th>
					<td><input type="file" name="file"></td>
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