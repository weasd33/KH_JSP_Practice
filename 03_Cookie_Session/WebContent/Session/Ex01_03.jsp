<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String userId = request.getParameter("id"); // null 발생
		String userPwd = request.getParameter("pwd"); // null 발생
		
		String sessionId = (String)session.getAttribute("ID"); // SHIM
		String sessionPwd = (String)session.getAttribute("PWD"); // 1234
		String sessionName = (String)session.getAttribute("NAME"); // 심규복
		
		// getMaxInactiveInterval() : 세션의 유효 시간을 확인
		// web.xml 629번 줄에서 시간 변경 가능
		int time = session.getMaxInactiveInterval();
	%>
	
	<h1>입력 폼(Ex01_02.jsp)에서 넘어온 데이터</h1>

	<h2>
		입력 받은 아이디 : <%=userId %> <br>
		입력 받은 비밀번호 : <%=userPwd %> <br>
	</h2>
	
	<hr>
	
	<h2>
		세션으로 받은 아이디 : <%=sessionId %> <br>
		세션으로 받은 비밀번호 : <%=sessionPwd %> <br>
		세션으로 받은 이름 : <%=sessionName %> <br>
	</h2>
	
	<hr>
	
	<h2>session 유효 시간 결과</h2>
	<h3>session 유효 시간 >> <%=time %>초</h3>
</body>
</html>