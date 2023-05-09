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

	        // SummonerDO ��ü���� ���� �޾ƿ� �� pstmt�� ����
	        pstmt.setString(1, summonerDO.getAccountId());

	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) { // �̹� �����ϴ� ��� -> ������Ʈ ����
	            updatePstmt.setInt(1, summonerDO.getProfileIconId());
	            updatePstmt.setLong(2, summonerDO.getRevisionDate());
	            updatePstmt.setString(3, summonerDO.getName());
	            updatePstmt.setString(4, summonerDO.getId());
	            updatePstmt.setString(5, summonerDO.getPuuid());
	            updatePstmt.setLong(6, summonerDO.getSummonerLevel());
	            updatePstmt.setString(7, summonerDO.getAccountId());
	            int count = updatePstmt.executeUpdate();
	            System.out.println(count + " rows updated.");
	        } else { // ���ο� ��� -> �߰� ����
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
