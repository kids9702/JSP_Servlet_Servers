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
        <li class="breadcrumb-item"><a href="#">List</a></li>
    <li class="breadcrumb-item active" aria-current="page">Read</li>
  </ol>
</nav>

<%@page import="vo.*" %>
<%
	BoardVO vo = (BoardVO)session.getAttribute("BoardVO");
	String nowPage = (String)request.getAttribute("nowPage");
	int start = (int)request.getAttribute("start");
	int end = (int)request.getAttribute("end");
	
%>

<%
	String MSG = (String)request.getAttribute("MSG");
	if(MSG!=null)
	{
%>
	<script>
		alert('<%=MSG%>');
	</script>
<%
	}
%>
<h2  class="mb-4">글내용</h2>
<table class="table w-75">
	<tr>
		<th>이메일</th>
		<td><%=vo.getEmail() %></td>
		<th>등록날짜</th>
		<td><%=vo.getRegdate() %></td>
	</tr>
	<tr>
		<th>제 목</th>
		<td colspan="3"><%=vo.getSubject() %></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<%
			if(vo.getFilename().equals("파일없음"))
			{
				%>
				<td colspan="3">파일없음</td>
				<%
			}
			else
			{
				%>
				<td colspan="3"><a href="/Board/download.do"><%=vo.getFilename() %></a> (<%=vo.getFilesize()/1024 %> Kbyte)</td>
				<%
				
			}
		%>
		
	</tr>
	<tr>
		<td colspan="4" style="height:250px;"><textarea rows=10 cols=50 class="form-control"><%=vo.getContent() %></textarea></td>
	</tr>
	<tr>
		<td colspan="4" align="right">IP : <%=vo.getIp() %> / 조회수 : <%=vo.getCount() %> </td>
	</tr>
	<tr>
		<td colspan="4">
		<a href="/Board/list.do?nowPage=<%=nowPage %>&start=<%=start %>&end=<%=end%>" class="btn btn-primary">LIST</a>&nbsp;
		<a href="/Board/updateReq.do?nowPage=<%=nowPage %>&start=<%=start %>&end=<%=end%>&flag=init" class="btn btn-primary">UPDATE</a>&nbsp;
		<a href="/Board/deleteReq.do?nowPage=<%=nowPage %>&start=<%=start %>&end=<%=end%>&flag=init" class="btn btn-primary">DELETE</a>&nbsp;
		</td>
	</tr>
</table>

<!--page-contents 끝  -->
</div>




<!-- Reply -->
<script>
	function postreply(){
		$.ajax({
			url:'/Board/replypost.do',
			type:'GET',
			datatype:'html',
			data:{'comment':$('#comment').val()},
			success:function(result){
				listreply();
			},
			error:function(){
				alert("문제 발생!");
			}
			
		});
	}
	function listreply(){
		$.ajax({
			url:'/Board/replylist.do',
			type:'GET',
			datatype:'html',
			success:function(result){
				$('#replydiv').empty();
				$('#replydiv').append(result);
				$('#comment').val('');
			},
			error:function(){
				alert("문제 발생!");
			}
			
		});
	}
	listreply();
</script>

<h3 class="p-3">Comment</h3>
<div class="w-75  p-3" style="background-color:#f1f2f1;height:500px;overflow:auto;" >
	<div class="row">
	<div class="col-10">
		<textarea row="3" class="form-control" id="comment"></textarea>
		</div>
			<div class="col-2">
				<button class="btn-secondary w-100 h-100" onclick="postreply()">SEND</button>
			</div>
		</div>
	<div id="replydiv">

	</div>
</div>




<!-- container-fluid 끝 -->
</div>





</body>
</html>