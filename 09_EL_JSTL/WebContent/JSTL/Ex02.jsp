<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL fmt:formatNumber</title>
</head>
<body>

	<%--
		포맷팅 라이브러리 종류
		- <fmt:timeZone> : 지정한 국가의 시간을 지정하는 태그
						   태그를 열고 닫는 영역에서만 적용
		- <fmt:setTimeZone> : 지정한 국가의 시간을 지정하는 태그
		- <fmt:formatNumber> : 표시할 숫자의 형식을 지정하는 태그
		- <fmt:formatDate> : 지정한 형식의 날짜를 표시하는 태그						   
	 --%>
	 
	 <%--
	 	<fmt:formatNumber> 태그의 여러 가지 속성
	 	- value : 출력의 형식 지정
	 	- var : <fmt:formatNumber> 태그의 결과를 저장할 변수의 이름을 지정
	 	- type : 출력될 타입 지정
	 			* percent : %
	 			* number : 숫자
	 			* currency : 통화 형식 
	 	- currencyCode : 통화 코드 지정. 한국 원화는 KRW
	 	- currencySymbol : 통화를 표시할 때 사용할 기호를 표시
	 	- scope : 변수의 접근 범위 지정
	 	- pattern : 숫자가 출력될 양식 지정
	  --%>

	<div align="center">
		<hr width="30%" color="gray">
			<h1>커피 주문 페이지</h1>
		<hr width="30%" color="gray"> <br>
		
		<form method="post" action="Ex02_01.jsp">
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>커피 종류</th>
					<td>
						<select name="coffee">
							<option value="1">아메리카노-3,000원</option>
							<option value="2">카페라떼-3,500원</option>
							<option value="3">커피밀크쉐이크-4,000원</option>
							<option value="4">아인슈페너-4,500원</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<th>수 량</th>
					<td> <input type="number" min="1" max="20" name="amount"> </td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="계산">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	
</body>
</html>