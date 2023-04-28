<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<title>회원가입: Step1</title>
</head>

<body>

	<h1>회원가입</h1>
	<hr />
	
	<h3>약관</h3>
	<p>약관 내용 ......</p>
	
	<h3>개인정보 수집 이용 동의서</h3>
	<p>동의서 내용 ......</p>
	
	<form action="step2" method="POST">
	<fieldset>
		<label>약관 및 동의 확인</label>
		<input type="checkbox" name="agree" id="agree" value="true"/>
		<input type="submit" value="다음 단계"/>
	</fieldset>
	</form>

</body>
</html>