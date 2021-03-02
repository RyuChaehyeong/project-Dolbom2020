<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
				                document.getElementById("jibunAddress").value = data.jibunAddress;
				                
				                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
				                if(roadAddr !== ''){
				                    document.getElementById("extraAddress").value = extraRoadAddr;
				                } else {
				                    document.getElementById("extraAddress").value = '';
				                }
				
				                var guideTextBox = document.getElementById("guide");
				                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
				                if(data.autoRoadAddress) {
				                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
				                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
				                    guideTextBox.style.display = 'block';
				
				                } else if(data.autoJibunAddress) {
				                    var expJibunAddr = data.autoJibunAddress;
				                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
				                    guideTextBox.style.display = 'block';
				                } else {
				                    guideTextBox.innerHTML = '';
				                    guideTextBox.style.display = 'none';
				                }
				            }
				        }).open();
				    }
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
	width: 80%;
	padding: 100px;
	margin-top: 100px;
	margin-bottom: 100px;
	border-top: 1px solid #93ae75; 
	border-bottom: 1px solid #93ae75;
	
	
}
.form-group {
	width: 70%;
	margin-left: 40px;
	font-size: 20px;
	padding-bottom: 15px;
}

.null_check {
	color: red;
	font-size: 11px;

}

</style>
</head>
<body>
	<div id="wrapper">



		<%@ include file="header.jsp"%>
		<%@ include file="navbar.jsp"%>

		<div class="container" >
			<form action="join.do" method="post">
				<div style="margin-bottom: 50px; margin-left: 30px;">
					<h2><i class="fas fa-pen-alt"></i> &nbsp;회원가입</h2>
				</div>
				<div class="form-group">
					<label for="inputName">이름</label> <input type="text" name="name"
						value="${param.name}" class="form-control" id="inputName">
					<div class="null_check">
						<c:if test="${not empty errors.name }">
							이름을 입력하세요.
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label for="inputId">아이디</label> <input type="text" name="id"
						value="${param.id}" class="form-control" id="inputId">
					<div class="null_check">
						<c:if test="${not empty errors.id }">
							아이디를 입력하세요.
						</c:if>
						<c:if test="${not empty errors.duplicateId }">
							이미 존재하는 아이디입니다.
						</c:if>
					</div>
				
				</div>
				<div class="form-group">
					<label for="inputPwd">비밀번호</label> <input type="password"
						value="${param.password}" name="password" class="form-control" id="inputPwd">
					<div class="null_check">
						<c:if test="${not empty errors.password }">
							비밀번호를 입력하세요.
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label for="inputconfirmPwd">비밀번호 확인</label> <input type="password"
						value="${param.confirmPwd}" name="confirmPwd" class="form-control" id="inputconfirmPwd">
					<div class="null_check">
						<c:if test="${not empty errors.confirmPwd }">
							비밀번호 확인을 입력하세요.
						</c:if>
						<c:if test="${not empty errors.notMatch }">
							비밀번호가 일치하지 않습니다.
						</c:if>
					</div>
				</div>

				
				<div class="form-group">
					<label for="postcode">주소</label><br>
					<input type="text" name="postcode" id="postcode" placeholder="우편번호">
					<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" name="roadAddress" id="roadAddress" placeholder="도로명주소">
					<span id="guide" style="color:#999;display:none"></span>
					<input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소">
					<div class="null_check">
						<c:if test="${not empty errors.postcode }">
							우편번호를 선택하세요.
						</c:if>
						<c:if test="${not empty errors.detailAddress }">
							상세 주소를 입력하세요.
						</c:if>
					</div>
				</div>
				
				
				
				<div class="form-group">
					<label for="inputEmail">Email</label> <input type="text"
						value="${param.email}" name="email" class="form-control" id="inputEmail">
					<div class="null_check">
						<c:if test="${not empty errors.email }">
							이메일을 입력하세요.
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPhone">휴대폰 번호</label> <input type="text"
						value="${param.phone}" name="phone" class="form-control" id="inputPhone" placeholder="010-0000-0000 형식으로 입력해주세요.">
					<div class="null_check">
						<c:if test="${not empty errors.phone }">
							휴대폰 번호를 입력하세요.
						</c:if>
					</div>
				</div>



				<div class="form-group">
					<label for="inputAnimal">돌봄동물</label> <select name="animal"
						class="form-control">
						<option selected>돌봄동물을 선택하세요.</option>
						<option value="dog">강아지</option>
						<option value="cat">고양이</option>
						<option value="bird">새</option>
						<option value="rabbit">토끼</option>
						<option value="fish">물고기</option>
						<option value="hamster">햄스터</option>
						<option value="turtle">거북이</option>
						<option value="hedgehog">고슴도치</option>
						<option value="reptile">파충류</option>
						<option value="amphibian">양서류</option>
					</select>
				</div>

				<div class="form-group">
					<label for="statusRadio">회원종류</label>
					<div class="form-group col-md-4" id="statusRadio">
						<input type="radio" id="provider" name="status" value="0" checked="checked">
						<label for="provider">고객회원</label><br> 
						<input type="radio" id="customer" name="status" value="1"> 
						<label for="customer">돌봄이회원</label><br>

					</div>
				</div>

				<div style="margin-right: 1020px; margin-top:80px; margin-left: 40px; width: 110px;">
				<button id=submitBtn type="submit" class="btn btn-outline-danger btn-lg">회원가입</button>
				</div>

			</form>
		</div>

		<%@ include file="footer.jsp"%>
	</div>


</body>
</html>