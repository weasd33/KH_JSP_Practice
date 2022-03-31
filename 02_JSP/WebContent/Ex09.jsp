<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- web.xml로 Error 페이지 설정하기 --%>
<%
	// 500 에러
	int num = Integer.parseInt(request.getParameter("num")); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테스트 페이지</title>
</head>
<body>
	
	<h1>JSP 페이지입니다.</h1>
	
</body>
</html>