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
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		
		// Ex01_01.jsp에서 넘어온 세션 정보 받기
		// session의 반환 타입은 Object
		String sessionId = (String)session.getAttribute("ID");
		String sessionPwd = (String)session.getAttribute("PWD");
		String sessionName = (String)session.getAttribute("NAME");
	%>
	
	<h1>입력 폼(Ex01.jsp)에서 넘어온 데이터</h1>
	
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
	
	<script type="text/javascript">
		location.href="Ex01_03.jsp";
	</script>

</body>
</html>