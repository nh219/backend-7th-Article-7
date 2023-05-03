package mvc.model;

import java.sql.*;

public class PostDaoSpring {

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;
	
	public PostDaoSpring() {
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
	
	public void Search (String category, String keyword) { //보완필요
		sql = "SELECT * FROM post";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
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
	}
	
	public void insert(PostDO postDO) {
		sql = "INSERT INTO post (post_id, category, title, member_id, post_content) "
				+ "VALUES (post_post_id_seq, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postDO.getPostId());
			pstmt.setString(2, postDO.getCategory());
			pstmt.setString(3, postDO.getTitle());
			pstmt.setInt(4, postDO.getMemberId());
			pstmt.setString(5, postDO.getContent());
			pstmt.executeUpdate();
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
	}
	
	public void update(PostDO postDO) {
		sql = "UPDATE post SET post_content='' WHERE post_content=''";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postDO.getContent());
			pstmt.executeUpdate();
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
	}
	
	public void delete(PostDO postDO) {
		sql = "DELETE FROM post WHERE post_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postDO.getPostId());
			pstmt.executeUpdate();
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
	}
	
	/*
	public int recommend(PostDO postDO) {
		+ recommend(int postId, int userId, boolean like): int
	}
	
	public int report(PostDO postDO) {
		+ report(int postId, int userId): int
	}
	*/
}
