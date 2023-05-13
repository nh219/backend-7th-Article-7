<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Teamluck.gg</title>
<link rel="stylesheet" href="WebContent/search style.css">
</head>
<body>
	<div id="all">
		<!-- 헤더 영역 -->
		<header>
			<div class="logo">
				<a href="http://Teamluck.gg.com"><img src="../WebContent/lol-logo.png" alt="Teamluck.gg"></a>
			</div>
			<div class="navi">
				<nav>
					<ul>
						<li>
							<a href="#">전적검색</a>
						</li>
						<li>
							<a href="#">커뮤니티</a>
						</li>
						<li>
							<a href="#">통합 인기글</a>
						</li>
						<li>
							<a href="#">로그아웃</a>
						</li>
					</ul>
				</nav>
			</div>
		</header>
		<!-- 대문짝 -->
		<div class="main banner">
			<img src="https://opgg-static.akamaized.net/logo/20230414083144.6eb75aa6cdf74acba088a2c108e5a061.png?image=q_auto,f_webp,w_auto&amp;v=1681892839653" alt="Teamluck.gg">
		</div>
		<!-- 좌측 배너 -->
		<div class="ADbanner left">
			<img src="banner_left.png" alt="Banner Left">
		</div>
		<!-- 콘텐츠 영역 -->
		<div class="container">
			<h1>롤 전적 검색</h1>
			<form id="search-form">
				<label for="summoner-name">소환사명:</label> <input type="text" id="summoner-name" name="summoner-name">
				<button type="submit">검색</button>
			</form>
			<div id="summoner-info"></div>
			<div id="stats-container">
				<div class="summoner-stat-card">
					<h2>레벨 및 아이디</h2>
					<p class="stat-name">소환사명</p>
					<p class="stat-value" id="summoner-name-stat">${summoner.name}</p>
					<p class="stat-name">레벨</p>
					<p class="stat-value" id="summoner-level">${summoner.summonerLevel}</p>
					<p>Profile Icon ID: ${summoner.profileIconId}</p>
				</div>
				<div class="side">
					<div class="league-stat-card">
						<h2>현재 티어</h2>
						<c:forEach var="league" items="${leagues}">
							<p class="stat-name">솔로 랭크</p>
							<p class="stat-value" id="solo-tier">${league.queueType}</p>
							<p>tier: ${league.tier}</p>
							<p>rank: ${league.rank}</p>
							<p>leaguePoints: ${league.leaguePoints}</p>
							<p class="stat-name">승률</p>
							<p>wins: ${league.wins}</p>
							<p>losses: ${league.losses}</p>
							<p class="stat-value" id="solo-winrate"></p>
						</c:forEach>
					</div>
					<div class="mostChampion-stat-card">
						<h2>모스트 챔피언별 승률 및 KDA</h2>
						<c:forEach var="entry" items="${mostPlayedChampions}">
							<c:set var="championName" value="${entry.key}" />
							<c:set var="playCount" value="${entry.value}" />
							<c:set var="winRate" value="${winRates[championName]}" />
							<p>Champion Name: ${championName}</p>
							<p>Count: ${playCount}</p>
							<p>Win Rate: ${winRate}%</p>
						</c:forEach>
					</div>
				</div>
				<div class="match">
					<div class="queueSearch">
						<ul class="ul1">
							<li class="button">
								<button value="TOTAL">전체</button>
							</li>
							<li class="button">
								<button value="SOLORANKED">솔로랭크</button>
							</li>
							<li class="button">
								<button value="FLEXRANKED">자유랭크</button>
							</li>
							<li class="button">
								<span><label class="hidden" for="queueType">큐타입</label> <select id="queueType">
										<option value="TOTAL">큐타입</option>
										<option value="NORMAL">일반 (비공개선택)</option>
										<option value="ARAM">무작위 총력전</option>
										<option value="BOT">AI 상대 대전</option>
										<option value="CLASH">격전</option>
										<option value="EVENT">이벤트 게임</option>
										<option value="URF">우르프</option>
								</select> </span>
							</li>
						</ul>
					</div>
					<div class="match-stat-card">
						<c:forEach var="myInfo" items="${myInfo}">
							<div class="myinfo">
								<div class="myChampions">
									<div class="myChampion">${myInfo.championName}</div>
								</div>
								<div class="perks">
									<div class="perk">${myInfo.perk0}</div>
								</div>
								<div class="items">
									<div class="item">${myInfo.item0}</div>
									<div class="item">${myInfo.item1}</div>
									<div class="item">${myInfo.item2}</div>
									<div class="item">${myInfo.item3}</div>
									<div class="item">${myInfo.item4}</div>
									<div class="item">${myInfo.item5}</div>
									<div class="item">${myInfo.item6}</div>
								</div>
							</div>
							<div>
								<c:forEach var="participantInfo" items="${participantInfoList}" varStatus="outerLoop">
									<c:if test="${outerLoop.index % 10 == 0}">
										<div class="upperline">
									</c:if>
									<c:if test="${outerLoop.index % 5 == 0}">
										<div class="line">
									</c:if>
									<div class="participant">${participantInfo.summonerName}${participantInfo.championName}</div>
									<c:if test="${(outerLoop.index + 1) % 5 == 0 || loop.last}">
							</div>
							</c:if>
							<c:if test="${(outerLoop.index + 1) % 10 == 0 || loop.last}">
					</div>
					</c:if>
					</c:forEach>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="ADbanner right">
		<img src="banner_right.png" alt="Banner Right">
	</div>
	</div>
</body>
</html>
