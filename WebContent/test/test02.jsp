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




		<div class="container">
			<form action="${root }/test01" method="post">

				<div class="form-group">
					<label for="inputTitle">제목</label> <input type="text" name="title"
						class="form-control" id="inputTitle">
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
					<label for="inputLoc">지역</label> <input type="text"
						name="loc" class="form-control" id="inputLoc">
				</div>
				
				<div class="form-group">
					 <label for="startDate">돌봄 시작 날짜</label>
 					 <input type="date" id="startDate" name="startDate">
  					
				</div>
				
				<div class="form-group">
					 <label for="endDate">돌봄 시작 날짜</label>
 					 <input type="date" id="endDate" name="endDate">
  					
				</div>
				<div class="form-group">
					 <label for="info">반려동물 특이사항 입력</label> <br />
					<textarea name="info" id="info" cols="155" rows="10"></textarea>
				</div>
				
				

				<button id=submitBtn type="submit" class="btn btn-primary">등록하기</button>

			</form>
		</div>


	</div>


</body>
</html>