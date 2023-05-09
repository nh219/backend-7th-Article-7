<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Match Info</title>
</head>
<body>
    <h1>Match Info</h1>
    <form action="/mySpringWeb/saveMatchInfo" method="get">
        <label>Match ID: </label>
        <input type="text" name="matchId"><br>
        <button type="submit">Save</button>
    </form>
    <table>
        <thead>
        <tr>
            <th>Match ID</th>
            <th>Game Duration</th>
            <th>Participants</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${matchInfo.matchId}</td>
            <td>${matchInfo.gameDuration}</td>
            <td>
                <ul>
                    <c:forEach var="participant" items="${matchInfo.participants}">
                        <li>Participant ID: ${participant.participantId}, Champion ID: ${participant.championId}, Team ID: ${participant.teamId}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        </tbody>
    </table>
</body>
</html>
