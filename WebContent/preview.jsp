<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
	margin-top: 50px;
	margin-bottom: 50px;

}
#head {
	background-color: #f9fbe7;
}

.container2 {
	margin: 10px;
	padding: 40px;

}

.item {

	margin: 40px;
	padding: 30px;
}
</style>
<title>Insert title here</title>
</head>
<body>

	<div id="wrapper">
		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>

		<div class="container ">

				<div id="head" class="container2">
					<h3>공지사항 입니다.</h3> <br>
					<p>안녕하세요. 방문해주셔서 감사합니다.</p>
					<p>돌봄 개발자 유채형입니다. 현재 견적서, 마이페이지가 유지보수 중에 있습니다.</p>
					<p>빠른 시일 내에 완료하도록 하겠습니다.</p>
					<p>혹시라도 돌봄 페이지를 궁금해 하실 분들을 위해 화면을 캡쳐하여 보여드리겠습니다.</p>
				</div>
				
				<div id="preview" class="container2">
					<hr>
					<div class="item">
						<h5>1. 견적서 작성 페이지입니다.</h5>
						<br>
						<img src="writeQ.png">
					</div>
					
					<hr>
					<div class="item">
						<h5>2. 견적서 목록 페이지입니다.</h5>
						<br>
						<img src="listQ.png">
					</div>
					
					<hr>
					<div class="item">
						<h5>3. 돌봄이의 마이 페이지입니다.</h5>
						<br>
						<img src="provmypage.png">
					</div>
					<hr>
					
					<div class="item">
						<h5>4. 일반 고객의 마이 페이지입니다.</h5>
						<br>
						<img src="custmypage.png">
					</div>
					
					<hr>
					
				</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>


</body>
</html>