<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">

	// 문서의 body 부분을 읽고 난 후 제이쿼리를 실행
	$(function() {
		$.ajax({
			type: "post",
			url: "data/data.html",
			dataType: "html",
			success: function(data) {
				document.body.innerHTML = data;
			},
			error: function(data) {
				alert("Error...");
			}
		});		
	});
	
</script>
</head>
<body>

</body>
</html>