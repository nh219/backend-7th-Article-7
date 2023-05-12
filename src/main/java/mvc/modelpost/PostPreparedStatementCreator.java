package mvc.modelpost;

import java.sql.*;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class PostPreparedStatementCreator implements PreparedStatementCreator {
	private PostDO postDO;
	private String [] keyColumns;
	
	public PostPreparedStatementCreator(PostDO postDO) {
		this(postDO, new String[] {});
	}
	
	public PostPreparedStatementCreator(PostDO postDO, String[] keyColumns) {
		this.postDO = postDO;
		this.keyColumns = keyColumns;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
		String sql = "insert into post (post_id, category, title, nickname, post_content) values (post_post_id_seq.nextval, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		
		if(keyColumns.length > 0) {
			pstmt = conn.prepareStatement(sql, keyColumns);
		}
		else {
			pstmt = conn.prepareStatement(sql);
		}
		
		//pstmt.setString(1, postDO.getCategory());
		pstmt.setString(1, postDO.getCategory());
		pstmt.setString(2, postDO.getTitle());
		pstmt.setString(3, postDO.getNickname());
		pstmt.setString(4, postDO.getContent());
		
		return pstmt;
	}
}