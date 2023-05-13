package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipantInfoDAO {
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String DB_USER = "scott";
	private static final String DB_PASSWORD = "tiger";

	public List<ParticipantInfoDO> getParticipantInfoByMatchId(String matchId) {
		List<ParticipantInfoDO> participantInfoList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM participant_info WHERE match_id = ?")) {

			stmt.setString(1, matchId);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int participantId = rs.getInt("participant_id");
					int teamId = rs.getInt("team_id");
					String summonerName = rs.getString("summonername");
					String championName = rs.getString("championname");
					int kills = rs.getInt("kills");
					int deaths = rs.getInt("deaths");
					int assists = rs.getInt("assists");
					int perk0 = rs.getInt("perk0");
					int perk1 = rs.getInt("perk1");
					int perk2 = rs.getInt("perk2");
					int perk3 = rs.getInt("perk3");
					int perk4 = rs.getInt("perk4");
					int perk5 = rs.getInt("perk5");
					int item0 = rs.getInt("item0");
					int item1 = rs.getInt("item1");
					int item2 = rs.getInt("item2");
					int item3 = rs.getInt("item3");
					int item4 = rs.getInt("item4");
					int item5 = rs.getInt("item5");
					int item6 = rs.getInt("item6");
					int win = rs.getInt("IS_TEAM_WIN");
					ParticipantInfoDO participantInfo = new ParticipantInfoDO();
					participantInfo.setParticipantId(participantId);
					participantInfo.setTeamId(teamId);
					participantInfo.setSummonerName(summonerName);
					participantInfo.setChampionName(championName);
					participantInfo.setKills(kills);
					participantInfo.setDeaths(deaths);
					participantInfo.setAssists(assists);
					participantInfo.setPerk0(perk0);
					participantInfo.setPerk1(perk1);
					participantInfo.setPerk2(perk2);
					participantInfo.setPerk3(perk3);
					participantInfo.setPerk4(perk4);
					participantInfo.setPerk5(perk5);
					participantInfo.setItem0(item0);
					participantInfo.setItem1(item1);
					participantInfo.setItem2(item2);
					participantInfo.setItem3(item3);
					participantInfo.setItem4(item4);
					participantInfo.setItem5(item5);
					participantInfo.setItem6(item6);
					participantInfo.setItem6(item6);
					participantInfo.setWin(win == 1);

					participantInfoList.add(participantInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participantInfoList;
	}
}
