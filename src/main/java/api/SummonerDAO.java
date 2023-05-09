package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SummonerDAO {

	public void insertOrUpdateSummoner(SummonerDO summonerDO) {
	    String url = "jdbc:oracle:thin:@localhost:1521:XE";
	    String user = "scott";
	    String password = "tiger";

	    try (Connection conn = DriverManager.getConnection(url, user, password);
	         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Summoner WHERE account_id = ?");
	         PreparedStatement insertPstmt = conn.prepareStatement("INSERT INTO Summoner VALUES (?, ?, ?, ?, ?, ?, ?)");
	         PreparedStatement updatePstmt = conn.prepareStatement("UPDATE Summoner SET profile_icon_id = ?, revision_date = ?, name = ?, id = ?, puuid = ?, summoner_level = ? WHERE account_id = ?")) {

	        // SummonerDO 객체에서 값을 받아온 후 pstmt에 설정
	        pstmt.setString(1, summonerDO.getAccountId());

	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) { // 이미 존재하는 경우 -> 업데이트 수행
	            updatePstmt.setInt(1, summonerDO.getProfileIconId());
	            updatePstmt.setLong(2, summonerDO.getRevisionDate());
	            updatePstmt.setString(3, summonerDO.getName());
	            updatePstmt.setString(4, summonerDO.getId());
	            updatePstmt.setString(5, summonerDO.getPuuid());
	            updatePstmt.setLong(6, summonerDO.getSummonerLevel());
	            updatePstmt.setString(7, summonerDO.getAccountId());
	            int count = updatePstmt.executeUpdate();
	            System.out.println(count + " rows updated.");
	        } else { // 새로운 경우 -> 추가 수행
	            insertPstmt.setString(1, summonerDO.getAccountId());
	            insertPstmt.setInt(2, summonerDO.getProfileIconId());
	            insertPstmt.setLong(3, summonerDO.getRevisionDate());
	            insertPstmt.setString(4, summonerDO.getName());
	            insertPstmt.setString(5, summonerDO.getId());
	            insertPstmt.setString(6, summonerDO.getPuuid());
	            insertPstmt.setLong(7, summonerDO.getSummonerLevel());
	            int count = insertPstmt.executeUpdate();
	            System.out.println(count + " rows inserted.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
