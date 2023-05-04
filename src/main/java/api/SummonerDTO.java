package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SummonerDTO {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "scott";
		String password = "tiger";

		try (Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn
						.prepareStatement("INSERT INTO summoner_info VALUES (?, ?, ?, ?, ?, ?)")) {

			SummonerDO summoner = new SummonerDO();			// SummonerDO 객체에서 값을 받아온 후 pstmt에 설정
			pstmt.setString(1, summoner.getAccountId());
			pstmt.setInt(2, summoner.getProfileIconId());
			pstmt.setLong(3, summoner.getRevisionDate());
			pstmt.setString(4, summoner.getName());
			pstmt.setString(5, summoner.getId());
			pstmt.setString(6, summoner.getPuuid());

			int count = pstmt.executeUpdate();
			System.out.println(count + " rows affected.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
