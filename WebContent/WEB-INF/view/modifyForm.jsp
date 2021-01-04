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
	$("#submitBtn").click(function(){
		var c = confirm("수정하시겠습니까?");
		if (c) {
			location.href="modify.do?reqNo=${param.no}";
		}
	});
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

</style>
</head>
<body>
	<div id="wrapper">



		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>

		<hr />
		<div class="container">
		<div style="padding-bottom: 50px; ">
			<h2><i class="fas fa-envelope-open-text"></i> ${authUser.member_id }님의 ${param.animal } 돌봄 요청글</h2>
		</div>
		
			<c:if test="${authUser.status == '1' }">
				<div class="d-grid gap-2 d-md-flex justify-content-md-end" style="padding: 15px;">
				<a href="${root }/request/quote.do" class="btn btn-outline-danger btn-lg"  role="button">견적보내기</a>
				</div>
			</c:if>
			<form action="${root }/request/modify.do" method="post">
				<input type="text" name="reqNo" value="${param.reqNo }" hidden />
				<div class="form-group">
					<label for="inputTitle">제목</label> 
					<input type="text" name="title" class="form-control" id="inputTitle" value="${param.title } ">					
					
						<c:if test="${errors.title }"><div class="d-grid gap-2 d-md-flex justify-content-md-end"><i class="fas fa-exclamation" style="color: red;"></i> 제목을 입력하세요.</div></c:if>
					
				</div>
			
				<div class="form-group">
					<label for="inputAnimal">돌봄동물</label> <select id="inputAnimal" name="animal"
						class="form-control" >
						<option selected>${param.animal }</option>
						<option value="강아지">강아지</option>
						<option value="고양이">고양이</option>
						<option value="새">새</option>
						<option value="토끼">토끼</option>
						<option value="물고기">물고기</option>
						<option value="햄스터">햄스터</option>
						<option value="거북이">거북이</option>
						<option value="고슴도치">고슴도치</option>
						<option value="파충류">파충류</option>
						<option value="양서류">양서류</option>
					</select>
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
						<c:if test="${errors.animal }">돌봄동물을 선택하세요.</c:if> 					
					</div>
				</div>
				<div class="form-group">
					<label for="inputLoc">지역</label>
						<input type="text" name="loc" class="form-control" id="inputLoc" value="${param.loc }">				
						<c:if test="${errors.local }">
							<div class="d-grid gap-2 d-md-flex justify-content-md-end">
								<i class="fas fa-exclamation" style="color: red;"></i> 지역을 입력하세요.
							</div>
						</c:if>  
				</div>
				
				<div class="form-group">
					 <label for="startDate">돌봄 시작 날짜</label> <br />
 					<input type="date" id="startDate" name="startDate" value="${param.startDate }">
 					 <c:if test="${errors.earlystart }"><i class="fas fa-exclamation" style="color: red;"></i> 오늘보다 늦은날 선택.</c:if> 
					 <c:if test="${errors.endIsBefore }"><i class="fas fa-exclamation" style="color: red;"></i> 종료날이 더 늦어야 합니다.</c:if>
				</div>
				
				<div class="form-group">
					 <label for="endDate">돌봄 종료 날짜</label> <br />				
 						 <input type="date" id="endDate" name="endDate" value="${param.endDate }">
 					  <c:if test="${errors.earlyend }"><i style="color: red;" class="fas fa-exclamation"></i> 오늘보다 늦은날 선택.</c:if> 
					 <c:if test="${errors.endIsBefore }"><i style="color: red;" class="fas fa-exclamation"></i> 종료날이 더 늦어야 합니다.</c:if>
  				
				</div>
				<div class="form-group">
					 <label for="info">반려동물 특이사항 입력</label> <br />
						<textarea name="info" id="info" cols="124" rows="10">${param.info }</textarea>
					  <c:if test="${errors.info }">
						 <div class="d-grid gap-2 d-md-flex justify-content-md-end">
							 <i class="fas fa-exclamation" style="color: red;"></i> 특이사항을 입력하세요.
						 </div>
					  </c:if>
				</div>
		
				<input type="submit" value="수정완료" id="submitBtn" type="submit" class="btn btn-light" />

			</form>

		<div class="d-grid gap-2 d-md-flex justify-content-md-start" >
		<a href="${root }/request/list.do" class="btn btn-outline-secondary" style="margin-top: 20px"  role="button">
		요청 목록</a>
		</div>
		</div>
		<hr />
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>