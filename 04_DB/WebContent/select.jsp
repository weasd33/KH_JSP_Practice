<%@page import="com.khie.model.DeptDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<DeptDTO> dept = (List<DeptDTO>)request.getAttribute("List");	
%>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="red">
			<h1>DEPT 테이블 전체 리스트</h1>
		<hr width="30%" color="red"> <br>
		
		<table border="1" cellspacing="0" width="350">
			<tr>
				<th>부서No.</th> <th>부서위치</th> <th>부서명</th>
			</tr>
			<%
				if(dept.size() != 0) {
					for(int i = 0; i < dept.size(); i++) {
						DeptDTO dto = dept.get(i);
			%>
			<tr>
				<td><%=dto.getDeptno() %></td>
				<td><%=dto.getDname() %></td>
				<td><%=dto.getLoc() %></td>
			</tr>
			<%
					}
				} else {
			%>
			<tr>
				<td colspan="3" align="center">
					<h2>검색된 레코드가 없습니다...</h2>
				</td>
			</tr>			
			<%} %>
			
			<tr>
				<td colspan="3" align="center">
					<input type="button" value="부서추가" onclick="location.href='insert.jsp'">
				</td>
			</tr>
		</table>
	</div>
	
</body>
</html>