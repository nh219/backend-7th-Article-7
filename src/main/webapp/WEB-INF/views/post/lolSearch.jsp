<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>전적 검색</title>
	<link rel="stylesheet" href="../WebContent/search style.css">
</head>
<body>
	<!-- 헤더 영역 -->
	<header>
		<c:url var="mainUrl" value="/main" />
		<div class="logo">
		    <a href="${mainUrl}"><img src="../WebContent/lol-logo.png" alt="Teamluck.gg"></a>
		</div>

		<div class="navi">
			<nav>
				<ul>
					<li><a href="<c:url value='/post/lolSearch' />">전적검색</a></li>
					<li><a href="<c:url value='/post/community' />">커뮤니티</a></li>
					<li><a href="<c:url value='/post/postCrawl' />">통합 인기글</a></li>
					
					<c:if test="${auth == null }">
					<li><a href="<c:url value='/member/login' />">로그인</a></li>
					<li><a href="<c:url value='/register/step1' />">회원가입</a></li>
					</c:if>
					
					<c:if test="${auth != null }">
					${auth.nickname}<li><a href="<c:url value='/member/logout' />">로그아웃</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</header>

	<!-- 메인 영역 -->
	<main>
		<div class="main section">
			
			<!-- 대문짝 -->
			<div class="main banner">
				<img src="../WebContent/teamluckgglogo2.png" alt="Teamluck.gg">
			</div>

			<!-- 하단 영역 -->
			<div class="ADbanner">

				<!-- 좌측 배너 -->
				<div class="ADbanner left">
				    <a href="https://lafudgestore.com/" target="_blank">
				        <img src="../WebContent/banner1.PNG" alt="Banner Left">
				    </a>
				</div>

				<!-- 콘텐츠 영역 -->
                <div class="container">
                    <h1>롤 전적 검색</h1>
                    <form id="search-form">
                        <label for="summoner-name">소환사명:</label>
                        <input type="text" id="summoner-name" name="summoner-name">
                        <button type="submit">검색</button>
                    </form>
                    <div id="summoner-info"></div>
		            <div id="stats-container">
			            <div class="stat-card">
				            <h2>레벨 및 아이디</h2>
				            <p class="stat-name">소환사명</p>
				            <p class="stat-value" id="summoner-name-stat"></p>
				            <p class="stat-name">레벨</p>
				            <p class="stat-value" id="summoner-level"></p>
			            </div>
			             <div class="stat-card">
				            <h2>현재 티어</h2>
				            <p class="stat-name">솔로 랭크</p>
				            <p class="stat-value" id="solo-tier"></p>
				            <p class="stat-name">승률</p>
				            <p class="stat-value" id="solo-winrate"></p>
			            </div>
                        <div class="stat-card">
				            <h2>최근 승률</h2>
				            <div id="recent-winrate"></div>
			            </div>
			             <div class="stat-card">
				            <h2>최근 전적</h2>
				            <div id="match-history"></div>
			            </div>
                        <div class="stat-card">
				            <h2>챔피언별 승률 및 KDA</h2>
				            <div id="champion-stats"></div>
			            </div>
		            </div>  
                </div>
                <script src="app.js"></script>

				<!-- 우측 배너 -->
				<div class="ADbanner right">
				    <a href="https://fifaonline4.nexon.com/main/index" target="_blank">
				        <img src="../WebContent/banner2.PNG" alt="Banner Right">
				    </a>
				</div>
			</div>
		</div>
	</main>
</body>

</html>

