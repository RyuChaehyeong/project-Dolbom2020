<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<style>
#wrapper {
	max-width: 1400px;
	margin: 0 auto;
}

.container {
	padding: 100px;
	margin-top:50px;
	
	margin-bottom: 150px;
}

#requestList {
	background-color: #f9fbe7;
	margin: 50px;
	width: 300px;
	height: 200px;
	font-style: normal;
}

.listContainer {
	position: relative;
}

.text_hide {
	display: block;
	height: 100%;
	width: 100%;
	padding-top: 60px;
	padding-left: 30px;
}

a:link {
	color: black;
}

a:visited {
	color: black;	
}

a.active {
	color: black;
}
</style>
<body>
	<div id="wrapper">



		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>
		<div class="container">
			<h1>${param.name }님, 회원가입이 완료되었습니다!</h1>

			<div id="requestList" class="listContainer">
			<c:if test="${param.status == '1' }">
				<h4>
					<a href="list.do" class="text_hide"> 돌봄서비스가<br /> 필요한 반려동물
						&nbsp; <i class="fas fa-angle-double-right"></i><br />찾으러가기
					</a>
				</h4>
			</c:if>
			
			<c:if test="${param.status == '0' }">
				<h4>
					<a href="list.do" class="text_hide"> 내 반려동물<br /> 돌봄요청
						&nbsp; <i class="fas fa-angle-double-right"></i><br /> 등록하기
					</a>
				</h4>
			</c:if>

			</div>


		</div>

		<%@ include file="footer.jsp"%>

	</div>
</body>
</html>