<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<title>회원 가입 step3</title>
</head>

<body>

	<h1>회원가입</h1>
	<hr />
	
	<h3>회원 가입 완료</h3>
	<p>${formData.nickname} 회원님의 가입을 진심으로 환영합니다 ... </p>
	
	<a href="<c:url value='/main' />">[ 메인 화면으로 이동 ]</a>

</body>
</html>