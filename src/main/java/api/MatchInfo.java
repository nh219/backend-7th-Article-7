package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MatchInfo {
	static String apiKey = API_KEY.KEY;

	// MatchId 추출
	public static String matchIds(SummonerDO summonerDO) throws IOException {
		String urlString = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summonerDO.getPuuid()
				+ "/ids?start=0&count=20&api_key=" + apiKey;
		String content = RiotGamesAPIExample.getHttpContent(urlString);

		Gson gson = new Gson();
		JsonArray matchIds = gson.fromJson(content.toString(), JsonArray.class);

		for (int i = 0; i < matchIds.size(); i++) {
			JsonElement matchId = matchIds.get(i);
			System.out.println((i + 1) + ": Match ID: " + matchId.getAsString());
		}

		Scanner scanner = new Scanner(System.in);
		System.out.print("몇번째 매치정보를 가져올까요?: ");
		int selectedMatchIndex = scanner.nextInt();
		scanner.nextLine(); // scanner 버퍼 비우기

		scanner.close();
		return matchInfo(matchIds.get(selectedMatchIndex - 1).getAsString());
	}

	// 추출한 MatchId로 해당 게임 세부 내용 검색

	public static String matchInfo(String matchId) throws IOException {
		String urlString = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;
		String content = RiotGamesAPIExample.getHttpContent(urlString);

		Gson gson = new Gson();
		JsonObject matchObject = gson.fromJson(content.toString(), JsonObject.class);

		JsonArray participants = matchObject.get("info").getAsJsonObject().get("participants").getAsJsonArray();
		JsonObject redTeam = new JsonObject();
		JsonObject blueTeam = new JsonObject();

		MatchInfoDO matchInfoDO = gson.fromJson(content.toString(), MatchInfoDO.class);
		matchInfoDO.setMatchId(matchObject.get("metadata").getAsJsonObject().get("matchId").getAsString());
		matchInfoDO.setDataVersion(matchObject.get("metadata").getAsJsonObject().get("dataVersion").getAsString());
		matchInfoDO.setGameStartTimeStamp(matchObject.get("info").getAsJsonObject().get("gameStartTimestamp").getAsLong());
		matchInfoDO.setGameDuration(matchObject.get("info").getAsJsonObject().get("gameDuration").getAsLong());
		matchInfoDO.setGameType(matchObject.get("info").getAsJsonObject().get("gameType").getAsString());
		matchInfoDO.setGameMode(matchObject.get("info").getAsJsonObject().get("gameMode").getAsString());
		matchInfoDO.setQueueId(matchObject.get("info").getAsJsonObject().get("queueId").getAsInt());
		matchInfoDO.setMapId(matchObject.get("info").getAsJsonObject().get("mapId").getAsInt());
		matchInfoDO.setRedTeamWin(matchObject.get("info").getAsJsonObject().get("teams").getAsJsonArray());

		MatchInfoDAO matchInfoDAO = new MatchInfoDAO();
		matchInfoDAO.insertMatchInfo(matchInfoDO);
		MatchInfoDAO dao = new MatchInfoDAO();
		
		for (int i = 0; i < participants.size(); i++) {
		    JsonObject participant = participants.get(i).getAsJsonObject();
		    int participantId = participant.get("participantId").getAsInt();
		    ParticipantInfo participantInfo = new ParticipantInfo();
		    participantInfo.setParticipantId(participantId);
		    participantInfo.setTeamId(participant.get("teamId").getAsInt());
		    participantInfo.setSummonerName(participant.get("summonerName").getAsString());
		    participantInfo.setChampionName(participant.get("championName").getAsString());
		    participantInfo.setKills(participant.get("kills").getAsInt());
		    participantInfo.setDeaths(participant.get("deaths").getAsInt());
		    participantInfo.setAssists(participant.get("assists").getAsInt());
		    participantInfo.setItem0(participant.get("item0").getAsInt());
		    participantInfo.setItem1(participant.get("item1").getAsInt());
		    participantInfo.setItem2(participant.get("item2").getAsInt());
		    participantInfo.setItem3(participant.get("item3").getAsInt());
		    participantInfo.setItem4(participant.get("item4").getAsInt());
		    participantInfo.setItem5(participant.get("item5").getAsInt());
		    participantInfo.setItem6(participant.get("item6").getAsInt());
		    
		    JsonArray perks = participant.get("perks").getAsJsonObject().get("styles").getAsJsonArray();
		    JsonObject primaryPerksObject = perks.get(0).getAsJsonObject();
		    JsonArray perkPrimarySelections = primaryPerksObject.get("selections").getAsJsonArray();
		    JsonObject subPerksObject = perks.get(1).getAsJsonObject();
		    JsonArray perkSubSelections = subPerksObject.get("selections").getAsJsonArray();
			for (int j = 0; j < perkPrimarySelections.size(); j++) {
				JsonObject primaryPerks = perkPrimarySelections.get(j).getAsJsonObject();
				int perk = primaryPerks.get("perk").getAsInt();

				if (j == 0) {
					participantInfo.setPerk0(perk);
				} else if (j == 1) {
					participantInfo.setPerk1(perk);
				} else if (j == 2) {
					participantInfo.setPerk2(perk);
				} else if (j == 3) {
					participantInfo.setPerk3(perk);
				}
			}
			for (int k = 0; k < perkSubSelections.size(); k++) {
				JsonObject subPerks = perkSubSelections.get(k).getAsJsonObject();
				int perka = subPerks.get("perk").getAsInt();

				if (k == 0) {
					participantInfo.setPerk4(perka);
				} else if (k == 1) {
					participantInfo.setPerk5(perka);
				}
			}
			dao.insertParticipantInfo(participantInfo, matchId);
		}

		/*System.out.println("Match ID: " + matchId);
		System.out.println("Data Version: " + matchObject.get("metadata").getAsJsonObject().get("dataVersion").getAsString());
		System.out.println("Game Start Time Stamp: "+ matchObject.get("info").getAsJsonObject().get("gameStartTimestamp").getAsString());
		long timestamp = matchObject.get("info").getAsJsonObject().get("gameStartTimestamp").getAsLong();
		Date date = new Date(timestamp);
		System.out.println("Game Start Time: " + date);
		System.out.println("Game Duration: " + matchObject.get("info").getAsJsonObject().get("gameDuration").getAsInt());
		int gameDuration = matchObject.get("info").getAsJsonObject().get("gameDuration").getAsInt();
		int minutes = gameDuration / 60;
		int seconds = gameDuration % 60;
		System.out.printf("Game Real Time: %d분 %d초\n", minutes, seconds);
		System.out.println("Game Type: " + matchObject.get("info").getAsJsonObject().get("gameType").getAsString());
		System.out.println("Game Mode: " + matchObject.get("info").getAsJsonObject().get("gameMode").getAsString());
		System.out.println("Queue Id: " + matchObject.get("info").getAsJsonObject().get("queueId").getAsInt());
		String gameMode = getGameMode.getGameMode(matchObject.get("info").getAsJsonObject().get("queueId").getAsInt());
		System.out.println("게임 모드: " + gameMode);
		System.out.println("Map ID: " + matchObject.get("info").getAsJsonObject().get("mapId").getAsInt());
		String mapId = getMap.getMap(matchObject.get("info").getAsJsonObject().get("mapId").getAsInt());
		System.out.println("맵: " + mapId);

		JsonArray teams = matchObject.get("info").getAsJsonObject().get("teams").getAsJsonArray();
		boolean isRedTeamWin = teams.get(0).getAsJsonObject().get("win").getAsBoolean();
		if (isRedTeamWin) {
			System.out.println("\nRed Team Win!");
		} else {
			System.out.println("\nBlue Team Win!");
		}

		// 매치 정보

		for (int i = 0; i < participants.size(); i++) {
			JsonObject participant = participants.get(i).getAsJsonObject();
			int teamId = participant.get("teamId").getAsInt();
			String summonerName1 = participant.get("summonerName").getAsString();
			String championName = participant.get("championName").getAsString();

			JsonArray perk = matchObject.get("info").getAsJsonObject().get("participants").getAsJsonArray().get(i)
					.getAsJsonObject().get("perks").getAsJsonObject().get("styles").getAsJsonArray();

			JsonObject itemObject = new JsonObject();
			itemObject.addProperty("item0", participant.get("item0").getAsInt());
			itemObject.addProperty("item1", participant.get("item1").getAsInt());
			itemObject.addProperty("item2", participant.get("item2").getAsInt());
			itemObject.addProperty("item3", participant.get("item3").getAsInt());
			itemObject.addProperty("item4", participant.get("item4").getAsInt());
			itemObject.addProperty("item5", participant.get("item5").getAsInt());
			itemObject.addProperty("item6", participant.get("item6").getAsInt());

			if (teamId == 100) {
				JsonObject participantObject = new JsonObject();
				participantObject.addProperty("championName", championName);
				participantObject.addProperty("kills", participant.get("kills").getAsInt());
				participantObject.addProperty("deaths", participant.get("deaths").getAsInt());
				participantObject.addProperty("assists", participant.get("assists").getAsInt());
				participantObject.add("perks", perk);
				participantObject.add("items", itemObject);
				redTeam.add(summonerName1, participantObject);
			} else if (teamId == 200) {
				JsonObject participantObject = new JsonObject();
				participantObject.addProperty("championName", championName);
				participantObject.addProperty("kills", participant.get("kills").getAsInt());
				participantObject.addProperty("deaths", participant.get("deaths").getAsInt());
				participantObject.addProperty("assists", participant.get("assists").getAsInt());
				participantObject.add("perks", perk);
				participantObject.add("items", itemObject);
				blueTeam.add(summonerName1, participantObject);
			}
		}

		System.out.println("\n--------Red Team--------");
		for (String summonerName1 : redTeam.keySet()) {
			JsonObject participantObject = redTeam.get(summonerName1).getAsJsonObject();
			String championName = participantObject.get("championName").getAsString();
			int kills = participantObject.get("kills").getAsInt();
			int deaths = participantObject.get("deaths").getAsInt();
			int assists = participantObject.get("assists").getAsInt();
			System.out.printf("- %s (%s) KDA: %d/%d/%d\n", summonerName1, championName, kills, deaths, assists);

			// 해당 참가자의 아이템 출력
			JsonObject itemObject = participantObject.get("items").getAsJsonObject();
			for (int i = 0; i <= 6; i++) {
				int itemId = itemObject.get("item" + i).getAsInt();
				String itemName = getItemMap.getItemMap().get(String.valueOf(itemId));
				System.out.println("  - Item " + itemId + " : " + itemName);
			}
			JsonArray perkArray = participantObject.get("perks").getAsJsonArray();
			JsonObject perkObject = perkArray.get(0).getAsJsonObject();
			JsonArray perkSelectionsArray = perkObject.get("selections").getAsJsonArray();
			JsonObject perkSubObject = perkArray.get(1).getAsJsonObject();
			JsonArray perkSubSelectionsArray = perkSubObject.get("selections").getAsJsonArray();

			for (int i = 0; i < perkSelectionsArray.size(); i++) {
				JsonObject perkSelectionObject = perkSelectionsArray.get(i).getAsJsonObject();
				long perkValue = perkSelectionObject.get("perk").getAsLong();
				String perkName = getPerkMap.getPerkMap().get(perkValue);
				System.out.println("  - Perk " + (i + 1) + " : " + perkName);
			}

			for (int i = 0; i < perkSubSelectionsArray.size(); i++) {
				JsonObject perkSubSelectionObject = perkSubSelectionsArray.get(i).getAsJsonObject();
				long perkValue = perkSubSelectionObject.get("perk").getAsLong();
				String perkName = getPerkMap.getPerkMap().get(perkValue);
				System.out.println("  - Sub Perk " + (i + 1) + " : " + perkName);
			}

		}

		System.out.println("\n--------Blue Team--------");
		for (String summonerName1 : blueTeam.keySet()) {
			JsonObject participantObject = blueTeam.get(summonerName1).getAsJsonObject();
			String championName = participantObject.get("championName").getAsString();
			int kills = participantObject.get("kills").getAsInt();
			int deaths = participantObject.get("deaths").getAsInt();
			int assists = participantObject.get("assists").getAsInt();
			System.out.printf("- %s (%s) KDA: %d/%d/%d\n", summonerName1, championName, kills, deaths, assists);
			JsonObject itemObject = participantObject.get("items").getAsJsonObject();
			for (int i = 0; i <= 6; i++) {
				int itemId = itemObject.get("item" + i).getAsInt();
				String itemName = getItemMap.getItemMap().get(String.valueOf(itemId));
				System.out.println("  - Item " + itemId + " : " + itemName);
			}
			JsonArray perkArray = participantObject.get("perks").getAsJsonArray();
			JsonObject perkObject = perkArray.get(0).getAsJsonObject();
			JsonArray perkSelectionsArray = perkObject.get("selections").getAsJsonArray();
			JsonObject perkSubObject = perkArray.get(1).getAsJsonObject();
			JsonArray perkSubSelectionsArray = perkSubObject.get("selections").getAsJsonArray();

			for (int i = 0; i < perkSelectionsArray.size(); i++) {
				JsonObject perkSelectionObject = perkSelectionsArray.get(i).getAsJsonObject();
				long perkValue = perkSelectionObject.get("perk").getAsLong();
				String perkName = getPerkMap.getPerkMap().get(perkValue);
				System.out.println("  - Perk " + (i + 1) + " : " + perkName);
			}

			for (int i = 0; i < perkSubSelectionsArray.size(); i++) {
				JsonObject perkSubSelectionObject = perkSubSelectionsArray.get(i).getAsJsonObject();
				long perkValue = perkSubSelectionObject.get("perk").getAsLong();
				String perkName = getPerkMap.getPerkMap().get(perkValue);
				System.out.println("  - Sub Perk " + (i + 1) + " : " + perkName);
			}
		}*/
		return "조회 완료";
	}
}
