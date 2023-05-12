<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Summoner Info</title>
</head>
<body>
	<h1>Summoner Info</h1>
	<ul>
		<li>Name: ${summoner.name}</li>
		<li>Summoner Level: ${summoner.summonerLevel}</li>
		<li>Profile Icon ID: ${summoner.profileIconId}</li>
	</ul>
	<h2>Leagues</h2>
	<c:forEach var="league" items="${leagues}">
		<p>leagueId: ${league.leagueId}</p>
		<p>Queue Type: ${league.queueType}</p>
		<p>tier: ${league.tier}</p>
		<p>rank: ${league.rank}</p>
		<p>leaguePoints: ${league.leaguePoints}</p>
		<p>wins: ${league.wins}</p>
		<p>losses: ${league.losses}</p>
	</c:forEach>

	<h2>Most Champions</h2>
	<c:forEach var="entry" items="${mostPlayedChampions}">
		<c:set var="championName" value="${entry.key}" />
		<c:set var="playCount" value="${entry.value}" />
		<c:set var="winRate" value="${winRates[championName]}" />
		<p>Champion Name: ${championName}</p>
		<p>Count: ${playCount}</p>
		<p>Win Rate: ${winRate}%</p>
	</c:forEach>

	<h2>Match IDs</h2>
	<table>
		<tr>
			<th>Match ID</th>
			<th>Match Duration</th>
		</tr>
		<c:forEach var="matchId" items="${matchIds}">
			<c:set var="matchObject" value="${matchObjects[matchId]}" />
			<tr>
				<td>${matchId}</td>
				<c:set var="durationSeconds"
					value="${matchObject.get('info').getAsJsonObject().get('gameDuration').getAsLong()}" />
				<c:set var="minutes" value="${(durationSeconds / 60)}" />
				<c:set var="seconds" value="${(durationSeconds % 60)}" />
				<fmt:formatNumber value="${minutes}" pattern="00"
					var="formattedMinutes" />
				<fmt:formatNumber value="${seconds}" pattern="00"
					var="formattedSeconds" />
				<td>${formattedMinutes}:${formattedSeconds}</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: left;">Blue Team</td>
			</tr>
			<tr>
				<th>summonerName</th>
				<th>Champion Name</th>
				<th>summoner1Id</th>
				<th>summoner2Id</th>
				<th>Kill</th>
				<th>Death</th>
				<th>Assist</th>
			</tr>
			<c:set var="participantArray"
				value="${matchObject.get('info').getAsJsonObject().get('participants').getAsJsonArray()}" />
			<c:forEach begin="0" end="${participantArray.size() - 6}"
				varStatus="loop">
				<c:set var="participant" value="${participantArray.get(loop.index)}" />
				<tr>
					<td>${participant.get("summonerName").getAsString()}</td>
					<td>${participant.get("championName").getAsString()}</td>
					<td>${participant.get("summoner1Id").getAsInt()}</td>
					<td>${participant.get("summoner2Id").getAsInt()}</td>
					<td>${participant.get("kills").getAsInt()}</td>
					<td>${participant.get("deaths").getAsInt()}</td>
					<td>${participant.get("assists").getAsInt()}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2" style="text-align: left;">Red Team</td>
			</tr>
			<tr>
				<th>summonerName</th>
				<th>Champion Name</th>
				<th>summoner1Id</th>
				<th>summoner2Id</th>
				<th>Kill</th>
				<th>Death</th>
				<th>Assist</th>
			</tr>
			<c:set var="participantArray"
				value="${matchObject.get('info').getAsJsonObject().get('participants').getAsJsonArray()}" />
			<c:forEach begin="5" end="${participantArray.size() - 1}"
				varStatus="loop">
				<c:set var="participant" value="${participantArray.get(loop.index)}" />
				<tr>
					<td>${participant.get("summonerName").getAsString()}</td>
					<td>${participant.get("championName").getAsString()}</td>
					<td>${participant.get("summoner1Id").getAsInt()}</td>
					<td>${participant.get("summoner2Id").getAsInt()}</td>
					<td>${participant.get("kills").getAsInt()}</td>
					<td>${participant.get("deaths").getAsInt()}</td>
					<td>${participant.get("assists").getAsInt()}</td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
</body>
</html>


