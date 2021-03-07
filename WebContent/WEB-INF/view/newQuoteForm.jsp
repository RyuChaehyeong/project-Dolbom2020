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
	width: 90%;
	margin-left: 40px;
	font-size: 20px;
	padding-bottom: 15px;
}
#submitBtn {
	margin-top: 50px;
	position: relative;
	left: 50%;
}

.form-group {
	width: 70%;
	margin-left: 40px;
	font-size: 20px;
	padding-bottom: 15px;
}
</style>

<script>
$(function() {
	$("#submitBtn").click(function(){
		var c = confirm("등록하시겠습니까?");
		if (c) {
			location.href="${root }/quote/write.do";
		}
	});
});
</script>
</head>
<body>
	<div id="wrapper">



		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>
		<br />
		<hr />
		<div class="container">
		<div style="padding-bottom: 50px;">
			<h2><i class="fas fa-pen-alt"></i>견적서 작성</h2>
		</div>
			<form action="${root }/quote/write.do?reqNo=${param.reqNo }&reqWriter=${param.reqWriter }&reqTitle=${param.reqTitle }" method="post" >

				<div class="form-group">
					<label for="inputTitle">제목</label> 
					<input type="text" name="title" value="${param.title }" class="form-control" id="inputTitle">
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<c:if test="${errors.title }"><i class="fas fa-exclamation" style="color: red;"></i> 제목을 입력하세요.</c:if>
					</div>
				</div>
				<div class="form-group">
					<label for="inputLoc">지역</label>
					<input type="text" name="loc" class="form-control" id="inputLoc" value="${param.loc }">
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<c:if test="${errors.local }"><i class="fas fa-exclamation" style="color: red;"></i> 지역을 입력하세요.</c:if>  
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputPrice">견적금액</label> 
					<input type="number" name="price" value="${param.price }"
						class="form-control" id="inputPrice" style=" width: 300px;" min="0" max="1000000">
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<c:if test="${errors.price }"><i class="fas fa-exclamation" style="color: red;"></i> 견적금액을 입력하세요.</c:if>
					</div>
				</div>
				
				
				<div class="form-group">
					 <label for="info">경력 및 특이사항 입력</label> 
					  <br />
					<textarea name="info" id="info" cols="85" rows="10">${param.info }</textarea>
					  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
					  <c:if test="${errors.info }"><i class="fas fa-exclamation" style="color: red;"></i> 특이사항을 입력하세요.</c:if>
					</div>
				</div>
				
				<div style="margin-right: 1020px; margin-top:50px; width: 90px;">
					<input type="submit" value="견적서 보내기" 
					class="btn btn-outline-secondary btn-lg"  id="submitBtn"/>
				</div>
			</form>
		</div>

		<%@ include file="footer.jsp"%>
	</div>


</body>
</html>