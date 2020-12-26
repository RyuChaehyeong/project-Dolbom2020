<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<script>
$(function() {
	$("#completeBtn").click(function() {
		var c = confirm ("돌봄 확정 이후에는 해당 돌봄이가 회원님의 정보를 열람가능합니다. \n확정하시겠습니까?");
		
	})
	
});
</script>
<style>
* {
	font-family: "Nanum Gothic", sans-serif;
}
#wrapper {
	max-width: 1400px;
	margin: 0 auto;
}

.container {
	width: 500px;
	padding: 70px;
	margin-top: 100px;
	margin-bottom: 100px;
	
	
}

#submitBtn {
	margin-top: 50px;
	position: relative;
	left: 50%;
}

.form-group {
	width: 600px;
}
</style>

<script>

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
			<h2><i class="fas fa-envelope-open-text"></i>&nbsp;${quote.provider } 돌봄이의 견적서 상세보기</h2>
		</div>
		
		<c:if test="${fn:trim(quote.provider)==fn:trim(authUser.member_id)}">
			<c:if test="${quote.complete == '0'}">
	
					<div class="d-grid gap-2 d-md-flex justify-content-md-start" >
						<button type="button" class="btn btn-outline-danger btn-lg" style="margin-bottom: 30px;" disabled>대기 중인 견적서</button>
					</div>	
			</c:if>
					<c:if test="${quote.complete == '1'}">
	
					<div class="d-grid gap-2 d-md-flex justify-content-md-start" >
						<button type="button" class="btn btn-outline-danger btn-lg" style="margin-bottom: 30px;" disabled>수락됨</button>
					</div>	
			</c:if>
		</c:if>
		
		<c:if test="${fn:trim(quote.reqSum.reqWriter)==fn:trim(authUser.member_id)}">
			<div class="d-grid gap-2 d-md-flex justify-content-md-start" style="margin-bottom: 30px;">
				<a href="${root }/quote/choose.do?quoteNo=${quote.quoteNo }&reqNo=${quote.reqSum.reqNo }" 
				class="btn btn-outline-danger btn-lg" id="completeBtn" role="button">돌봄 확정</a>
				</div>
		</c:if>

				<div class="form-group">
					<label for="inputTitle">제목</label> 
					<input type="text" name="title" value="${quote.title }" class="form-control" id="inputTitle" readonly>
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					</div>
				</div>
				<div class="form-group">
					<label for="inputLoc">지역</label>
					<input type="text" name="loc" class="form-control" id="inputLoc" value="${quote.location }" readonly>
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					</div>
				</div>
				
				<div>
					<label for="inputPrice">견적금액</label> 
					<input type="number" name="price" value="${quote.price }"
						class="form-control" id="inputPrice" style=" width: 300px;" readonly>
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					</div>
				</div>
				
				
				<div>
					 <label for="info">경력 및 특이사항 입력</label> 
					  <br />
					<textarea name="info" id="info" cols="80" rows="10" readonly>${quote.info }</textarea>
					  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
					</div>
				</div>

				
		<div class="d-grid gap-2 d-md-flex justify-content-md-start" >
			<a href="${root }/mypage/read.do" class="btn btn-outline-secondary btn-lg" style="margin-top: 20px"  role="button">
			견적서 목록</a>
		</div>

		</div>

		<%@ include file="footer.jsp"%>
	</div>


</body>
</html>