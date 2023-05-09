package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class RiotGamesAPIExample {
	// API KEY
	private static final String API_KEY = "RGAPI-8dc75365-8144-4ce7-bf22-9cf83c7b2ac9";

	// 뭔지 모르겠는데 계속 반복됨
	public static String getHttpContent(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK) {
			return "HTTP 응답 오류: " + responseCode;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder content = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		con.disconnect();
		return content.toString();
	}

	// 소환사 정보
	public static String summonerInfo(String summonerName) throws IOException {
		String urlString = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName
				+ "?api_key=" + API_KEY;
		String content = getHttpContent(urlString);

		Gson gson = new Gson();
		SummonerDO summonerDO = gson.fromJson(content.toString(), SummonerDO.class);

		System.out.println("\nName: " + summonerDO.getName());
		System.out.println("Level: " + summonerDO.getSummonerLevel());
		
		try {
		    return league(summonerDO);
		} catch (Exception e) {
		    System.out.println("예외 발생: " + e.getMessage());
		    return "리그 정보를 가져오는 중 예외가 발생하였습니다.";
		}
	}
	
	// LEAUGE-V4
	public static String league(SummonerDO summonerDO) throws IOException {
		String urlString = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDO.getId() + "?api_key=" + API_KEY;
		String content = getHttpContent(urlString);

		Gson gson = new Gson();
		JsonArray league = gson.fromJson(content.toString(), JsonArray.class);

		for (int i = 0; i < league.size(); i++) {
			JsonObject leagueObj = league.get(i).getAsJsonObject();
			String queueType = leagueObj.get("queueType").getAsString();
			String tier = leagueObj.get("tier").getAsString();
			String rank = leagueObj.get("rank").getAsString();
			int leaguePoints = leagueObj.get("leaguePoints").getAsInt();
			int wins = leagueObj.get("wins").getAsInt();
			int losses = leagueObj.get("losses").getAsInt();

			System.out.println("Queue Type: " + queueType);
			System.out.println("Tier: " + tier);
			System.out.println("Rank: " + rank);
			System.out.println("League Points: " + leaguePoints + "\n");
			System.out.println("wins: " + wins);
			System.out.println("losses: " + losses + "\n");
			System.out.printf("win rate: %.0f%%\n", (double) wins / (wins + losses) * 100);

		}
		try {
			System.out.println("현재 게임중입니다" );
			return CurrentlyInGame(summonerDO);

		} catch (Exception e) {
			return matchIds(summonerDO);
		}
	}

	// 현재 게임 중인지 확인하는 메서드
	public static String CurrentlyInGame(SummonerDO summonerDO) throws IOException {
		String urlString = "https://kr.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/" + summonerDO.getId() + "?api_key=" + API_KEY;
		try {
			String gameInfoJson = getHttpContent(urlString);
			JsonObject gameInfoObject = JsonParser.parseString(gameInfoJson).getAsJsonObject();
			
			long gameId = gameInfoObject.get("gameId").getAsLong();
			String gameType = gameInfoObject.get("gameType").getAsString();
			long gameStartTime = gameInfoObject.get("gameStartTime").getAsLong();
			long mapId = gameInfoObject.get("mapId").getAsLong();
			String gameMode = gameInfoObject.get("gameMode").getAsString();
			long gameQueueConfigId = gameInfoObject.get("gameQueueConfigId").getAsLong();
			JsonArray participantsArray = gameInfoObject.getAsJsonArray("participants");
			JsonArray bannedChampionsArray = gameInfoObject.getAsJsonArray("bannedChampions");
			JsonObject redTeam = new JsonObject();
			JsonObject blueTeam = new JsonObject();

			System.out.println("Game ID: " + gameId);
			System.out.println("gameType: " + gameType);
			System.out.println("gameStartTime: " + gameStartTime);
			System.out.println("mapId: " + getMap(mapId));
			System.out.println("gameMode: " + gameMode);
			System.out.println("gameQueueConfigId: " + gameQueueConfigId);

			for (int i = 0; i < participantsArray.size(); i++) {
				JsonObject participant = participantsArray.get(i).getAsJsonObject();
				int teamId = participant.get("teamId").getAsInt();
				String summonerName = participant.get("summonerName").getAsString();
				long championId = participant.get("championId").getAsLong();
				long spell1Id = participant.get("spell1Id").getAsLong();
				long spell2Id = participant.get("spell2Id").getAsLong();
				JsonObject perks = participant.get("perks").getAsJsonObject();
				long perkIds = perks.getAsJsonArray("perkIds").get(0).getAsLong();
				long perkSubStyle = perks.get("perkSubStyle").getAsLong();
				JsonObject bannedChampions = bannedChampionsArray.get(i).getAsJsonObject();
				long bannedTeamId = bannedChampions.get("teamId").getAsLong();
				long bannedChampionId = bannedChampions.get("championId").getAsLong();

				if (teamId == 100) {
					JsonObject participantObject = new JsonObject();
					participantObject.addProperty("summonerName", summonerName);
					participantObject.addProperty("championId", championId);
					participantObject.addProperty("spell1Id", spell1Id);
					participantObject.addProperty("spell2Id", spell2Id);
					participantObject.addProperty("perkIds", perkIds);
					participantObject.addProperty("perkSubStyle", perkSubStyle);
					// participantObject.addProperty("teamId", bannedTeamId);
					participantObject.addProperty("bannedchampionId", bannedChampionId);
					redTeam.add(summonerName, participantObject);
				} else if (teamId == 200) {
					JsonObject participantObject = new JsonObject();
					participantObject.addProperty("summonerName", summonerName);
					participantObject.addProperty("championId", championId);
					participantObject.addProperty("spell1Id", spell1Id);
					participantObject.addProperty("spell2Id", spell2Id);
					participantObject.addProperty("perkIds", perkIds);
					participantObject.addProperty("perkSubStyle", perkSubStyle);
					participantObject.addProperty("bannedchampionId", bannedChampionId);
					blueTeam.add(summonerName, participantObject);
				}
			}

			System.out.println("\n--------Red Team--------");
			for (String summonerName : redTeam.keySet()) {
				JsonObject participantObject = redTeam.get(summonerName).getAsJsonObject();
				long championId = participantObject.get("championId").getAsLong();
				long spell1Id = participantObject.get("spell1Id").getAsLong();
				long spell2Id = participantObject.get("spell2Id").getAsLong();
				long perkIds = participantObject.get("perkIds").getAsLong();
				long perkSubStyle = participantObject.get("perkSubStyle").getAsLong();
				long bannedChampionId = participantObject.get("bannedchampionId").getAsLong();
				String perkName = getPerkMap().get(perkIds);
				String perkName2 = getPerkMap().get(perkSubStyle);

				System.out.println("Summoner Name: " + summonerName);
				System.out.println("Champion Name: " + getChampionName(championId));
				System.out.println("Banned Champion Name: " + getChampionName(bannedChampionId));
				System.out.println("Spell1 Id: " + getSpellName(spell1Id) + " Spell2 Id: " + getSpellName(spell2Id));
				System.out.println("Perk Ids: " + perkName + " Perk Sub: " + perkName2 + "\n");
			}
			System.out.println("\n--------Blue Team--------");
			for (String summonerName : blueTeam.keySet()) {
				JsonObject participantObject = blueTeam.get(summonerName).getAsJsonObject();
				long championId = participantObject.get("championId").getAsLong();
				long spell1Id = participantObject.get("spell1Id").getAsLong();
				long spell2Id = participantObject.get("spell2Id").getAsLong();
				long perkIds = participantObject.get("perkIds").getAsLong();
				long perkSubStyle = participantObject.get("perkSubStyle").getAsLong();

				System.out.println("Summoner Name: " + summonerName + " Champion Id: " + championId);
				System.out.println("Spell1 Id: " + spell1Id + " Spell2 Id: " + spell2Id);
				System.out.println("Perk Ids: " + perkIds + " Perk Sub: " + perkSubStyle + "\n");
			}
			return "\n현재 게임중입니다.\n" + matchIds(summonerDO);
			
		} catch (IOException e) {
			if (e instanceof java.io.FileNotFoundException) {
				return "cuurentlyInGame 오류 발생.";
			} else {
				throw e;
			}
		}
	}



	// MatchId 추출
	public static String matchIds(SummonerDO summonerDO) throws IOException {
		String urlString = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + summonerDO.getPuuid()
				+ "/ids?start=0&count=20&api_key=" + API_KEY;
		String content = getHttpContent(urlString);

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
		String urlString = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + API_KEY;
		String content = getHttpContent(urlString);

		Gson gson = new Gson();
		JsonObject matchObject = gson.fromJson(content.toString(), JsonObject.class);
		System.out.println("Match ID: " + matchId);
		System.out.println(
				"Data Version: " + matchObject.get("metadata").getAsJsonObject().get("dataVersion").getAsString());
		System.out.println("Game Start Time Stamp: "
				+ matchObject.get("info").getAsJsonObject().get("gameStartTimestamp").getAsString());
		long timestamp = matchObject.get("info").getAsJsonObject().get("gameStartTimestamp").getAsLong();
		Date date = new Date(timestamp);
		System.out.println("Game Start Time: " + date);
		System.out
				.println("Game Duration: " + matchObject.get("info").getAsJsonObject().get("gameDuration").getAsInt());
		int gameDuration = matchObject.get("info").getAsJsonObject().get("gameDuration").getAsInt();
		int minutes = gameDuration / 60;
		int seconds = gameDuration % 60;
		System.out.printf("Game Real Time: %d분 %d초\n", minutes, seconds);
		System.out.println("Game Type: " + matchObject.get("info").getAsJsonObject().get("gameType").getAsString());
		System.out.println("Game Mode: " + matchObject.get("info").getAsJsonObject().get("gameMode").getAsString());
		System.out.println("Queue Id: " + matchObject.get("info").getAsJsonObject().get("queueId").getAsInt());
		String gameMode = getGameMode(matchObject.get("info").getAsJsonObject().get("queueId").getAsInt());
		System.out.println("게임 모드: " + gameMode);
		System.out.println("Map ID: " + matchObject.get("info").getAsJsonObject().get("mapId").getAsInt());
		String mapId = getMap(matchObject.get("info").getAsJsonObject().get("mapId").getAsInt());
		System.out.println("맵: " + mapId);

		JsonArray teams = matchObject.get("info").getAsJsonObject().get("teams").getAsJsonArray();
		boolean isRedTeamWin = teams.get(0).getAsJsonObject().get("win").getAsBoolean();
		if (isRedTeamWin) {
			System.out.println("\nRed Team Win!");
		} else {
			System.out.println("\nBlue Team Win!");
		}

		// 매치 정보
		JsonArray participants = matchObject.get("info").getAsJsonObject().get("participants").getAsJsonArray();
		JsonObject redTeam = new JsonObject();
		JsonObject blueTeam = new JsonObject();

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
				String itemName = getItemMap().get(String.valueOf(itemId));
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
				String perkName = getPerkMap().get(perkValue);
				System.out.println("  - Perk " + (i + 1) + " : " + perkName);
			}

			for (int i = 0; i < perkSubSelectionsArray.size(); i++) {
				JsonObject perkSubSelectionObject = perkSubSelectionsArray.get(i).getAsJsonObject();
				long perkValue = perkSubSelectionObject.get("perk").getAsLong();
				String perkName = getPerkMap().get(perkValue);
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
				String itemName = getItemMap().get(String.valueOf(itemId));
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
				String perkName = getPerkMap().get(perkValue);
				System.out.println("  - Perk " + (i + 1) + " : " + perkName);
			}

			for (int i = 0; i < perkSubSelectionsArray.size(); i++) {
				JsonObject perkSubSelectionObject = perkSubSelectionsArray.get(i).getAsJsonObject();
				long perkValue = perkSubSelectionObject.get("perk").getAsLong();
				String perkName = getPerkMap().get(perkValue);
				System.out.println("  - Sub Perk " + (i + 1) + " : " + perkName);
			}

		}
		return null;

	}

	// QueueId 파싱 메서드
	public static String getGameMode(int queueId) {
		try {
			// 1. HTTP 요청 보내기
			String urlString = "https://static.developer.riotgames.com/docs/lol/queues.json";
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 2. HTTP 응답 처리하기
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				return "HTTP 응답 오류: " + responseCode;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// 3. JSON 데이터 파싱하기
			JsonArray queuesJsonArray = JsonParser.parseString(content.toString()).getAsJsonArray();
			Map<Integer, String> queueIdToGameMode = new HashMap<Integer, String>();
			for (JsonElement queueElement : queuesJsonArray) {
				JsonObject queue = queueElement.getAsJsonObject();
				int id = queue.get("queueId").getAsInt();
				JsonElement descriptionElement = queue.get("description");
				String gameMode = descriptionElement.isJsonNull() ? null : descriptionElement.getAsString();
				queueIdToGameMode.put(id, gameMode);
			}

			// 4. queueId 매핑 정보를 이용하여 게임 모드 이름 반환
			String gameMode = queueIdToGameMode.get(queueId);
			if (gameMode == null) {
				return "해당하는 게임 모드가 없습니다.";
			}
			return gameMode;

		} catch (IOException e) {
			return "HTTP 요청 오류: " + e.getMessage();
		}
	}

	// mapId 파싱 메서드
	public static String getMap(long mapId) {
		try {
			// 1. HTTP 요청 보내기
			String urlString = "https://static.developer.riotgames.com/docs/lol/maps.json";
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 2. HTTP 응답 처리하기
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				return "HTTP 응답 오류: " + responseCode;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// 3. JSON 데이터 파싱하기
			JsonArray mapJsonArray = JsonParser.parseString(content.toString()).getAsJsonArray();
			Map<Long, String> mapIdToMap = new HashMap<Long, String>();
			for (JsonElement mapElement : mapJsonArray) {
				JsonObject mapObject = mapElement.getAsJsonObject();
				long id = mapObject.get("mapId").getAsLong();
				JsonElement descriptionElement = mapObject.get("mapName");
				String mapName = (descriptionElement == null || descriptionElement.isJsonNull()) ? null
						: descriptionElement.getAsString();
				mapIdToMap.put(id, mapName);
			}

			// 4. mapId 매핑 정보를 이용하여 맵 이름 반환
			String mapName = mapIdToMap.get(mapId);
			if (mapName == null) {
				return "해당하는 맵이 없습니다.";
			}
			return mapName;

		} catch (IOException e) {
			return "HTTP 요청 오류: " + e.getMessage();
		}
	}

	// item 파싱 메서드
	public static Map<String, String> getItemMap() {
		Map<String, String> itemMap = new HashMap<String, String>();
		try {
			// 1. HTTP 요청 보내기
			String urlString = "https://ddragon.leagueoflegends.com/cdn/13.8.1/data/ko_KR/item.json";
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 2. HTTP 응답 처리하기
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				System.out.println("HTTP 응답 오류: " + responseCode);
				return itemMap;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// 3. JSON 데이터 파싱하기
			JsonObject itemJsonObject = JsonParser.parseString(content.toString()).getAsJsonObject();
			JsonObject items = itemJsonObject.getAsJsonObject("data");
			for (Map.Entry<String, JsonElement> entry : items.entrySet()) {
				JsonObject itemObject = entry.getValue().getAsJsonObject();
				String itemName = itemObject.get("name").getAsString();
				String itemId = entry.getKey();
				itemMap.put(itemId, itemName);
			}

			return itemMap;
		} catch (IOException e) {
			System.out.println("HTTP 요청 오류: " + e.getMessage());
			return itemMap;
		}
	}

	public static Map<Long, String> getPerkMap() {
		Map<Long, String> perkMap = new HashMap<Long, String>();
		try {
			// 1. HTTP 요청 보내기
			String urlString = "https://ddragon.leagueoflegends.com/cdn/13.8.1/data/ko_KR/runesReforged.json";
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 2. HTTP 응답 처리하기
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				System.out.println("HTTP 응답 오류: " + responseCode);
				return null;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// 3. JSON 데이터 파싱하기
			JsonArray runesArray = JsonParser.parseString(content.toString()).getAsJsonArray();
			for (JsonElement runeElement : runesArray) {
				JsonObject runeObject = runeElement.getAsJsonObject();
				long perkId = runeObject.get("id").getAsLong();
				String perkStyle = runeObject.get("name").getAsString();
				for (JsonElement perkElement : runeObject.get("slots").getAsJsonArray()) {
					for (JsonElement perkSubElement : perkElement.getAsJsonObject().get("runes").getAsJsonArray()) {
						JsonObject perkObject = perkSubElement.getAsJsonObject();
						long perkId1 = perkObject.get("id").getAsLong();
						String perkName = perkObject.get("name").getAsString();
						perkMap.put(perkId1, perkName);
						if (perkName == null) {
							break;
						}
					}
				}
				perkMap.put(perkId, perkStyle);
			}
			return perkMap;
		} catch (IOException e) {
			System.out.println("HTTP 요청 오류: " + e.getMessage());
			return null;
		} catch (JsonSyntaxException e) {
			System.out.println("JSON 파싱 오류: " + e.getMessage());
			return null;
		}
	}

	// 챔피언 ID 맵
	private static Map<Long, String> championIdToName = new HashMap<Long, String>();

	// 챔피언 ID 맵핑
	public static String getChampionName(long championId) {
		try {
			// 1. HTTP 요청 보내기
			String championDataUrl = "https://ddragon.leagueoflegends.com/cdn/13.9.1/data/ko_KR/champion.json";
			URL url = new URL(championDataUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 2. HTTP 응답 처리하기
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				return "HTTP 응답 오류: " + responseCode;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// 3. JSON 데이터 파싱하기
			JsonObject champion = JsonParser.parseString(content.toString()).getAsJsonObject();
			JsonObject championData = champion.getAsJsonObject("data");
			for (Map.Entry<String, JsonElement> entry : championData.entrySet()) {
				JsonObject championObject = entry.getValue().getAsJsonObject();
				if (championObject.get("key").getAsString().equals(String.valueOf(championId))) {
					String championName = championObject.get("name").getAsString();
					championIdToName.put(championId, championName);
					return championName;
				}
			}

			return "해당하는 챔피언이 없습니다.";
		} catch (IOException e) {
			return "HTTP 요청 오류: " + e.getMessage();
		}
	}

	// 소환사 주문 맵
	private static Map<Long, String> spellIdToName = new HashMap<Long, String>();

	// 소환사 주문 맵핑
	public static String getSpellName(long spellId) {
		try {
			// 1. HTTP 요청 보내기
			String championDataUrl = "https://ddragon.leagueoflegends.com/cdn/13.9.1/data/ko_KR/summoner.json";
			URL url = new URL(championDataUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 2. HTTP 응답 처리하기
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				return "HTTP 응답 오류: " + responseCode;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// 3. JSON 데이터 파싱하기
			JsonObject summoner = JsonParser.parseString(content.toString()).getAsJsonObject();
			JsonObject summonerSpellData = summoner.getAsJsonObject("data");
			for (Map.Entry<String, JsonElement> entry : summonerSpellData.entrySet()) {
				JsonObject championObject = entry.getValue().getAsJsonObject();
				if (championObject.get("key").getAsString().equals(String.valueOf(spellId))) {
					String championName = championObject.get("name").getAsString();
					spellIdToName.put(spellId, championName);
					return championName;
				}
			}

			return "해당하는 소환사 주문이 없습니다.";
		} catch (IOException e) {
			return "HTTP 요청 오류: " + e.getMessage();
		}
	}

	// 추출한 MatchId로 해당 게임 세부 내용 검색

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("소환사명을 입력하세요: ");
		String summonerName = scanner.nextLine();

		// 현재 게임 중인지 아닌지 확인
		System.out.println(summonerInfo(summonerName));
	}
}
