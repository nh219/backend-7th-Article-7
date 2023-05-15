package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class MostChampion {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "scott";
    private static final String DB_PASSWORD = "tiger";
    private static final int MAX_RESULTS = 10;

    public Map<String, Integer> findMostPlayedChampions(String summonerName) {
        Map<String, Integer> mostPlayedChampions = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT championName, COUNT(*) AS count " +
                             "FROM PARTICIPANT_INFO " +
                             "WHERE summonerName = ? " +
                             "  AND MATCH_ID IN (SELECT MATCH_ID FROM MATCH_INFO WHERE QUEUE_ID = 420) " +
                             "GROUP BY championName " +
                             "ORDER BY count DESC")) {

            stmt.setString(1, summonerName);

            try (ResultSet rs = stmt.executeQuery()) {
                int count = 0;
                while (rs.next() && count < MAX_RESULTS) {
                    String championName = rs.getString("championName");
                    int playCount = rs.getInt("count");
                    mostPlayedChampions.put(championName, playCount);
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mostPlayedChampions;
    }
    public Map<String, Integer> calculateWinRate(String summonerName) {
        Map<String, Integer> winRates = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT championName, " +
                     "SUM(CASE WHEN is_team_win = 1 THEN 1 ELSE 0 END) AS winCount, " +
                     "COUNT(*) AS totalCount " +
                     "FROM PARTICIPANT_INFO " +
                     "WHERE summonerName = ? " +
                     "AND MATCH_ID IN (SELECT MATCH_ID FROM MATCH_INFO WHERE QUEUE_ID = 420) " +
                     "GROUP BY championName")) {

            stmt.setString(1, summonerName);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String championName = rs.getString("championName");
                    int winCount = rs.getInt("winCount");
                    int totalCount = rs.getInt("totalCount");
                    int winRate = (int) ((winCount * 100.0) / totalCount);
                    winRates.put(championName, winRate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return winRates;
    }


}
