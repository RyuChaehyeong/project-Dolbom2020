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
	margin-top: 100px;
	margin-bottom: 100px;
	
	
}

#submitBtn {
	margin-top: 50px;
	position: relative;
	left: 50%;
}
</style>

<script>
$(function() {
	$("#submitBtn").click(function(){
		var c = confirm("등록하시겠습니까?");
		if (c) {
			var c2 = confirm("완료되었습니다.");
			location.href="${root }/request/write.do";
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
			<h2><i class="fas fa-pen-alt"></i> 돌봄 요청 작성</h2>
		</div>
			<form action="${root }/request/write.do" method="post">

				<div class="form-group">
					<label for="inputTitle">제목</label> 
					<input type="text" name="title" value="${param.title }"
						class="form-control" id="inputTitle">
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<c:if test="${errors.title }"><i class="fas fa-exclamation" style="color: red;"></i> 제목을 입력하세요.</c:if>
					</div>
				</div>
			
				<div class="form-group">
					<label for="inputAnimal">돌봄동물</label>
					<select name="animal"class="form-control" >
						<option selected>돌봄동물을 선택하세요.</option>
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
					<c:if test="${errors.animal }"><i class="fas fa-exclamation" style="color: red;"></i> 돌봄동물을 선택하세요.</c:if> 					
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
					 <label for="startDate">돌봄 시작 날짜</label> 
					 <br />
 					 <input type="date" id="startDate" name="startDate" value="${param.startDate }">
					 <c:if test="${errors.startDate }"> <i class="fas fa-exclamation" style="color: red;"></i> 시작 날짜를 선택하세요.</c:if>  
					 <c:if test="${empty errors.startDate && errors.earlystart }"><i class="fas fa-exclamation" style="color: red;"></i> 오늘보다 늦은날 선택.</c:if> 
					 <c:if test="${empty errors.startDate && errors.endIsBefore }"><i class="fas fa-exclamation" style="color: red;"></i> 종료날이 더 늦어야 합니다.</c:if>
					 
  		
				</div>
				
				<div class="form-group">
					 <label for="endDate">돌봄 종료 날짜</label> 
					 <br />
 					 <input type="date" id="endDate" name="endDate" value="${param.endDate }">
					 <c:if test="${errors.endDate }"> <i style="color: red;" class="fas fa-exclamation"></i> 마지막 날짜를 선택하세요.</c:if>  
					 <c:if test="${empty errors.startDate && errors.earlyend }"><i style="color: red;" class="fas fa-exclamation"></i> 오늘보다 늦은날 선택.</c:if> 
					 <c:if test="${empty errors.startDate && errors.endIsBefore }"><i style="color: red;" class="fas fa-exclamation"></i> 종료날이 더 늦어야 합니다.</c:if>
  				
				</div>
				<div class="form-group">
					 <label for="info">반려동물 특이사항 입력</label> 
					  <br />
					<textarea name="info" id="info" cols="140" rows="10">${param.info }</textarea>
					  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
					  <c:if test="${errors.info }"><i class="fas fa-exclamation" style="color: red;"></i> 특이사항을 입력하세요.</c:if>
					</div>
				</div>
				
				
		
		<div class="d-grid gap-2 d-md-flex justify-content-md-start" >
				<button id="submitBtn" type="submit" class="btn btn-outline-secondary btn-lg">등록하기</button>
		</div>

			</form>
		</div>

		<%@ include file="footer.jsp"%>
	</div>


</body>
</html>