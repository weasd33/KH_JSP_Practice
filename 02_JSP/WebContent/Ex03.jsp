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
		// 구구단 출력해보기
		for(int i = 2; i < 10; i++) {
			System.out.println("*** " + i + "단 ***");
			for(int j = 1; j < 10; j++) {
				System.out.println(i + " * " + j + " = " + (i * j));
			}
			System.out.println();
		}
	%>
	
	<%-- 구구단 웹 브라우저에 출력 --%>
	
	<%
		for(int i = 2; i < 10; i++) {
	%>
		*** <%=i %>단 *** <br>
	<%
			for(int j = 1; j < 10; j++) {
	%>
		<%=i %> * <%=j %> = <%=i * j %> <br>
		
		<%} %> <br>
	<%} %>		
		
</body>
</html>