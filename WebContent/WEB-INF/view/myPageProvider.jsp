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
	<h4><i class="fas fa-paw"></i>&nbsp; 내가 보낸 견적서</h4>
	<table class="table table-hover">
	  <thead>
		
		<tr>
			<th><div class="truncate">견적서 번호</div></th>
			<th><div class="truncate" >요청서 제목</div></th>
			<th><div class="truncate" >견적서 제목</div></th>
			<th><div class="truncate" >견적 금액</div></th>
			<th><div class="truncate" >수락/마감</div></th>
		</tr>
		</thead>
		
		<tbody>
		<c:if test="${quotePage.hasNoQuotes() }">
			<tr>
				<td colspan="5"><div class="truncate" >게시글이 없습니다.</div></td>
			</tr>
		</c:if>

		<c:forEach var="quote" items="${quotePage.quoteList }">
			<tr>
				<td><div class="truncate">${quote.quoteNo }</div></td>
				<td><div class="truncate" >
					<a href="${root }/request/read.do?no=${quote.reqSum.reqNo }&qpageNo=${quotePage.currentPage}">
						<c:out value="${quote.reqSum.reqTitle }" />
					</a></div>
				</td>
				<td><div class="truncate" >
					<a href="${root }/quote/read.do?no=${quote.quoteNo }&qpageNo=${quotePage.currentPage}">
						<c:out value="${quote.title }"></c:out>
					</a>
				</div></td>
				<td><div class="truncate" >${quote.price }</div></td>
				
				<c:if test="${quote.complete == '2'}">
					<td><div class="truncate" >마감</div></td>
				</c:if>
				<c:if test="${quote.complete == '1'}">
					<td><div class="truncate" >수락</div></td>
				</c:if>
				<c:if test="${quote.complete == '0'}">
					<td><div class="truncate" >대기중</div></td>
				</c:if>
			
			</tr>
		</c:forEach>
		</tbody>
	</table>

		<c:if test="${quotePage.hasQuotes() }">
			<tr>
				<td colspan="5">
					<nav aria-label="Page navigation example" style="padding-top: 40px;">
					<ul class="pagination justify-content-center">
					
					 <c:if test="${quotePage.startPage > 5}">
					 <li class="page-item">
					 	<a class="page-link" href="read.do?qpageNo=${requestPage.startPage - 5 }"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if> 
			
					<c:forEach var="pNo" begin="${quotePage.startPage }"
						end="${quotePage.endPage }">
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
				</td>

			</tr>

		</c:if>
</div>	

<div class="section">
	<h4><i class="fas fa-paw"></i>&nbsp; 나의 돌봄 이용 내역</h4>
	<table class="table table-hover">
	  <thead>
		
		<tr>
			<th><div class="truncate" >계약 번호</div></th>
			<th><div class="truncate" >요청 번호</div></th>
			<th><div class="truncate" >견적서 번호</div></th>
			<th><div class="truncate">고객아이디</div></th>
			<th><div class="truncate" >후기</div></th>
			
		</tr>
		</thead>
		
		<tbody>
		<c:if test="${contractPage.hasNoContracts() }">
			<tr>
				<td colspan="5"><div class="truncate" >게시글이 없습니다.</div></td>
			</tr>
		</c:if>

		<c:forEach var="contract" items="${contractPage.contractList }">
			<tr>
				<td>${contract.contractNum }</td>
				<td><div class="truncate" >
					<a href="${root }/request/read.do?reqNo=${contract.reqNum }&cpageNo=${contractPage.currentPage}">
						<c:out value="요청서 보기" />
					</a></div>
				</td>
				
				<td><div class="truncate" >
					<a href="${root }/quote/read.do?no=${contract.quoNum }&cpageNo=${contractPage.currentPage}">
						<c:out value="견적서 보기" />
					</a></div>
				</td>
				<td>${contract.customerId }</td>
				<td>
					<c:if test="${contract.providerReview == '0' }">
						<div class="truncate" ><a href="${root }/review/write.do?reqNum=${contract.reqNum }" 
						class="btn btn-outline-danger btn-sm"  role="button">고객 평가</a></div>
					</c:if>
					<c:if test="${contract.providerReview == '1' }">
						<div class="truncate" ><a href="${root }/review/read.do?reqNum=${contract.reqNum }" 
						class="btn btn-outline-danger btn-sm"  role="button">나의 후기</a></div>
					</c:if>
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>

		<c:if test="${contractPage.hasContracts() }">
			<tr>
				<td colspan="5">
					<nav aria-label="Page navigation example" style="padding-top: 40px;">
					<ul class="pagination justify-content-center">
					
					 <c:if test="${contractPage.startPage > 5}">
					 <li class="page-item">
					 	<a class="page-link" href="read.do?cpageNo=${contractPage.startPage - 5 }"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if> 
			
					<c:forEach var="pNo" begin="${contractPage.startPage }"
						end="${contractPage.endPage }">
						<li class="page-item"><a class="page-link" href="read.do?cpageNo=${pNo }">${pNo }</a></li>
					</c:forEach> 
					
					<c:if test="${contractPage.endPage < contractPage.totalPages }">
							<li class="page-item">
								<a class="page-link" href="read.do?cpageNo=${requestPage.startPage + 5 }"
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