<%@page import="com.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("paramId").trim();

	MemberDAO dao = MemberDAO.getInstance();
	int result = dao.checkMemberId(userId);
	
	// Ajax에게 응답
	out.println(result);
%>