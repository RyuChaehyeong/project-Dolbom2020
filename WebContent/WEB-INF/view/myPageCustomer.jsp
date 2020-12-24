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
	margin-bottom: 80px;

}

table, td, th {
	text-align: center;
}

table {
	width: 100%;
	margin-top: 30px;
}

th, td {
padding: 15px;
}

th>.truncate, td>.truncate{
  width: auto;
  min-width: 0;
  max-width: 200px;
  display: inline-block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.section {
	margin: 70px;

}


</style>
</head>
<body>
<div id="wrapper">



		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>

		<div class="container ">
		<div style="padding-bottom: 50px;">
			<h2><i class="far fa-list-alt"></i>&nbsp; ${authUser.name }님의 마이페이지</h2>
		</div>
	
<div class="section">
			<h4><i class="fas fa-paw"></i>&nbsp; My돌봄 요청</h4>
	<table class="table table-hover">
	  <thead>
		
		<tr>
			<th><div class="truncate" >No.</div></th>
			<th><div class="truncate" >제목</div></th>
			<th><div class="truncate" >돌봄동물</div></th>
			<th><div class="truncate" >요청날짜</div></th>
			<th><div class="truncate">진행/마감</div></th>
		</tr>
		</thead>
		
		<tbody>
		<c:if test="${requestPage.hasNoRequests() }">
			<tr>
				<td colspan="5"><div class="truncate" >게시글이 없습니다.</div></td>
			</tr>
		</c:if>

		<c:forEach var="request" items="${requestPage.reqList }">
			<tr>
				<td>${request.reqNo }</td>
				<td><div class="truncate" >
					<a href="${root }/request/read.do?no=${request.reqNo }&pageNo=${requestPage.currentPage}">
						<c:out value="${request.title }" />
					</a></div>
				</td>
				
				<td><div class="truncate" >${request.animal }</div></td>
				<td><div class="truncate" >${request.startDate } ~ ${request.endDate }</div></td>
				<td><div class="truncate">${request.complete }</div></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

		<c:if test="${requestPage.hasRequests() }">
			<tr>
				<td colspan="5">
					<nav aria-label="Page navigation example" style="padding-top: 40px;">
					<ul class="pagination justify-content-center">
					
					 <c:if test="${requestPage.startPage > 5}">
					 <li class="page-item">
					 	<a class="page-link" href="read.do?pageNo=${requestPage.startPage - 5 }"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if> 
			
					<c:forEach var="pNo" begin="${requestPage.startPage }"
						end="${requestPage.endPage }">
						<li class="page-item"><a class="page-link" href="read.do?pageNo=${pNo }">${pNo }</a></li>
					</c:forEach> 
					
					<c:if test="${requestPage.endPage < requestPage.totalPages }">
							<li class="page-item">
								<a class="page-link" href="read.do?pageNo=${requestPage.startPage + 5 }"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a>
							</li>
					</c:if>
					</ul>
					</nav>
				</td>

			</tr>

		</c:if>
</div>		


<div class="section">
	<h4><i class="fas fa-paw"></i>&nbsp; 내가 받은 견적서</h4>
	<table class="table table-hover">
	  <thead>
		
		<tr>
			<th><div class="truncate" >No.</div></th>
			<th><div class="truncate" >제목</div></th>
			<th><div class="truncate" >돌봄동물</div></th>
			<th><div class="truncate" >요청날짜</div></th>
		</tr>
		</thead>
		
		<tbody>
		<c:if test="${requestPage.hasNoRequests() }">
			<tr>
				<td colspan="5"><div class="truncate" >게시글이 없습니다.</div></td>
			</tr>
		</c:if>

		<c:forEach var="request" items="${requestPage.reqList }">
			<tr>
				<td>${request.reqNo }</td>
				<td><div class="truncate" >
					<a href="${root }/request/read.do?no=${request.reqNo }&pageNo=${requestPage.currentPage}">
						<c:out value="${request.title }" />
					</a></div>
				</td>
				
				<td><div class="truncate" >${request.animal }</div></td>
				<td><div class="truncate" >${request.startDate } ~ ${request.endDate }</div></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

		<c:if test="${requestPage.hasRequests() }">
			<tr>
				<td colspan="5">
					<nav aria-label="Page navigation example" style="padding-top: 40px;">
					<ul class="pagination justify-content-center">
					
					 <c:if test="${requestPage.startPage > 5}">
					 <li class="page-item">
					 	<a class="page-link" href="read.do?pageNo=${requestPage.startPage - 5 }"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if> 
			
					<c:forEach var="pNo" begin="${requestPage.startPage }"
						end="${requestPage.endPage }">
						<li class="page-item"><a class="page-link" href="read.do?pageNo=${pNo }">${pNo }</a></li>
					</c:forEach> 
					
					<c:if test="${requestPage.endPage < requestPage.totalPages }">
							<li class="page-item">
								<a class="page-link" href="read.do?pageNo=${requestPage.startPage + 5 }"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a>
							</li>
					</c:if>
					</ul>
					</nav>
				</td>

			</tr>

		</c:if>
</div>		



<div class="section">
	<h4><i class="fas fa-paw"></i>&nbsp; 나의 돌봄 이용 내역</h4>
	<table class="table table-hover">
	  <thead>
		
		<tr>
			<th><div class="truncate" >No.</div></th>
			<th><div class="truncate" >제목</div></th>
			<th><div class="truncate" >돌봄동물</div></th>
			<th><div class="truncate" >요청날짜</div></th>
		</tr>
		</thead>
		
		<tbody>
		<c:if test="${requestPage.hasNoRequests() }">
			<tr>
				<td colspan="5"><div class="truncate" >게시글이 없습니다.</div></td>
			</tr>
		</c:if>

		<c:forEach var="request" items="${requestPage.reqList }">
			<tr>
				<td>${request.reqNo }</td>
				<td><div class="truncate" >
					<a href="${root }/request/read.do?no=${request.reqNo }&pageNo=${requestPage.currentPage}">
						<c:out value="${request.title }" />
					</a></div>
				</td>
				
				<td><div class="truncate" >${request.animal }</div></td>
				<td><div class="truncate" >${request.startDate } ~ ${request.endDate }</div></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

		<c:if test="${requestPage.hasRequests() }">
			<tr>
				<td colspan="5">
					<nav aria-label="Page navigation example" style="padding-top: 40px;">
					<ul class="pagination justify-content-center">
					
					 <c:if test="${requestPage.startPage > 5}">
					 <li class="page-item">
					 	<a class="page-link" href="read.do?pageNo=${requestPage.startPage - 5 }"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if> 
			
					<c:forEach var="pNo" begin="${requestPage.startPage }"
						end="${requestPage.endPage }">
						<li class="page-item"><a class="page-link" href="read.do?pageNo=${pNo }">${pNo }</a></li>
					</c:forEach> 
					
					<c:if test="${requestPage.endPage < requestPage.totalPages }">
							<li class="page-item">
								<a class="page-link" href="read.do?pageNo=${requestPage.startPage + 5 }"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a>
							</li>
					</c:if>
					</ul>
					</nav>
				</td>

			</tr>

		</c:if>
</div>				
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>