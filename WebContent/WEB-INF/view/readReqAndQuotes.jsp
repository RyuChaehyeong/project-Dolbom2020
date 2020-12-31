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

.request {
	margin-top: 50px;
	margin-bottom: 50px;
	border: 1px solid lightgray;
	padding: 50px;
}

.section {
	margin-top: 50px;
	margin-bottom: 50px;
	border: 1px solid lightgray;
	padding: 50px;

}
</style>
</head>
<body>
	<div id="wrapper">
		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>


		<div class="container">
			<h1><i class="fas fa-paw"></i>&nbsp; 나의 요청 내역</h1>
			<div class="request">
				<br />
				<div class="form-group">
					<label for="inputTitle">제목</label> 
					<input type="text" name="title" class="form-control" id="inputTitle" value="${request.title }" readonly>
				</div>
				
				<div class="form-group">
					<label for="inputAnimal">돌봄동물</label> 
					<select id="inputAnimal" name="animal" class="form-control" disabled="disabled">
						<option selected>${request.animal }</option>
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
	
				</div>
					
				<div class="form-group">
					<label for="inputLoc">지역</label>
					<input type="text" name="loc" class="form-control" id="inputLoc" value="${request.local }" readonly>
				</div>
					
				<div class="form-group">
					 <label for="startDate">돌봄 시작 날짜</label> <br />
	 				 <input type="date" id="startDate" name="startDate" value="${request.startDate }" disabled="disabled">
				</div>
					
				<div class="form-group">
					 <label for="endDate">돌봄 종료 날짜</label> <br />
	 				 <input type="date" id="endDate" name="endDate" value="${request.endDate }" disabled="disabled">
	 			</div>
				
				<div class="form-group">
					 <label for="info">반려동물 특이사항 입력</label> <br />
					<textarea name="info" id="info" cols="124" rows="10" readonly>${request.info }</textarea>
				</div>
				
			</div>
			
			<hr />

			<c:if test="${quotePage.hasNoQuotes() }">		
					<div class="truncate" >게시글이 없습니다.</div>		
			</c:if>
			<br /><br />
			<h1><i class="fas fa-paw"></i>&nbsp; ${request.quoteCnt }개의 견적서가 도착했습니다.</h1>
			<c:forEach var="quote" items="${quotePage.quoteList }">
				<div class="section">
			
			<c:if test="${quote.complete == '0' }">
				<div class="d-grid gap-2 d-md-flex justify-content-md-start" style="margin-bottom: 50px;">
					<a href="${root }/quote/choose.do?quoteNo=${quote.quoteNo }&reqNo=${quote.reqSum.reqNo }" 
					class="btn btn-outline-danger btn-lg" id="completeBtn" role="button">돌봄 확정</a>
				</div>
			</c:if>
			<c:if test="${quote.complete == '1' }">
				<div class="d-grid gap-2 d-md-flex justify-content-md-start" style="margin-bottom: 50px;">
					<button type="button" class="btn btn-success btn-lg" disabled>채택된 견적서</button>
				</div>
			</c:if>
	
				
				<h2><i class="fas fa-envelope-open-text"></i>&nbsp;<a href="${root }/review/read.do?target=${quote.provider }">
						<c:out value="${quote.provider }"></c:out>
					</a>돌봄이의 견적서 
					 </h2>
				<br />
				<div class="form-group">
					<label for="inputTitle">제목</label> 
					<input type="text" name="title" value="${quote.title }" class="form-control" id="inputTitle" style="width: 500px;" readonly>
				</div>
				
				<div class="form-group">
					<label for="inputLoc">지역</label>
					<input type="text" name="loc" class="form-control" id="inputLoc" value="${quote.location }" style="width: 500px;" readonly>
				</div>
				
				<div>
					<label for="inputPrice">견적금액</label> 
					<input type="number" name="price" value="${quote.price }"
							class="form-control" id="inputPrice" style=" width: 300px;" readonly>
				</div>
				
				<div>
					<label for="info">경력 및 특이사항 입력</label> 
					<br />
					<textarea name="info" id="info" cols="80" rows="10" readonly>${quote.info }</textarea>
				</div>
	

				</div>
			</c:forEach>
	
			<c:if test="${quotePage.hasQuotes() }">
				<nav aria-label="Page navigation example" style="padding-top: 40px;">
					<ul class="pagination justify-content-center">
						<c:if test="${quotePage.startPage > 5}">
							<li class="page-item">
						 		<a class="page-link" href="read.do?qpageNo=${requestPage.startPage - 5 }"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if> 
				
						<c:forEach var="pNo" begin="${quotePage.startPage }" end="${quotePage.endPage }">
							<li class="page-item"><a class="page-link" href="read.do?qpageNo=${pNo }">${pNo }</a></li>
						</c:forEach> 
						
						<c:if test="${quotePage.endPage < quotePage.totalPages }">
								<li class="page-item">
									<a class="page-link" href="read.do?qpageNo=${quotePage.startPage + 5 }"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
									</a>
								</li>
						</c:if>
					</ul>
				</nav>
			</c:if>
	

	</div>
		<hr />
		<%@ include file="footer.jsp"%>
</div>
					
</body>
</html>