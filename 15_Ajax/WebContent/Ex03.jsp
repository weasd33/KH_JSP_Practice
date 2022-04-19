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
			url: "data/book.xml",
			dataType: "xml",
			success: function(data) {
				/*
					$(data)는 $.ajax() 메서드가 book.xml 파일에서
					불러온 데이터 객체를 말함
					find() 메서드로 하위 book 태그를 찾음
					작성된 book 태그만큼 each() 함수를 이용해서
					반복적으로 안의 내용을 수행
				*/
				$(data).find("book").each(function() {
					let title = $("title", this).text();
					let author = $("author", this).text();
					let price = $("price", this).text();
					
					let txt = "<li>책 제목 : " + title + "</li>" +
							  "<li>책 저자 : " + author + "</li>" +
							  "<li>책 가격 : " + price + "</li><hr>"; 
							  
					$("body").append(txt);
				});
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