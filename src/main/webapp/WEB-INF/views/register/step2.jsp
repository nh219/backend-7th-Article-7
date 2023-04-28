<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<title>회원가입: Step2</title>
	<style>
		label {
			display: inline-block;
			width: 120px;
		}
	</style>
	<script>
	function submitHandler() {
		var userName = document.querySelector('#nickname').value;
		var password = document.querySelector('#password');
		var confirmPassword = document.querySelector('#confirmPassword');
		// 뒤에 value를 붙여주면 사용자가 입력한 값이 됨.
		
		if(userName.length >= 2 && password.value.length >= 4
				&& password.value == confirmPassword.value) {
			alert("회원가입 처리를 수행합니다.");
		}
		else {
			alert("올바른 회원 정보를 입력하세요.");
			
			password.value = '';
			confirmPassword.value = '';
			
			return false;
		}
		
		// 원래 이벤트 처리 --> 해당 이벤트에 해당하는 요청을 서버에 보내는데
		// 이때 false를 반환하면 이벤트 처리를 한 후 일어나는 것들을 수행하지 않음.
	}
	
	function init() {
		var registerForm = document.querySelector('#registerForm');
		registerForm.onsubmit = submitHandler;
	}
	
	window.onload = init;
	</script>
</head>

<body>

	<h1>회원가입</h1>
	<hr/>
	
	<h3>회원 정보 입력</h3>
	
	<form:form action="step3" modelAttribute="formData" id="registerForm">
	<fieldset>
		<legend>회원 정보</legend>
		
		<label for="email">이메일:</label>
		<form:input type="email" path="email" required="required" /><br/>
		
		<label for="nickname">닉네임:</label>
		<form:input path="nickname" required="required" /><br/>
		
		<label for="password">비밀번호:</label>
		<form:password path="password" required ="required"/><br/>
		
		<label for="confirmPassword">비밀번호 확인:</label>
		<form:password path="confirmPassword" required ="required"/><br/>
		
		<input type="submit" value="회원가입" />	
	</fieldset>	
	</form:form>
	
	<p><font color="red">${msg}</font></p>

</body>
</html>