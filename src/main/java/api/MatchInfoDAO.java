package api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component
public class MatchInfoDAO {
	static String apiKey = API_KEY.KEY;
	private Connection connection;

	public MatchInfoDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertMatchInfo(MatchInfoDO matchInfoDO) {
		String sql = "INSERT INTO match_info (match_id, data_version, game_start_time_stamp, game_duration, game_type, game_mode, queue_id, map_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, matchInfoDO.getMatchId());
			pstmt.setString(2, matchInfoDO.getDataVersion());
			pstmt.setLong(3, matchInfoDO.getGameStartTimeStamp());
			pstmt.setLong(4, matchInfoDO.getGameDuration());
			pstmt.setString(5, matchInfoDO.getGameType());
			pstmt.setString(6, matchInfoDO.getGameMode());
			pstmt.setInt(7, matchInfoDO.getQueueId());
			pstmt.setInt(8, matchInfoDO.getMapId());
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			// �ߺ��� �������� ��쿡 ���� ó��
			System.out.println("Duplicate data for matchId: " + matchInfoDO.getMatchId() + " - ignoring...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertParticipantInfo(ParticipantInfoDO participantInfo, String matchId) {
		String sql = "INSERT INTO participant_info (PATICIPANTS_PUUID, participant_id, match_id, team_id, summonername, championName, kills, deaths, assists, perk0, perk1, perk2, perk3, perk4, perk5, item0, item1, item2, item3, item4, item5, item6, is_team_win) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, participantInfo.getPaticipantsPUUID());
			pstmt.setInt(2, participantInfo.getParticipantId());
			pstmt.setString(3, matchId);
			pstmt.setInt(4, participantInfo.getTeamId());
			pstmt.setString(5, participantInfo.getSummonerName());
			pstmt.setString(6, participantInfo.getChampionName());
			pstmt.setInt(7, participantInfo.getKills());
			pstmt.setInt(8, participantInfo.getDeaths());
			pstmt.setInt(9, participantInfo.getAssists());
			pstmt.setInt(10, participantInfo.getPerk0());
			pstmt.setInt(11, participantInfo.getPerk1());
			pstmt.setInt(12, participantInfo.getPerk2());
			pstmt.setInt(13, participantInfo.getPerk3());
			pstmt.setInt(14, participantInfo.getPerk4());
			pstmt.setInt(15, participantInfo.getPerk5());
			pstmt.setInt(16, participantInfo.getItem0());
			pstmt.setInt(17, participantInfo.getItem1());
			pstmt.setInt(18, participantInfo.getItem2());
			pstmt.setInt(19, participantInfo.getItem3());
			pstmt.setInt(20, participantInfo.getItem4());
			pstmt.setInt(21, participantInfo.getItem5());
			pstmt.setInt(22, participantInfo.getItem6());
			pstmt.setInt(23, participantInfo.isWin() ? 1 : 0);
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Duplicate data : " + participantInfo.getParticipantId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// MatchId�� participants DB�� �����ϴ� �޼ҵ�
	public void matchInfo(String matchId) throws IOException {
		String urlString = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;
		String content = RiotGamesAPIExample.getHttpContent(urlString);

		Gson gson = new Gson();
		JsonObject matchObject = gson.fromJson(content.toString(), JsonObject.class);
		MatchInfoDO matchInfoDO = gson.fromJson(content.toString(), MatchInfoDO.class);
		JsonArray participantPUUID = matchObject.get("metadata").getAsJsonObject().get("participants").getAsJsonArray();
		JsonArray participants = matchObject.get("info").getAsJsonObject().get("participants").getAsJsonArray();

		matchInfoDO.setMatchId(matchObject.get("metadata").getAsJsonObject().get("matchId").getAsString());
		matchInfoDO.setDataVersion(matchObject.get("metadata").getAsJsonObject().get("dataVersion").getAsString());
		matchInfoDO.setGameStartTimeStamp(matchObject.get("info").getAsJsonObject().get("gameStartTimestamp").getAsLong());
		matchInfoDO.setGameDuration(matchObject.get("info").getAsJsonObject().get("gameDuration").getAsLong());
		matchInfoDO.setGameType(matchObject.get("info").getAsJsonObject().get("gameType").getAsString());
		matchInfoDO.setGameMode(matchObject.get("info").getAsJsonObject().get("gameMode").getAsString());
		matchInfoDO.setQueueId(matchObject.get("info").getAsJsonObject().get("queueId").getAsInt());
		matchInfoDO.setMapId(matchObject.get("info").getAsJsonObject().get("mapId").getAsInt());

		MatchInfoDAO matchInfoDAO = new MatchInfoDAO();
		matchInfoDAO.insertMatchInfo(matchInfoDO);
		MatchInfoDAO dao = new MatchInfoDAO();

		for (int i = 0; i < participants.size(); i++) {
			JsonObject participant = participants.get(i).getAsJsonObject();
			int participantId = participant.get("participantId").getAsInt();
			ParticipantInfoDO participantInfo = new ParticipantInfoDO();
			participantInfo.setPaticipantsPUUID(participantPUUID.get(i).getAsString());
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
			participantInfo.setWin(participant.get("win").getAsBoolean());

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
	}

	public List<MatchInfoDO> MatchInfoDB(String matchId) throws SQLException {
		List<MatchInfoDO> matchInfoList = new ArrayList<>();
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "scott";
		String password = "tiger";
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT* FROM MATCH_INFO WHERE match_id = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, matchId);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						String dataVersion = resultSet.getString("data_version");
						long gameStartTimeStamp = resultSet.getLong("game_start_time_stamp");
						long gameDuration = resultSet.getLong("game_duration");
						String gameType = resultSet.getString("game_type");
						String gameMode = resultSet.getString("game_mode");
						int queueId = resultSet.getInt("queue_id");
						int mapId = resultSet.getInt("map_id");
						MatchInfoDO matchInfoDO = new MatchInfoDO();
						matchInfoDO.setMatchId(matchId);
						matchInfoDO.setDataVersion(dataVersion);
						matchInfoDO.setGameStartTimeStamp(gameStartTimeStamp);
						matchInfoDO.setGameDuration(gameDuration);
						matchInfoDO.setGameType(gameType);
						matchInfoDO.setGameMode(gameMode);
						matchInfoDO.setQueueId(queueId);
						matchInfoDO.setMapId(mapId);

						matchInfoList.add(matchInfoDO);
					}
				}
			}
		}
		return matchInfoList;
	}
}
