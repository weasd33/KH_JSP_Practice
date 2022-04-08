<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Scope</title>
</head>
<body>
	<h1>표현식으로 scope 내용 출력 - 2</h1>
	<div>
		pageContext >> <%=pageContext.getAttribute("K") %> <br>
		request >> <%=request.getAttribute("R") %> <br>
		session >> <%=session.getAttribute("S") %> <br>
		application >> <%=application.getAttribute("A") %> <br><br>
	</div>
	
	<hr width="100%" color="gray">
	
	<h1>표현언어(EL)로 scope 내용 출력 - 2</h1>
	<div>
		pageContext : \${pageScope.K } >> ${pageScope.K } or \${K } >> ${K } <br>
		requestContext : \${requestScope.R } >> ${requestScope.R } or \${R } >> ${R } <br>
		session : \${sessionScope.S } >> ${sessionScope.S } or \${S } >> ${S } <br>
		application : \${applicationScope.A } >> ${applicationScope.A } or \${A } >> \${A } <br>
	</div>
	
	<script type="text/javascript">
		location.href="Ex03_02.jsp";
	</script>
</body>
</html>