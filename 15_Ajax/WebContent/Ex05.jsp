<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">

	$(function() {
		$(".submit").on("click", function() {
			let data = $("form").serialize();
			$(".result").text(data);
			alert(data);
		});
	});
	
	
</script>
</head>
<body>
	<div align="center">
		<%-- 쿼리 스트링 : 사용자가 웹 프로그램으로 입력 데이터를 가장 단순하고
						쉽게 전달하는 방법 --%>
		<form action="">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<td colspan="2" align="center">
						<h3>폼 데이터를 쿼리 스트링으로 변환</h3>
					</td>
				</tr>
				
				<tr>
					<th>아이디</th>
					<td><input name="id"></td>
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
					<th>연락처</th>
					<td><input name="phone"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" class="submit" value="전송">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
		
		<div class="result"></div>
	</div>
</body>
</html>