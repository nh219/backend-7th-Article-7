<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>메인 화면</title>
</head>
<body>
	<h2>TeamLuck.gg</h2>
	<p>${auth.nickname}님</p>
	<hr />

	<form action="<c:url value='search' />" method="GET">
		<label for="summonerName">소환사 이름:</label> 
		<input type="text"	id="summonerName" name="summonerName" />
		<button type="submit">검색</button>
	</form>
	<ul>
		<li><a href="<c:url value='/register/step1' />">회원가입</a></li>
		<li><a href="<c:url value='/member/login' />">로그인</a></li>
		<li><a href="<c:url value='/member/memberUpdate' />">회원정보 변경</a></li>
		<li><a href="<c:url value='/member/logout' />">로그아웃</a></li>
	</ul>
</body>
</html>