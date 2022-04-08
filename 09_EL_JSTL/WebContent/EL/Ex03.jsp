<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Scope</title>
</head>
<body>

	<%
		pageContext.setAttribute("K", 100);
		
		request.setAttribute("R", 1000);
		
		session.setAttribute("S", 10000);
		
		application.setAttribute("A", 100000);
		
		RequestDispatcher rd = request.getRequestDispatcher("Ex03_01.jsp");
		
		rd.forward(request, response);
	%>

</body>
</html>