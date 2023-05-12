package mvc.modelReply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import mvc.modelpost.PostDO;

public class ReplyPreparedStatementCreator implements PreparedStatementCreator {
	private ReplyDO replyDO;
	private String [] keyColumns;
	
	public ReplyPreparedStatementCreator(ReplyDO replyDO) {
		this(replyDO, new String[] {});
	}
	
	public ReplyPreparedStatementCreator(ReplyDO replyDO, String[] keyColumns) {
		this.replyDO = replyDO;
		this.keyColumns = keyColumns;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
		String sql = "insert into reply (reply_id, nickname, reply_content, post_id) values (reply_reply_id_seq.nextval, ?, ?, ?)";
		PreparedStatement pstmt = null;
		
		if(keyColumns.length > 0) {
			pstmt = conn.prepareStatement(sql, keyColumns);
		}
		else {
			pstmt = conn.prepareStatement(sql);
		}
		
		pstmt.setString(1, replyDO.getNickname());
		pstmt.setString(2, replyDO.getContent());
		pstmt.setLong(3, replyDO.getPostId());
		
		return pstmt;
	}
}
