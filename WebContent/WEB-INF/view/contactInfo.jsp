<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<style>
* {
	font-family: "Nanum Gothic", sans-serif;
}
#wrapper {
	max-width: 1400px;
	margin: 0 auto;
}

.container {
	display: flex;
	flex-direction: column;
	flex-wrap: nowrap;
	width: 500px;
	padding: 70px;
	margin-top: 100px;
	margin-bottom: 250px;

}

table, td, th {
	text-align: center;
}

table {
	width: 100%;
}

th, td {
padding: 15px;
}

th>.truncate, td>.truncate{
  width: auto;
  min-width: 0;
  max-width: 200px;
  display: inline-block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

</style>
</head>
<body>
<div id="wrapper">



		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>
		<div class="container">

			<h2><i class="fas fa-paw"></i>&nbsp;회원님과 돌봄계약이 성사된</h2>
			<h2>&emsp; ${member.member_id }님의 연락정보입니다.</h2>
			<br /><br />
			<table class="table">
			  <thead class="thead-light">
			    <tr>
			      <th scope="col">이름</th>
			      <th scope="col">휴대폰번호</th>
			      <th scope="col">이메일</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <td>${member.name }</td>
			      <td>${member.phone }</td>
			      <td>${member.email }</td>
			    </tr>
			    
			</table>
		</div>
		<%@ include file="footer.jsp"%>
	</div>


</body>
</html>