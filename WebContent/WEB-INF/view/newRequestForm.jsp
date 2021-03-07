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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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
	width: 85%;
	margin-left: 40px;
	font-size: 20px;
	padding-bottom: 15px;
}
.null_check {
	color: red;
	font-size: 11px;

}
</style>

<script>
$(function() {
	$("#submitBtn").click(function(){
		var c = confirm("등록하시겠습니까?");
		if (c) {
			location.href="${root }/request/write.do";
		}
	});
});
</script>

<script>
	   //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	   function sample4_execDaumPostcode() {
	       new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수
	
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById("roadAddress").value = roadAddr;         
	                
	             
	                var guideTextBox = document.getElementById("guide");
	                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';
	
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }
	            }
	        }).open();
	    }
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
					<div class="d-grid gap-2 d-md-flex justify-content-md-end  null_check" >
					<c:if test="${errors.title }"><i class="fas fa-exclamation" style="color: red;"></i> 제목을 입력하세요.</c:if>
					</div>
				</div>
			
				<div class="form-group">
					<label for="inputAnimal">돌봄동물</label>
					<select name="animal"class="form-control">
						<c:if test="${empty param.animal }">
							<option selected>돌봄 동물을 선택하세요.</option>
						</c:if>					
						<c:if test="${not empty param.animal }">
							<option selected>${param.animal }</option>
						</c:if>
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
					<div class="d-grid gap-2 d-md-flex justify-content-md-end  null_check">
					<c:if test="${errors.animal }"><i class="fas fa-exclamation" style="color: red;"></i> 돌봄동물을 선택하세요.</c:if> 					
					</div>
				</div>

				<div class="form-group">
					<label for="postcode">주소</label><br>
					<input type="text" name="postcode" id="postcode" placeholder="우편번호" value="${param.postcode }">
					<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
					<input style="width: 320px;" type="text" name="roadAddress" value="${param.roadAddress }" id="roadAddress" placeholder="도로명주소">
					<span id="guide" style="color:#999;display:none"></span>
					<div class="null_check">
						<c:if test="${not empty errors.postcode }">
							우편번호를 선택하세요.
						</c:if>
						
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
					<textarea name="info" id="info" cols="86" rows="10">${param.info }</textarea>
					  <div class="d-grid gap-2 d-md-flex justify-content-md-end  null_check ">
					  <c:if test="${errors.info }"><i class="fas fa-exclamation" style="color: red;"></i> 특이사항을 입력하세요.</c:if>
					</div>
				</div>
				
				
		
		<div style="margin-right: 1020px; margin-left:40px; margin-top: 40px; width: 120px;" >
				<button id="submitBtn" type="submit" class="btn btn-outline-secondary btn-lg">등록하기</button>
		</div>

			</form>
		</div>

		<%@ include file="footer.jsp"%>
	</div>


</body>
</html>