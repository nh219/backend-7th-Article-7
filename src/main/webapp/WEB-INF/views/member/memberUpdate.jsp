<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<title>회원 정보 변경</title>
	<style>
		label {
			display: inline-block;
			width: 150px;
		}
	</style>
	<script src="/mySpringWeb/res/script/changePasswd.js" charset="UTF-8">
	<!-- js를 비롯한 이미지, css 파일과 같은 정적인 처리는 res에 저장 및 configureDefaultServletHandling가 처리-->
	<!--<script src="<c:url value='/res/script/changePasswd.js' />" charset="UTF-8">-->
	</script>
	
	<script>
	function removeMember() {
		if (window.confirm("회원 탈퇴를 진행하시겠습니까?")) {
			location.href="http://localhost:8080/finalProject/member/memberWithdrawalProcess";
		}
	}
	
	function fn_nameChk(){
	    $.ajax({
	      url : "member/nicknameDoubleCheck",
	      type : "post",
	      dataType : "json",
	      data : {"nickname" : $("#nickname").val()},
	      success : function(data){
	        if(data == 1){
	          alert("중복된 닉네임입니다.");
	        }else if(data == 0){
	          $("#nameChk").attr("value", "Y");
	          alert("사용가능한 닉네임입니다.");
	        }
	      }
	    })
	  }
	</script>
</head>

<body>

	<h1>회원 정보 변경</h1>
	<hr/>

	<form action="memberUpdateProcess" method="POST" id="memberUpdate">
	<fieldset>
		<label for="email">이메일</label>
		<input type="email" id="email" value="${auth.email}" disabled/><br/>
		
		<div class="form-group has-feedback">
		<label class="control-label" for="nickname">닉네임</label>
		<input class="form-control" type="text" id="nickname" name="nickname" value="${auth.nickname}" />
		<button class="nameChk" type="button" id="nameChk" onclick="fn_nameChk();" value="N">중복확인</button><br/>
		</div>
		
		<label for="password">현재 비밀번호</label>
		<input type="password" name="password" id="password" required/><br/>
		
		<label for="newPassword">변경 비밀번호</label>
		<input type="password" name="newPassword" id="newPassword" required/><br/>
		
		<label for="confirmPassword">변경 비밀번호 확인</label>
		<input type="password" id="confirmPassword" required/><br/>
			<!-- 변경 비밀번호는 name 속성x -> 아무리 값을 입력해도 서버에 전달x,
				 사용자 입력 검증을 서버가 아닌 브라우저에서 하기 때문! -->
		<input type="hidden" name="email" value="${auth.email}" />
		<input type="submit" value="회원정보 변경" />	
		
	</fieldset>	
	</form>
	
	<button type="button" onclick="removeMember();"> 회원탈퇴 </button>
	
	<p><font color="red">${changePasswdMsg}</font></p>

</body>
</html>