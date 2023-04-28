function submitHandler() {
		var password = document.querySelector('#password');
		var newPassword = document.querySelector('#newPassword');
		var confirmPassword = document.querySelector('#confirmPassword');
		
		if(password.value.length >= 4 && newPassword.value.length >= 4
				&& newPassword.value == confirmPassword.value) {
			alert("비밀번호 변경 처리를 수행합니다.");
		}
		else {
			alert("올바른 비밀번호 정보를 입력하세요.");
			
			password.value = '';
			newPassword.value = '';
			confirmPassword.value = '';
			
			return false;
		}
	}
	
	function init() {
		var registerForm = document.querySelector('#changePasswd');
		registerForm.onsubmit = submitHandler;
	}
	
	window.onload = init;