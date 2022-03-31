<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 주소창은 Ex07로 나타나지 않는 특징 --%>
	<div align="center">
		<hr width="30%" color="orange">
			<h1>메인 페이지</h1>
		<hr width="30%" color="orange"> <br>
		
		<h2><%=request.getParameter("id") %>님 환영합니다.</h2>
		<h3>이름 : <%=request.getAttribute("Name") %> <br>
			주소 : <%=request.getAttribute("Addr") %>
		</h3>			
	</div> 

</body>
</html>