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
	margin-top: 100px;
	margin-bottom: 100px;

}

table, td, th {
	text-align: center;
}

table {
	width: 100%;
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

</style>
</head>
<body>
<div id="wrapper">



		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>

		<div class="container ">
		<div style="padding-bottom: 50px;">
		<c:if test="${member.status == '1' }">
			<h2><i class="fas fa-paw"></i>&nbsp;${param.target } 돌봄이의 리뷰목록</h2>
		</c:if>
		<c:if test="${member.status == '0' }">
			<h2><i class="fas fa-paw"></i> 돌봄이들이 평가한 &nbsp;${param.target } 회원님</h2>
		</c:if>
		</div>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col" width="30%"><div class="truncate" >평가점수</div></th>
		      <th scope="col"  width="70%"><div class="truncate" >코멘트</div></th>
		    </tr>
		  </thead>
		  <tbody>
		  		<c:if test="${reviewPage.hasNoReview() }">
					<tr>
						<td colspan="2"><div class="truncate" >리뷰가 없습니다.</div></td>
					</tr>
				</c:if>
				
				
			<c:forEach var="review" items="${reviewPage.reviewList }">
				    <tr>
				      <td><div class="truncate" >${review.score }/10</div></td>
				      <td><div class="truncate" >${review.comment }</div></td>
				    </tr>
			</c:forEach>
					<tr>
						<td><div class="truncate"> 평균 평가점수</div></td>
						 <td><div class="truncate" >
								 ${member.score }/10							
						</div></td>
						 
					</tr>
		  </tbody>
		</table>
		
			<c:if test="${reviewPage.hasReview() }">
						<tr>
							<td colspan="3">
								<nav aria-label="Page navigation example" style="padding-top: 40px;">
								<ul class="pagination justify-content-center">
								
								 <c:if test="${reviewPage.startPage > 5}">
								 <li class="page-item">
								 	<a class="page-link" href="read.do?pageNo=${reviewPage.startPage - 5 }"
											aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									</a></li>
								</c:if> 
						
								<c:forEach var="pNo" begin="${reviewPage.startPage }"
									end="${reviewPage.endPage }">
									<li class="page-item"><a class="page-link" href="read.do?pageNo=${pNo }">${pNo }</a></li>
								</c:forEach> 
								
								<c:if test="${reviewPage.endPage < reviewPage.totalPages }">
										<li class="page-item">
											<a class="page-link" href="read.do?pageNo=${reviewPage.startPage + 5 }"
											aria-label="Next"> <span aria-hidden="true">&raquo;</span>
											</a>
										</li>
								</c:if>
								</ul>
								</nav>
							</td>
			
						</tr>
			
			</c:if>
			
		<div class="d-grid gap-2 d-md-flex justify-content-md-start" >
		<a href="${root }/mypage/list.do?reqNo=${review.reqNo }" class="btn btn-outline-secondary" style="margin-top: 20px"  role="button">
		견적서 목록</a>
		</div>
	</div>
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>