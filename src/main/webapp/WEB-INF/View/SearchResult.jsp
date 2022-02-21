<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 조회 페이지</h1>
<%@page import="vo.*" %>
<%
		MemberVO vo = (MemberVO)request.getAttribute("vo");
		out.println("EMAIL : " + vo.getEmail()+"<br>");
		out.println("PW : " + vo.getPwd()+"<br>");
		out.println("ADDR1 : " + vo.getAddr1()+"<br>");
		out.println("ADDR2 : " + vo.getAddr2()+"<br>");
		out.println("ROLE  : " + vo.getRole()+"<br>");
%>

</body>
</html>