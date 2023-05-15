package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import map.championID;
import map.getMap;
import map.getPerkMap;
import map.spellToName;

@Component
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
		return content.toString();
	}

	// 소환사 정보
	public SummonerDO summonerInfo(String summonerName) throws IOException {
		String urlString = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + apiKey;
		String content = getHttpContent(urlString);

		Gson gson = new Gson();
		JsonObject gameInfoObject = JsonParser.parseString(content).getAsJsonObject();

		SummonerDO summonerDO = new SummonerDO();
		summonerDO.setAccountId(gameInfoObject.get("accountId").getAsString());
		summonerDO.setProfileIconId(gameInfoObject.get("profileIconId").getAsInt());
		summonerDO.setRevisionDate(gameInfoObject.get("revisionDate").getAsLong());
		summonerDO.setName(gameInfoObject.get("name").getAsString());
		summonerDO.setId(gameInfoObject.get("id").getAsString());
		summonerDO.setPuuid(gameInfoObject.get("puuid").getAsString());
		summonerDO.setSummonerLevel(gameInfoObject.get("summonerLevel").getAsLong());

		SummonerDAO summonerDAO = new SummonerDAO();
		summonerDAO.summonerDB(summonerDO);
		return summonerDO;
	}

	// LEAUGE-V4
	public List<LeagueDO> league(SummonerDO summonerDO) throws IOException {
		String urlString = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + summonerDO.getId() + "?api_key=" + apiKey;
		String content = getHttpContent(urlString);

		Gson gson = new Gson();
		JsonArray leagues = gson.fromJson(content.toString(), JsonArray.class);

		List<LeagueDO> leaguesinfo = new ArrayList<>();
		for (JsonElement element : leagues) {
			LeagueDO league = gson.fromJson(element, LeagueDO.class);
			leaguesinfo.add(league);
		}
		return leaguesinfo;
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
			return "\n현재 게임중입니다.\n";

		} catch (IOException e) {
			if (e instanceof java.io.FileNotFoundException) {
				return "cuurentlyInGame 오류 발생.";
			} else {
				throw e;
			}
		}
	}

	// 추출한 MatchId로 해당 게임 세부 내용 검색
	public static void main(String[] args) throws IOException, SQLException {
		    Scanner scanner = new Scanner(System.in);
		    System.out.print("이름을 입력하세요: ");
		    String summonerName = scanner.nextLine();
		    MatchInfoDAO matchInfoDAO = new MatchInfoDAO();
		    MatchInfoDO MatchInfoDO = new MatchInfoDO();
		    ParticipantInfoDO ParticipantInfoDO = new ParticipantInfoDO();

		    RiotGamesAPIExample riotGamesAPIExample = new RiotGamesAPIExample();
		    SummonerDO summonerDO = riotGamesAPIExample.summonerInfo(summonerName);
		    List<String> matchIds = MatchInfo.matchIds(summonerDO);
		    for (int i = 0; i < matchIds.size(); i++) {
		        String matchId = matchIds.get(i);
		        System.out.println(matchId);
		        matchInfoDAO.matchInfo(matchId);
		        matchInfoDAO.insertMatchInfo(MatchInfoDO);
		        matchInfoDAO.insertParticipantInfo(ParticipantInfoDO, matchId);
		    }
		    System.out.println("완료");
		}
	}
		
