package mvc.model;

import java.sql.*;

public class PostDao {

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	
	public PostDao() {
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
	
	public void Search (String category, String keyword) {
		
	}
	
	public int insert(PostDO postDO) {
		int rowCount = 0;
		sql = "INSERT INTO post (post_id, member_id, post_content) VALUES (?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postDO.getPostId());
			pstmt.setInt(2, postDO.getMemberId());
			pstmt.setString(3, postDO.getContent());
			rowCount = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowCount;
	}
	
	public int update(PostDO postDO) {
		int rowCount = 0;
		sql = "UPDATE post SET post_content='' WHERE post_content=''";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postDO.getContent());
			rowCount = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowCount;
	}
}
