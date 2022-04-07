<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int no = Integer.parseInt(request.getParameter("no"));
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOARD TABLE</title>
</head>
<body>

	<div align="center">
		<hr width="30%" color="gray">
			<h1>게시글 삭제 페이지</h1>
		<hr width="30%" color="gray"> <br>
		
		<form method="post" action="<%=request.getContextPath() %>/delete">
		
			<input type="hidden" name="no" value="<%=no %>">
			
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>삭제할 비밀번호</th>
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