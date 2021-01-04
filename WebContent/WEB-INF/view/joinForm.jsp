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
	<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
	width: 80%;
	padding: 100px;
	margin-top: 100px;
	margin-bottom: 100px;
	border-top: 1px solid #93ae75; 
	border-bottom: 1px solid #93ae75;
	
	
}
.form-group {
	width: 70%;
	margin-left: 40px;
	font-size: 20px;
	padding-bottom: 15px;
}

</style>
</head>
<body>
	<div id="wrapper">



		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>

		<div class="container" >
			<form action="join.do" method="post">
				<div style="margin-bottom: 50px; margin-left: 30px;">
					<h2><i class="fas fa-pen-alt"></i> &nbsp;회원가입</h2>
				</div>
				<div class="form-group">
					<label for="inputName">이름</label> <input type="text" name="name"
						class="form-control" id="inputName">
				</div>
				<div class="form-group">
					<label for="inputId">아이디</label> <input type="text" name="id"
						class="form-control" id="inputId">
				</div>
				<div class="form-group">
					<label for="inputPwd">비밀번호</label> <input type="password"
						name="password" class="form-control" id="inputPwd">
				</div>
				<div class="form-group">
					<label for="inputconfirmPwd">비밀번호 확인</label> <input type="password"
						name="confirmPwd" class="form-control" id="inputconfirmPwd">
				</div>
				<div class="form-group">
					<label for="inputAddress">주소</label> <input type="text"
						name="address" class="form-control" id="inputAddress">
				</div>
				<div class="form-group">
					<label for="inputEmail">Email</label> <input type="text"
						name="email" class="form-control" id="inputEmail">
				</div>
				<div class="form-group">
					<label for="inputPhone">휴대폰 번호</label> <input type="text"
						name="phone" class="form-control" id="inputPhone">
				</div>



				<div class="form-group">
					<label for="inputAnimal">돌봄동물</label> <select name="animal"
						class="form-control">
						<option selected>돌봄동물을 선택하세요.</option>
						<option value="dog">강아지</option>
						<option value="cat">고양이</option>
						<option value="bird">새</option>
						<option value="rabbit">토끼</option>
						<option value="fish">물고기</option>
						<option value="hamster">햄스터</option>
						<option value="turtle">거북이</option>
						<option value="hedgehog">고슴도치</option>
						<option value="reptile">파충류</option>
						<option value="amphibian">양서류</option>
					</select>
				</div>

				<div class="form-group">
					<label for="statusRadio">회원종류</label>
					<div class="form-group col-md-4" id="statusRadio">
						<input type="radio" id="provider" name="status" value="0">
						<label for="provider">고객회원</label><br> 
						<input type="radio"
							id="customer" name="status" value="1"> <label
							for="customer">돌봄이회원</label><br>

					</div>
				</div>

				<div style="margin-right: 1020px; margin-top:80px; margin-left: 40px; width: 110px;">
				<button id=submitBtn type="submit" class="btn btn-outline-danger btn-lg">회원가입</button>
				</div>

			</form>
		</div>

		<%@ include file="footer.jsp"%>
	</div>


</body>
</html>