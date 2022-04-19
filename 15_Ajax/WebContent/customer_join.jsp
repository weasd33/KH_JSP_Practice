<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CUSTOMER JOIN</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
	$(function() {
		// 중복확인 버튼 클릭 시
		$("#check_id").on("click", function() {
			$("#idcheck").hide(); // 해당 아이디의 영역 숨김
			
			let userId = $("#id").val();
			
			// 입력 길이 체크
			if($.trim($("#id").val()).length < 4) {
				
				let warningTxt = '<font color="red">아이디는 4자 이상이어야 합니다.</font>';
				$("#idcheck").text(''); // idcheck 영역(span 태그) 초기화
				$("#idcheck").show();	// span 태그 영역을 보임
				$("#idcheck").append(warningTxt);
				$("#id").val('').focus();
				return false;
			}
			
			// 입력 길이 체크
			if($.trim($("#id").val()).length > 16) {
				
				let warningTxt = '<font color="red">아이디는 16자 이하이어야 합니다.</font>';
				$("#idcheck").text(''); // idcheck 영역(span 태그) 초기화
				$("#idcheck").show();	// span 태그 영역을 보임
				$("#idcheck").append(warningTxt);
				$("#id").val('').focus();
				return false;
			}
			
			// 아이디 중복 여부 확인 - Ajax
			$.ajax({
				type: "post",
				url: "data/idCheck.jsp",
				data: {paramId : userId},
				datatype: "jsp",
				success: function(data) {
					if(data == 1) { // 아이디 중복
						let warningTxt = '<font color="red">중복된 아이디입니다.</font>';
						$("#idcheck").text(''); // idcheck 영역(span 태그) 초기화
						$("#idcheck").show();	// span 태그 영역을 보임
						$("#idcheck").append(warningTxt);
						$("#id").val('').focus();
					} else { // 중복이 아닌 경우
						let warningTxt = '<font color="red">사용 가능한 아이디입니다.</font>';
						$("#idcheck").text(''); // idcheck 영역(span 태그) 초기화
						$("#idcheck").show();	// span 태그 영역을 보임
						$("#idcheck").append(warningTxt);
					}
				},
				error: function() {
					alert("Error..");
				}
			});
			
		});
	});
	
</script>
</head>
<body>
	<div align="center">
		<hr width="30%" color="red">
			<h2>회원 가입</h2>
		<hr width="30%" color="red"> <br>
		
		<form method="post" action="http:www.naver.com">
			<table border="1" cellspacing="0" width="450">
				<tr>
					<th>아이디</th>
					<td>
						<input name="id" id="id" size="20">
						<input type="button" value="중복확인" id="check_id"> <br>
						<span id="idcheck"></span>
					</td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input name="name"></td>
				</tr>
			</table>
		</form>
	</div> 
</body>
</html>