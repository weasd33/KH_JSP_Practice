<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="blue">
			<h1>DEPT 테이블 메인 페이지</h1>
		<hr width="30%" color="blue"> <br>
		<%-- request.getContextPath() : 현재 프로젝트명을 문자열로 반환 --%>
		<a href="<%=request.getContextPath() %>/select">[전체 부서 목록]</a>
	</div>
</body>
</html>