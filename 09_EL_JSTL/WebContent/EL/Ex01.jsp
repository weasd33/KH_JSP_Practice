<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int su = 150;

	pageContext.setAttribute("SU", su);
%>    

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 표현 언어로 데이터 출력</title>
</head>
<body>
	<h1>EL 표현 언어로 데이터 출력</h1>
	<p>
		스크립트릿 >> <%=su %> <br>
		
		EL >> ${SU } <br>
		
		${"EL의 문자열" } <br>
		
		EL 수식 >> ${10 + 20 } <br> 
	</p>
</body>
</html>