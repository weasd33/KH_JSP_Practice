<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL fmt:formatDate</title>
</head>
<body>

	<%--
		<fmt:formatDate> 태그의 여러 가지 속성
		- value : 포맷 될 날짜를 지정하는 속성
		- type : 포맷팅 할 타입을 지정하는 속성
				* date : 날짜 지정
				* time : 시간 지정
				* both : 날짜 / 시간 지정
		- dateStyle : 날짜의 출력 양식을 지정하는 속성
					  값(value)에는 full, long, medium, short 등을 지정할 수 있음.
		- timeStyle : 시간 출력 형식을 지정하는 속성
		- pattern : 직접 출력 형식을 지정하는 속성
		- timeZone : 특정 나라 시간대로 시간을 설정하는 속성
	 --%>
	 
	<h1>JSTL fmt:formatDate</h1>
	
	<c:set var="now" value="<%=new Date() %>" />
	
	<h2>type="date"</h2>
	dateStyle="full" : <fmt:formatDate value="${now }" type="date" dateStyle="full" /> <br>
	dateStyle="long" : <fmt:formatDate value="${now }" type="date" dateStyle="long" /> <br>
	dateStyle="medium": <fmt:formatDate value="${now }" type="date" dateStyle="medium" /> <br>
	dateStyle="short": <fmt:formatDate value="${now }" type="date" dateStyle="short" /> <br>
	
	<hr>
	
	<h2>type="time"</h2>
	timeStyle="full" : <fmt:formatDate value="${now }" type="time" timeStyle="full" /> <br>
	timeStyle="long" : <fmt:formatDate value="${now }" type="time" timeStyle="long" /> <br>
	timeStyle="medium" : <fmt:formatDate value="${now }" type="time" timeStyle="medium" /> <br> 
	timeStyle="short" : <fmt:formatDate value="${now }" type="time" timeStyle="short" /> <br>
	
	<hr>
	
	<h2>type="both"</h2>
	dateStyle="full" timeStyle="full" : <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full" /> <br>
	dateStyle="long" timeStyle="long" : <fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="long" /> <br>
	dateStyle="medium" timeStyle="medium" : <fmt:formatDate value="${now }" type="both" dateStyle="medium" timeStyle="medium" /> <br>
	dateStyle="short" timeStyle="short" : <fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="short" /> <br>
	
	<hr>
	
	<h2>pattern</h2>
	pattern="YYYY-MM-dd hh:mm:ss" : <fmt:formatDate value="${now }" pattern="YYYY-MM-dd hh:mm:ss" /> <br>
	
	<hr>
	
	<h2>timeZone</h2>
	한국 현재 시간 : <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full" /> <br>
	
	<fmt:timeZone value="America/LA">
	미국 현재 시간 : <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full" /> <br>
	</fmt:timeZone>
	

</body>
</html>











