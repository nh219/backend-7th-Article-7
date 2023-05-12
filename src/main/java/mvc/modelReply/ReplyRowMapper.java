package mvc.modelReply;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mvc.modelpost.PostDO;

public class ReplyRowMapper implements RowMapper<ReplyDO> {
	
	@Override
	public ReplyDO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReplyDO reply = new ReplyDO();
		reply = new ReplyDO();
		reply.setPostId(rs.getLong("post_id"));
		reply.setReplyId(rs.getLong("reply_id"));
		reply.setNickname(rs.getString("nickname"));
		reply.setContent(rs.getString("reply_content"));
		reply.setReplyRegistTime(rs.getTimestamp("reply_date"));
		
		return reply;
	}
}
