<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
	 성적 처리 폼 페이지 만들어보기
		이름, 국어점수, 영어점수, 수학점수	
	 --%>
	 
	 <div align="center">
	 	<h1>성적 처리 폼</h1>
	 	<form action="exam" method="POST">
		 	<table border="1" cellspacing="0">
		 		<tr>
		 			<th>이름</th>
		 			<td><input type="text" name=name></td>
		 		</tr>
		 		<tr>
		 			<th>국어점수</th>
		 			<td><input type="text" name=kor></td>
		 		</tr>
		 		<tr>
		 			<th>영어점수</th>
		 			<td><input type="text" name=eng></td>
		 		</tr>
		 		<tr>
		 			<th>수학점수</th>
		 			<td><input type="text" name=math></td>
		 		</tr>
		 		
		 		<tr>
		 			<td colspan="2" align="center">
		 				<input type="submit" value="전송">
		 				<input type="reset" value="취소">
		 			</td>
		 		</tr>		 		
		 	</table>
	 	</form>
	 </div>

</body>
</html>