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
<style>
#wrapper {
	max-width: 1400px;
	margin: 0 auto;
}

.container {
	width: 500px;
	padding: 70px;
	
	border: 1px solid lightgray;
	margin-top: 100px;
	margin-bottom: 100px;
	
	
}

#submitBtn {
	margin-top: 50px;
	position: relative;
	left: 50%;
}
</style>
</head>
<body>
	<div id="wrapper">



		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>

		<div class="container">
			<form action="join.do" method="post">

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


				<button id=submitBtn type="submit" class="btn btn-primary">회원가입</button>

			</form>
		</div>

		<%@ include file="footer.jsp"%>
	</div>


</body>
</html>