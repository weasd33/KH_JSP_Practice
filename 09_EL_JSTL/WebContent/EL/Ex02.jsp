<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 연산자</title>
</head>
<body>
	<h1>EL 산술 연산자</h1>
	\${15 + 10 } >> ${15 + 10 } <br>
	\${25 - 14 } >> ${25 - 14 } <br>
	\${52 * 17 } >> ${52 * 17 } <br>
	\${167 / 12 } >> ${167 / 12 } <br>
	<%-- \${167 div 12 } >> ${167 div 12 } <br> --%>
	\${66 % 15 } >> ${66 % 15 } <br><br>
	<%-- \${66 mod 15 } >> ${66 mod 15 } <br> --%>
	
	<hr width="100%" color="gray">
	
	<h1>EL 관계(비교) 연산자</h1>
	\${10 == 10 } >> ${10 == 10 } <br>
	\${10 eq 10 } >> ${10 eq 10 } <br><br>
	
	\${"Hello" == "hello"" } >> ${"Hello" == "hello" } <br> 
	\${"Hello" eq "hello"" } >> ${"Hello" eq "hello" } <br><br>
	
	\${20 != 10 } >> ${20 != 10 } <br><br>
	<%-- \${20 ne 10 } >> ${20 ne 10 } <br>  --%>
	
	\${20 < 10 } >> ${20 < 10 } <br><br>
	
	<hr width="100%" color="gray">
	
	<h1>EL 논리 연산자</h1>
	\${(10 == 10) && (20 == 20) } >> ${(10 == 10) && (20 == 20) } <br>
	\${(10 == 10) && (20 == 10) } >> ${(10 == 10) && (20 == 10) } <br>
	\${(10 == 10) || (20 == 10) } >> ${(10 == 10) || (20 == 10) } <br>
</body>
</html>