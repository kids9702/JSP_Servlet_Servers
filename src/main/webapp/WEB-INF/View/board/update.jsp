<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- 링크경로(Head포함) -->
<%@include file="../../module/link.jsp" %>


<body>

<div class="container-fluid">
<!-- meta_Header -->
<%@include file="../../module/meta_header.jsp" %>
<!-- header -->
<%@include file="../../module/header.jsp" %>
<!-- nav -->
<%@include file="../../module/nav.jsp" %>

<div class="page-contents p-3">

<!-- 페이지 위치정보(브레드크럼 사용) -->
<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="#">Home</a></li>
    <li class="breadcrumb-item"><a href="#">Board</a></li>
    <li class="breadcrumb-item active" aria-current="page">Update</li>
  </ol>
</nav>

<%@page import="vo.*" %>
<%
	BoardVO vo = (BoardVO)session.getAttribute("BoardVO");
	//String nowPage = (String)request.getAttribute("nowPage");
	//int start = (int)request.getAttribute("start");
	//int end = (int)request.getAttribute("end");
	
%>


<h2  class="mb-4">글수정</h2>
<form  action="/Board/update.do">
<table class="table w-75">
	<tr>
		<th>이메일</th>
		<td><%=vo.getEmail() %></td>
		<th>등록날짜</th>
		<td><%=vo.getRegdate() %></td>
	</tr>
	<tr>
		<th>제 목</th>
		<td colspan="3"><input name="subject" value="<%=vo.getSubject() %>" class="form-control"></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td colspan="3"><%=vo.getFilename() %> (<%=vo.getFilesize() %> byte)</td>
	</tr>
	<tr>
		<td colspan="4" style="height:250px;"><textarea rows=10 cols=50 class="form-control" name="content"><%=vo.getContent() %></textarea></td>
	</tr>
	<tr>
		<td colspan="4" align="right">IP : <%=vo.getIp() %> / 조회수 : <%=vo.getCount() %> </td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="submit" value="UPDATE" class="btn btn-primary">
			<a href="/Board/list.do" class="btn btn-primary">CANCEL</a>
		</td>
	</tr>
</table>


	<input type="hidden" name=nowPage value="<%=request.getParameter("nowPage") %>">
	<input type="hidden" name=start value="<%=request.getParameter("start") %>">
	<input type="hidden" name=end value="<%=request.getParameter("end") %>">
</form>
<!--page-contents 끝  -->
</div>

<!-- container-fluid 끝 -->
</div>

</body>
</html>