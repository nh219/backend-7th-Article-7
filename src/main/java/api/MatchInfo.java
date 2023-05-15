package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Component
public class MatchInfo {
	static String apiKey = API_KEY.KEY;

	// MatchId 추출
	public static List<String> matchIds(SummonerDO summonerDO) throws IOException {
		String urlString = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summonerDO.getPuuid() + "/ids?start=0&count=30&api_key=" + apiKey;
		String content = RiotGamesAPIExample.getHttpContent(urlString);

		Gson gson = new Gson();
		JsonArray matchIdsArray = gson.fromJson(content.toString(), JsonArray.class);

		List<String> matchIds = new ArrayList<>();
		for (JsonElement element : matchIdsArray) {
			String matchId = element.getAsString();
			matchIds.add(matchId);
		}
		return matchIds;
	}

	// 추출한 MatchId로 해당 게임 세부 내용 검색
	public static JsonObject matchInfo(String matchId) throws IOException {
		String urlString = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;
		String content = RiotGamesAPIExample.getHttpContent(urlString);

		Gson gson = new Gson();
		JsonObject matchObject = gson.fromJson(content.toString(), JsonObject.class);

		return matchObject;
	}
}

/*
 * System.out.println("Match ID: " + matchId);
 * System.out.println("Data Version: " +
 * matchObject.get("metadata").getAsJsonObject().get("dataVersion").getAsString(
 * )); System.out.println("Game Start Time Stamp: "+
 * matchObject.get("info").getAsJsonObject().get("gameStartTimestamp").
 * getAsString()); long timestamp =
 * matchObject.get("info").getAsJsonObject().get("gameStartTimestamp").getAsLong
 * (); Date date = new Date(timestamp); System.out.println("Game Start Time: " +
 * date); System.out.println("Game Duration: " +
 * matchObject.get("info").getAsJsonObject().get("gameDuration").getAsInt());
 * int gameDuration =
 * matchObject.get("info").getAsJsonObject().get("gameDuration").getAsInt(); int
 * minutes = gameDuration / 60; int seconds = gameDuration % 60;
 * System.out.printf("Game Real Time: %d분 %d초\n", minutes, seconds);
 * System.out.println("Game Type: " +
 * matchObject.get("info").getAsJsonObject().get("gameType").getAsString());
 * System.out.println("Game Mode: " +
 * matchObject.get("info").getAsJsonObject().get("gameMode").getAsString());
 * System.out.println("Queue Id: " +
 * matchObject.get("info").getAsJsonObject().get("queueId").getAsInt()); String
 * gameMode =
 * getGameMode.getGameMode(matchObject.get("info").getAsJsonObject().get(
 * "queueId").getAsInt()); System.out.println("게임 모드: " + gameMode);
 * System.out.println("Map ID: " +
 * matchObject.get("info").getAsJsonObject().get("mapId").getAsInt()); String
 * mapId =
 * getMap.getMap(matchObject.get("info").getAsJsonObject().get("mapId").getAsInt
 * ()); System.out.println("맵: " + mapId);
 * 
 * JsonArray teams =
 * matchObject.get("info").getAsJsonObject().get("teams").getAsJsonArray();
 * boolean isRedTeamWin =
 * teams.get(0).getAsJsonObject().get("win").getAsBoolean(); if (isRedTeamWin) {
 * System.out.println("\nRed Team Win!"); } else {
 * System.out.println("\nBlue Team Win!"); }
 * 
 * // 매치 정보
 * 
 * for (int i = 0; i < participants.size(); i++) { JsonObject participant =
 * participants.get(i).getAsJsonObject(); int teamId =
 * participant.get("teamId").getAsInt(); String summonerName1 =
 * participant.get("summonerName").getAsString(); String championName =
 * participant.get("championName").getAsString();
 * 
 * JsonArray perk =
 * matchObject.get("info").getAsJsonObject().get("participants").getAsJsonArray(
 * ).get(i) .getAsJsonObject().get("perks").getAsJsonObject().get("styles").
 * getAsJsonArray();
 * 
 * JsonObject itemObject = new JsonObject(); itemObject.addProperty("item0",
 * participant.get("item0").getAsInt()); itemObject.addProperty("item1",
 * participant.get("item1").getAsInt()); itemObject.addProperty("item2",
 * participant.get("item2").getAsInt()); itemObject.addProperty("item3",
 * participant.get("item3").getAsInt()); itemObject.addProperty("item4",
 * participant.get("item4").getAsInt()); itemObject.addProperty("item5",
 * participant.get("item5").getAsInt()); itemObject.addProperty("item6",
 * participant.get("item6").getAsInt());
 * 
 * if (teamId == 100) { JsonObject participantObject = new JsonObject();
 * participantObject.addProperty("championName", championName);
 * participantObject.addProperty("kills", participant.get("kills").getAsInt());
 * participantObject.addProperty("deaths",
 * participant.get("deaths").getAsInt());
 * participantObject.addProperty("assists",
 * participant.get("assists").getAsInt()); participantObject.add("perks", perk);
 * participantObject.add("items", itemObject); redTeam.add(summonerName1,
 * participantObject); } else if (teamId == 200) { JsonObject participantObject
 * = new JsonObject(); participantObject.addProperty("championName",
 * championName); participantObject.addProperty("kills",
 * participant.get("kills").getAsInt()); participantObject.addProperty("deaths",
 * participant.get("deaths").getAsInt());
 * participantObject.addProperty("assists",
 * participant.get("assists").getAsInt()); participantObject.add("perks", perk);
 * participantObject.add("items", itemObject); blueTeam.add(summonerName1,
 * participantObject); } }
 * 
 * System.out.println("\n--------Red Team--------"); for (String summonerName1 :
 * redTeam.keySet()) { JsonObject participantObject =
 * redTeam.get(summonerName1).getAsJsonObject(); String championName =
 * participantObject.get("championName").getAsString(); int kills =
 * participantObject.get("kills").getAsInt(); int deaths =
 * participantObject.get("deaths").getAsInt(); int assists =
 * participantObject.get("assists").getAsInt();
 * System.out.printf("- %s (%s) KDA: %d/%d/%d\n", summonerName1, championName,
 * kills, deaths, assists);
 * 
 * // 해당 참가자의 아이템 출력 JsonObject itemObject =
 * participantObject.get("items").getAsJsonObject(); for (int i = 0; i <= 6;
 * i++) { int itemId = itemObject.get("item" + i).getAsInt(); String itemName =
 * getItemMap.getItemMap().get(String.valueOf(itemId));
 * System.out.println("  - Item " + itemId + " : " + itemName); } JsonArray
 * perkArray = participantObject.get("perks").getAsJsonArray(); JsonObject
 * perkObject = perkArray.get(0).getAsJsonObject(); JsonArray
 * perkSelectionsArray = perkObject.get("selections").getAsJsonArray();
 * JsonObject perkSubObject = perkArray.get(1).getAsJsonObject(); JsonArray
 * perkSubSelectionsArray = perkSubObject.get("selections").getAsJsonArray();
 * 
 * for (int i = 0; i < perkSelectionsArray.size(); i++) { JsonObject
 * perkSelectionObject = perkSelectionsArray.get(i).getAsJsonObject(); long
 * perkValue = perkSelectionObject.get("perk").getAsLong(); String perkName =
 * getPerkMap.getPerkMap().get(perkValue); System.out.println("  - Perk " + (i +
 * 1) + " : " + perkName); }
 * 
 * for (int i = 0; i < perkSubSelectionsArray.size(); i++) { JsonObject
 * perkSubSelectionObject = perkSubSelectionsArray.get(i).getAsJsonObject();
 * long perkValue = perkSubSelectionObject.get("perk").getAsLong(); String
 * perkName = getPerkMap.getPerkMap().get(perkValue);
 * System.out.println("  - Sub Perk " + (i + 1) + " : " + perkName); }
 * 
 * }
 * 
 * System.out.println("\n--------Blue Team--------"); for (String summonerName1
 * : blueTeam.keySet()) { JsonObject participantObject =
 * blueTeam.get(summonerName1).getAsJsonObject(); String championName =
 * participantObject.get("championName").getAsString(); int kills =
 * participantObject.get("kills").getAsInt(); int deaths =
 * participantObject.get("deaths").getAsInt(); int assists =
 * participantObject.get("assists").getAsInt();
 * System.out.printf("- %s (%s) KDA: %d/%d/%d\n", summonerName1, championName,
 * kills, deaths, assists); JsonObject itemObject =
 * participantObject.get("items").getAsJsonObject(); for (int i = 0; i <= 6;
 * i++) { int itemId = itemObject.get("item" + i).getAsInt(); String itemName =
 * getItemMap.getItemMap().get(String.valueOf(itemId));
 * System.out.println("  - Item " + itemId + " : " + itemName); } JsonArray
 * perkArray = participantObject.get("perks").getAsJsonArray(); JsonObject
 * perkObject = perkArray.get(0).getAsJsonObject(); JsonArray
 * perkSelectionsArray = perkObject.get("selections").getAsJsonArray();
 * JsonObject perkSubObject = perkArray.get(1).getAsJsonObject(); JsonArray
 * perkSubSelectionsArray = perkSubObject.get("selections").getAsJsonArray();
 * 
 * for (int i = 0; i < perkSelectionsArray.size(); i++) { JsonObject
 * perkSelectionObject = perkSelectionsArray.get(i).getAsJsonObject(); long
 * perkValue = perkSelectionObject.get("perk").getAsLong(); String perkName =
 * getPerkMap.getPerkMap().get(perkValue); System.out.println("  - Perk " + (i +
 * 1) + " : " + perkName); }
 * 
 * for (int i = 0; i < perkSubSelectionsArray.size(); i++) { JsonObject
 * perkSubSelectionObject = perkSubSelectionsArray.get(i).getAsJsonObject();
 * long perkValue = perkSubSelectionObject.get("perk").getAsLong(); String
 * perkName = getPerkMap.getPerkMap().get(perkValue);
 * System.out.println("  - Sub Perk " + (i + 1) + " : " + perkName); } }
 */
