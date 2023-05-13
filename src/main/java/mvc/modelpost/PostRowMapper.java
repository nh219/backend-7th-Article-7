package mvc.modelpost;

import java.sql.*;
import org.springframework.jdbc.core.RowMapper;

public class PostRowMapper implements RowMapper<PostDO> {
	
	@Override
	public PostDO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PostDO post = new PostDO();
		post = new PostDO();
		post.setPostId(rs.getLong("post_id"));
		post.setCategory(rs.getString("category"));
		post.setTitle(rs.getString("title"));
		post.setNickname(rs.getString("nickname"));
		post.setPostRegistTime(rs.getTimestamp("post_date"));
		post.setContent(rs.getString("post_content"));
		post.setViews(rs.getInt("views"));
		
		return post;
	}
}