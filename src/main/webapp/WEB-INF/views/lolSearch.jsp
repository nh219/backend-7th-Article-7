<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Teamluck.gg</title>
	<link rel="stylesheet" href="WebContent/search style.css">
</head>
<body>
	<!-- 헤더 영역 -->
	<header>
		<div class="logo">
			<a href="http://Teamluck.gg.com"><img src="../WebContent/lol-logo.png" alt="Teamluck.gg"></a>
		</div>

		<div class="navi">
			<nav>
				<ul>
					<li><a href="#">전적검색</a></li>
					<li><a href="#">커뮤니티</a></li>
					<li><a href="#">통합 인기글</a></li>
					<li><a href="#">로그아웃</a></li>
				</ul>
			</nav>
		</div>
	</header>

	<!-- 메인 영역 -->
	<main>
		<div class="main section">
			
			<!-- 대문짝 -->
			<div class="main banner">
				<img src="https://i.ibb.co/fDR0YnX/teamluckgglogo2.png" alt="Teamluck.gg">
			</div>

			<!-- 하단 영역 -->
			<div class="ADbanner">

				<!-- 좌측 배너 -->
				<div class="ADbanner left">
					<img src="banner_left.png" alt="Banner Left">
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

				<div class="ADbanner right">
					<img src="banner_right.png" alt="Banner Right">
				</div>
			</div>
		</div>
	</main>
</body>
</html>

