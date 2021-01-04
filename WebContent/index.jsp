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

.container0 {
	display: flex;
	flex-direction: column;
	width: 1400px;
}

.container1 {
	margin-top: 100px;
	height: 500px;
	padding-top: 20px;
	display: flex;
	flex-direction: row;
	flex-wrap: nowrap;
	justify-content: space-evenly;
}

.item1 {
	height: 100%;
	width: 73%;
	margin-top:15px;
	padding-left: 25px;
	padding-right: 20px;
}

.loginForm {
	display: flex;
	flex-direction: column;
	height: 100%;
	width: 23%;
	padding: 30px;
	background-color:#f8ffd7;
}

.container2 {
	margin-bottom: 120px;
	height: 550px;
	padding-top: 20px;
	display: flex;
	flex-direction: row;
	flex-wrap: nowrap;
	justify-content: space-evenly;
	align-content: center;
}

.item3 {
	background-color:#f8ffd7;
	height: 90%;
	width: 48%;
	margin: 10px;
}

.item4 {
	background-color:#f8ffd7;
	height: 90%;
	width: 48%;
	margin: 10px;
}

#loginBtn {
	background-color: #f8ffd7;
	color: black;
	height: 40px;
	width: 100%;
	border-color: #ffccbc;
	border-radius: 8px;
}

#requestList {
	background-color:#c5e1a5;
	margin: 50px;
	width: 75%;
	height: 75%;
	font-style: normal;
}

.listContainer {
	position: relative;
}
.text_hide {
	display: block;
	color: black;
	font-size: 40px;
	height: 100%;
	width: 100%;
	padding-top: 100px;
	padding-left: 45px;
}

.info-container {
	display: flex;
	flex-direction: row;
	text-align: center;
	padding-left: 28px;
	padding-right: 23px;
	margin-top: 30px;
	margin-bottom: 20px;
	border-top: 2px solid  #93ae75;
	border-bottom: 2px solid  #93ae75;
	
	
}

.info-item {
	padding-top: 7px;
	padding-bottom: 7px;
	text-align: center;
	margin-left: 5px;
	margin-right: 5px;

	
}

</style>
</head>
<body>
	<div id="wrapper">


		<div class="container0">
			<%@ include file="header.jsp"%>
			<%@ include file="navbar.jsp"%>
			<div class="container1">
				<div class="item1">
					<div id="carouselExampleIndicators" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleIndicators" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img src="puppy.jpg" style="height: 450px; width: 500px;"
									class="w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img src="cats.jpg" style="height: 450px; width: 500px;"
									class="w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img src="rabbit.jpg" style="height: 450px; width: 500px;"
									class="w-100" alt="...">
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleIndicators"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next"
							href="#carouselExampleIndicators" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>


				<div class="loginForm">
					<u:notLogin>
						<form action="login.do" method="post"
							style="margin-top: 30px; padding: 20px; background-color: #c5e1a5; border-radius: 2%;">
							<div class="form-group" style="margin-top: 15px;">
								아이디 <input type="text" name="id" class="form-control"
									id="exampleInputEmail1">
							</div>
							<div class="form-group">
								비밀번호 <input type="password" name="password" class="form-control" />
							</div>

							<button class="btn btn-light" style="width: 100%; margin-top: 15px; margin-bottom: 20px;" type="submit">로그인</button>
						</form>
					</u:notLogin>
					<u:isLogin>
							<div style=" margin-top: 45px; border-top: 2px solid #93ae75; border-bottom: 2px solid #93ae75; padding: 20px;">
							<h3>${authUser.name }<c:if test="${authUser.status == '0'  }"> 회원님, </c:if><c:if test="${authUser.status == '1' }"> &nbsp;돌봄이님, 	</c:if></h3> <br />
							<h3>안녕하세요!</h3>
							</div>
							<div class="info-container">
							<div class="info-item" style="padding-right: 20px;">
							나의 평점 <br /> 
							 <a href="${root }/review/read.do?target=${authUser.member_id }"> ${memberInfo.score }
							</a>
							</div>
							<div class="info-item">
							<c:if test="${authUser.status == '0'  }">
								도착한 견적서 <br /> 
								 <a href="${root }/mypage/read.do"> ${newQuoNum }</a>
							 </c:if>
							<c:if test="${authUser.status == '1'  }">
								대기 견적서 <br /> 
								 <a href="${root }/mypage/read.do"> ${newQuoNum }</a>
							 </c:if>
							</div>
						
							</div>
							<br /> 
							<a class="btn btn-light" href="logout.do" role="button">로그아웃</a>
							
					</u:isLogin>
				</div>


			</div>
			

			<div class="container2">
				<div class="item3">
				<div id="requestList" class="listContainer">
				
					<a href="${root }/request/list.do" class="text_hide"> 돌봄서비스가<br /> 필요한 반려동물
						&nbsp; <i class="fas fa-angle-double-right"></i><br />찾으러가기
					</a>
				
				</div>
				</div>
				
				<div class="item4">
				<div id="requestList" class="listContainer">
				
					<a href="${root }/request/write.do" class="text_hide"> 내 반려동물<br /> 돌봄요청
						&nbsp; <i class="fas fa-angle-double-right"></i><br /> 등록하기
					</a>
		
				</div>
				
				</div>
			</div>
		</div>

		<%@ include file="footer.jsp"%>

	</div>
</body>
</html>