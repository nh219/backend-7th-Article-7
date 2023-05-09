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
	static String apiKey = API_KEY.KEY;

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
				+ "?api_key=" + apiKey;
		String content = getHttpContent(urlString);

		Gson gson = new Gson();
		SummonerDO summonerDO = gson.fromJson(content.toString(), SummonerDO.class);
		SummonerDAO summonerDAO = new SummonerDAO();
		summonerDAO.insertOrUpdateSummoner(summonerDO);
		
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
		String urlString = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDO.getId() + "?api_key=" + apiKey;
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
			return CurrentlyInGame(summonerDO);

		} catch (Exception e) {
			System.out.println("현재 게임중이 아닙니다.");
			return MatchInfo.matchIds(summonerDO);
		}
	}

	// 현재 게임 중인지 확인하는 메서드
	public static String CurrentlyInGame(SummonerDO summonerDO) throws IOException {
		String urlString = "https://kr.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/" + summonerDO.getId() + "?api_key=" + apiKey;
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
			System.out.println("mapId: " + getMap.getMap(mapId));
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
				String perkName = getPerkMap.getPerkMap().get(perkIds);
				String perkName2 = getPerkMap.getPerkMap().get(perkSubStyle);

				System.out.println("Summoner Name: " + summonerName);
				System.out.println("Champion Name: " + championID.getChampionName(championId));
				System.out.println("Banned Champion Name: " + championID.getChampionName(bannedChampionId));
				System.out.println("Spell1 Id: " + spellToName.getSpellName(spell1Id) + " Spell2 Id: " + spellToName.getSpellName(spell2Id));
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
			return "\n현재 게임중입니다.\n" + MatchInfo.matchIds(summonerDO);
			
		} catch (IOException e) {
			if (e instanceof java.io.FileNotFoundException) {
				return "cuurentlyInGame 오류 발생.";
			} else {
				throw e;
			}
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
