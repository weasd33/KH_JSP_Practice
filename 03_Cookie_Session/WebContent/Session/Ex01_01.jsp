<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String userId = request.getParameter("id");
	String userPwd = request.getParameter("pwd");
	
	// 원래는 DB의 회원 관리 테이블에서 입력한 아이디와 비밀번호가 맞는지
	// 확인을 하여 회원이면 메인 페이지로 이동
	String dbId = "SHIM";
	String dbPwd = "1234";
	
	if(userId.equals(dbId)) { // 입력폼 창에서 입력한 아이디와 DB상의 아이디가 일치하는 경우
		if(userPwd.equals(dbPwd)) { // 입력폼 창에서 입력한 비밀번호와 DB상의 비밀번호가 일치하는 경우
			// 회원이므로 메인 페이지로 이동
			// 정보를 이동하는 페이지로 전달하는 방법
			// 세션 설정하여 정보를 전달하는 방법
			session.setAttribute("ID", userId);
			session.setAttribute("PWD", userPwd);
			session.setAttribute("NAME", "심규복");
			
			RequestDispatcher rd = request.getRequestDispatcher("Ex01_02.jsp"); // 이동 위치
			
			rd.forward(request, response); // 실제 페이지 이동
		}
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>