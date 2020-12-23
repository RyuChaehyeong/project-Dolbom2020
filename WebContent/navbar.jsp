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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
.navbar {
width: 1400px;
}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<!-- Image and text -->

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="${root }/index.jsp"> Home <i class="fas fa-igloo"></i><span
					class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a class="nav-link"
				href="${root }/request/list.do"> 게시글 <i
					class="fas fa-clipboard-list"></i></a></li>
			<li class="nav-item"><a class="nav-link"
				href="${root }/request/write.do"> 글작성 <i
					class="fas fa-pencil-alt"></i></a></li>
		</ul>

		<u:notLogin>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${root }/login.do">로그인하기</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${root }/join.do">회원가입</a></li>
			</ul>
		</u:notLogin>

		<u:isLogin>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${root }/logout.do"> 로그아웃 <i class="fas fa-sign-out-alt"></i></a></li>
				<li class="nav-item"><a class="nav-link"
					href="${root }/changePwd.do">비밀번호 변경</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${root }/removeMember.do">회원 탈퇴</a></li>
			</ul>
		</u:isLogin>


	</div>
</nav>
</body>
</html>