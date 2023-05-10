const API_KEY = "YOUR_API_KEY";

const form = document.getElementById("search-form");
const summonerInfo = document.getElementById("summoner-info");
const summonerStats = document.getElementById("summoner-stats");

form.addEventListener("submit", async (event) => {
	event.preventDefault();
	const summonerName = event.target.elements["summoner-name"].value;
	const summonerData = await getSummonerData(summonerName);
	if (summonerData) {
		showSummonerInfo(summonerData);
		const summonerStatsData = await getSummonerStats(summonerData.id);
		showSummonerStats(summonerStatsData);
	}
});

async function getSummonerData(summonerName) {
	const url = `https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/${summonerName}?api_key=${API_KEY}`;
	const response = await fetch(url);
	if (response.ok) {
		const data = await response.json();
		return data;
	} else {
		alert("소환사 정보를 불러오는 데 실패했습니다.");
		return null;
	}
}

async function getSummonerStats(summonerId) {
	const url = `https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/${summonerId}?api_key=${API_KEY}`;
	const response = await fetch(url);
	if (response.ok) {
		const data = await response.json();
		return data;
	} else {
		alert("소환사 전적을 불러오는 데 실패했습니다.");
		return null;
	}
}

function showSummonerInfo(summonerData) {
	const { name, level, profileIconId } = summonerData;
	summonerInfo.innerHTML = `
		<h2>${name}</h2>
		<p>레벨: ${level}</p>
		<img src="http://ddragon.leagueoflegends.com/cdn/11.10.1/img/profileicon/${profileIconId}.png">
	`;
}

function showSummonerStats(summonerStatsData) {
	if (!summonerStatsData || summonerStatsData.length === 0) {
		summonerStats.innerHTML = "해당 소환사의 전적을 찾을 수 없습니다.";
		return;
	}
	summonerStats.innerHTML = "";
	for (let i = 0; i < summonerStatsData.length; i++) {
		const { queueType, tier, rank, leaguePoints, wins, losses } = summonerStatsData[i];
		summonerStats.innerHTML += `
			<div class="stat-card">
				<h2>${queueType}</h2>
				<p>${tier} ${rank} (${leaguePoints}LP)</p>
				<p>승: ${wins} 패: ${losses}</p>
			</div>
		`;
	}
}