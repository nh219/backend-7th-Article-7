package mvc.modelpost;

import java.sql.*;
import java.util.ArrayList;

public class CrawlDAO {

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	
	public CrawlDAO() {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "scott";
		String pwd = "tiger";
		
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, user, pwd);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CrawlDO> getAllCrawl() {
		ArrayList<CrawlDO> Clist = new ArrayList<CrawlDO>();
		CrawlDO crawlDO= null;
		sql = "SELECT * FROM (" +
				  "SELECT url, title, nickname, c_post_date, views, sitename" +
				 " FROM crawl_post" + 
				 " ORDER BY views DESC)" +
				"WHERE ROWNUM <= 10";

		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				crawlDO = new CrawlDO();
				crawlDO.setUrl(rs.getString("url"));
				crawlDO.setTitle(rs.getString("title"));
				crawlDO.setNickName(rs.getString("nickname"));
				crawlDO.setDate(rs.getString("c_post_date"));
				crawlDO.setSiteName(rs.getString("sitename"));
				crawlDO.setViews(rs.getInt("views"));
				Clist.add(crawlDO);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!stmt.isClosed()) {
					stmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return Clist;
	}

}
