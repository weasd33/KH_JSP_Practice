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
		$.ajax({
			type: "get",
			url: "data/data.json",
			dataType: "json",
			success: function(data) {
				// index : data 객체 내의 인덱스를 의미
				// item : data 객체 내의 이름과 값을 가지고 있는 객체
				$.each(data, function(index, item) {
					let txt = "<li>책 제목 : " + item.title + "</li>" +
							  "<li>책 저자 : " + item.author + "</li>" + 
							  "<li>책 가격 : " + item.price + "</li><hr>";
							  
					$("body").append(txt);
				}); 
			},
			error: function(data) {
				alert("Error..");
			}
		});
	});
	
	
</script>
</head>
<body>

</body>
</html>