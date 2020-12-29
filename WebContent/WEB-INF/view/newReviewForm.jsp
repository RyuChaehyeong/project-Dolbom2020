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
	width: 100px;
}
</style>

</head>
<body>
	<div id="wrapper">



		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>
		<br />
		<hr />
		<div class="container">
		<div style="padding-bottom: 50px;">
			<h2><i class="fas fa-pen-alt"></i> &nbsp;후기작성</h2>
		</div>
			<form action="${root }/review/write.do?quoNum=${param.quoNum }" method="post" >
				<c:if test="${authUser.status == '0'  }">
					<h2>${quote.provider } 돌봄이님을 평가해주세요.</h2> <br />
					
					<label for="inputScore">나의 요청: </label> 
					<a href="${root }/request/read.do?reqNo=${quote.reqSum.reqNo }" class="text_hide">${quote.reqSum.reqTitle}</a> <br />
					<label for="inputScore"> 견적서 제목: </label> 
					<a href="${root }/quote/read.do?no=${quote.quoteNo }" class="text_hide">${quote.title}</a>	
				</c:if>
				<c:if test="${authUser.status == '1'  }">
					<h2>${quote.reqSum.reqWriter } 회원님을 평가해주세요.</h2>
					<label for="inputScore">돌봄 요청: </label> 
					<a href="${root }/request/read.do?reqNo=${quote.reqSum.reqNo }" class="text_hide">${quote.reqSum.reqTitle}</a> <br />
					<label for="inputScore"> 내가 보낸 견적서: </label> 
					<a href="${root }/quote/read.do?no=${quote.quoteNo }" class="text_hide">${quote.title}</a>	
				</c:if>
				<div class="form-group">
					<label for="inputScore">평점</label> 
					<input type="number" name="score" value="${param.score }" class="form-control" id="inputScore" min="0" max="10">
				</div>
				<div class="form-group">
					<label for="inputText">코멘트</label>
					<textarea name="comment" id="inputText" cols="30" rows="10">${param.comment }</textarea>
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<c:if test="${errors.comment }"><i class="fas fa-exclamation" style="color: red;"></i> 코멘트를 입력하세요.</c:if>  
					</div>
				</div>
				
					<input type="submit" value="후기등록" 
					class="btn btn-outline-secondary btn-lg"  id="submitBtn" style="width: 170px;"/>
			
			</form>
		</div>

		<%@ include file="footer.jsp"%>
	</div>


</body>
</html>