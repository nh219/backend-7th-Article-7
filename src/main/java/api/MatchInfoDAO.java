package api;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MatchInfoDAO {
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
	            // 중복된 데이터인 경우에 대한 처리
	            System.out.println("Duplicate data for matchId: " + matchInfoDO.getMatchId() + " - ignoring...");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void insertParticipantInfo(ParticipantInfo participantInfo, String matchId) {
	        String sql = "INSERT INTO participant_info (participant_id, match_id, team_id, summonername, championName, kills, deaths, assists, perk0, perk1, perk2, perk3, perk4, perk5, item0, item1, item2, item3, item4, item5, item6, is_team_win) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";	        try {
	            PreparedStatement pstmt = connection.prepareStatement(sql);
	                pstmt.setInt(1, participantInfo.getParticipantId());
	                pstmt.setString(2, matchId);
	                pstmt.setInt(3, participantInfo.getTeamId());
	                pstmt.setString(4, participantInfo.getSummonerName());
	                pstmt.setString(5, participantInfo.getChampionName());
	                pstmt.setInt(6, participantInfo.getKills());
	                pstmt.setInt(7, participantInfo.getDeaths());
	                pstmt.setInt(8, participantInfo.getAssists());
	                pstmt.setInt(9, participantInfo.getPerk0());
	                pstmt.setInt(10, participantInfo.getPerk1());
	                pstmt.setInt(11, participantInfo.getPerk2());
	                pstmt.setInt(12, participantInfo.getPerk3());
	                pstmt.setInt(13, participantInfo.getPerk4());
	                pstmt.setInt(14, participantInfo.getPerk5());
	                pstmt.setInt(15, participantInfo.getItem0());
					pstmt.setInt(16, participantInfo.getItem1());
					pstmt.setInt(17, participantInfo.getItem2());
					pstmt.setInt(18, participantInfo.getItem3());
					pstmt.setInt(19, participantInfo.getItem4());
					pstmt.setInt(20, participantInfo.getItem5());
					pstmt.setInt(21, participantInfo.getItem6());
					pstmt.setInt(22, participantInfo.isWin() ? 1 : 0);
					pstmt.executeUpdate();
				} catch (SQLIntegrityConstraintViolationException e) {
					System.out.println("Duplicate data : " + participantInfo.getParticipantId());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	    
	    public List<ParticipantInfo> retrieveParticipantInfoFromDatabase(String matchId) throws SQLException {
	        List<ParticipantInfo> participantInfoList = new ArrayList<>();
	        String url = "jdbc:oracle:thin:@localhost:1521:XE";
	        String username = "scott";
	        String password = "tiger";
	        try (Connection connection = DriverManager.getConnection(url, username, password)) {
	            String sql = "SELECT participant_id, match_id, team_id, summonername, championName, kills, deaths, assists, perk0, perk1, perk2, perk3, perk4, perk5, item0, item1, item2, item3, item4, item5, item6 FROM participant_info WHERE match_id = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, matchId);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    while (resultSet.next()) {
	                        int participantId = resultSet.getInt("participant_id");
	                        int teamId = resultSet.getInt("team_id");
	                        String summonerName = resultSet.getString("summonername");
	                        String championName = resultSet.getString("championName");
	                        int kills = resultSet.getInt("kills");
	                        int deaths = resultSet.getInt("deaths");
	                        int assists = resultSet.getInt("assists");
	                        int perk0 = resultSet.getInt("perk0");
	                        int perk1 = resultSet.getInt("perk1");
	                        int perk2 = resultSet.getInt("perk2");
	                        int perk3 = resultSet.getInt("perk3");
	                        int perk4 = resultSet.getInt("perk4");
	                        int perk5 = resultSet.getInt("perk5");
	                        int item0 = resultSet.getInt("item0");
	                        int item1 = resultSet.getInt("item1");
	                        int item2 = resultSet.getInt("item2");
	                        int item3 = resultSet.getInt("item3");
	                        int item4 = resultSet.getInt("item4");
	                        int item5 = resultSet.getInt("item5");
	                        int item6 = resultSet.getInt("item6");
	                        ParticipantInfo participantInfo = new ParticipantInfo();
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

	                        participantInfoList.add(participantInfo);
	                    }
	                }
	            }
	        }
	        return participantInfoList;
	    }
}


