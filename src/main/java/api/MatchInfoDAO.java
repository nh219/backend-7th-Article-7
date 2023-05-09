package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

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
	        String sql = "INSERT INTO match_info (match_id, data_version, game_start_time_stamp, game_duration, game_type, game_mode, queue_id, map_id, is_red_team_win) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
	            pstmt.setInt(9, matchInfoDO.isRedTeamWin() != null ? 1 : 0);
	            pstmt.executeUpdate();
	        } catch (SQLIntegrityConstraintViolationException e) {
	            // 중복된 데이터인 경우에 대한 처리
	            System.out.println("Duplicate data for matchId: " + matchInfoDO.getMatchId() + " - ignoring...");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void insertParticipantInfo(ParticipantInfo participantInfo, String matchId) {
	        String sql = "INSERT INTO participant_info (participant_id, match_id, team_id, summonername, championName, kills, deaths, assists, perk0, perk1, perk2, perk3, perk4, perk5, item0, item1, item2, item3, item4, item5, item6) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";	        try {
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
					pstmt.executeUpdate();
				} catch (SQLIntegrityConstraintViolationException e) {
					System.out.println("Duplicate data : " + participantInfo.getParticipantId());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
