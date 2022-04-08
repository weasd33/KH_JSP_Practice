<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 태그</title>
</head>
<body>
	
	<%--
		기본적인 JSTL 태그 ==> 출력을 할 때는 EL 언어를 사용
		
		1. 변수 태그(set)
			<c:set var="변수명" value="값"> </c:set>
			예) <c:set var="num" value="10" /> ==> int num = 10;
				<c:set var="str" value="Hello" /> ==> String str = "Hello";
				
		2. 출력 태그(out)
			<c:out value="변수명"> </c:out>
			예) <c:out value="str" /> ==> Hello
			
		3. 삭제 태그(remove)
			<c:remove var="변수명"> </c:remove>
			예) <c:remove var="변수명" />
		
		4. 조건 처리 태그(if문) ==> else문이 없음
			<c:if test="조건식" var="변수명"> </c:if>		
			
		5. 조건 처리 태그(choose문) ==> switch~case문과 유사
			<c:choose>
				<c:when test="조건식1"> 조건식1이 참인 경우 실행 문장 </c:when>
				<c:when test="조건식2"> 조건식2이 참인 경우 실행 문장 </c:when>
				<c:when test="조건식3"> 조건식3이 참인 경우 실행 문장 </c:when>
				<c:when test="조건식4"> 조건식4이 참인 경우 실행 문장 </c:when>
				<c:otherwise>상기 조건식 이와의 경우 실행 문장</c:otherwise> default 문장과 유사
			</c:choose>
			
		6. 반복문(forEach문)
			<c:forEach begin="시작값" end="끝값" step="증감값" var="변수명">
				반복 문장
			</c:forEach>
			
			<c:forEach items="객체명" var="변수명">
	 --%>

	<h1>JSTL의 기본적인 태그들</h1>
	
	<h2>변수 태그(set)</h2>
	<c:set var="str" value="Hello JSTL!!" />
	
	<hr>
	
	<h2>출력 태그(out)</h2>
	문자열 출력 >> <c:out value="이건 그냥 문자열입니다." /> <br>
	변수값 출력 >> <c:out value="${str }" />
	
	<hr>
	
	<h2>삭제 태그(remove)</h2>
	값 삭제 >> <c:remove var="str" /> <br>
	삭제 후 변수값 출력 >> <c:out value="${str }" />
	
	<hr>
	
	<h2>조건 처리 태그(if문)</h2>
	<c:if test="${10 > 5 }" var="k"/>
	
	조건식 결과 >> <c:out value="${k }" /> <br>
	
	<hr>
	
	<h2>조건 처리 태그(choose문)</h2>
	<c:set var="grade" value="89" />
	
	<c:choose>
		<c:when test="${grade >= 90 }">결과 : A학점입니다.</c:when>
		<c:when test="${grade >= 80 }">결과 : B학점입니다.</c:when>
		<c:when test="${grade >= 70 }">결과 : C학점입니다.</c:when>
		<c:when test="${grade >= 60 }">결과 : D학점입니다.</c:when>
		<c:otherwise>결과 : F학점입니다.</c:otherwise>
	</c:choose>
	
	<hr>
	
	<h2>반복 처리(forEach문)</h2>
	<c:forEach begin="1" end="10" var="i">
		${i }&nbsp;&nbsp;&nbsp;
	</c:forEach>
	
	<hr>
	
	<%
		String[] str = {"홍길동", "이순신", "유관순", "김유신", "김연아"};
	
		pageContext.setAttribute("list", str);
	%>
	
	결과 >> <c:forEach items="${list }" var="k">
			${k }&nbsp;&nbsp;&nbsp;
		   </c:forEach>
</body>
</html>

















