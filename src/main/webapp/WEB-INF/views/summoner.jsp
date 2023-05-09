<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<title>소환사 정보</title>
</head>

<body>

	<h2>${summonerDO.name}님의 정보</h2>
	<p>소환사 레벨: ${summonerDO.summonerLevel}</p>
	<p>최근 갱신 일자: ${summonerDO.revisionDate}</p>
	<p>프로필 아이콘 ID: ${summonerDO.profileIconId}</p>

</body>
</html>
