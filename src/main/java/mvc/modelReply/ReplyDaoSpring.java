package mvc.modelReply;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


public class ReplyDaoSpring extends ReplyDao {
	
	private String sql = null;
	private JdbcTemplate jdbcTemplate;
	
	public ReplyDaoSpring(DataSource dstm) {
		jdbcTemplate = new JdbcTemplate(dstm);
	}
	
	@Override
	public List<ReplyDO> readReply(long postId) {
    	List<ReplyDO> list = null;
    	
    	this.sql = "select * from reply where post_Id = ?";
		
		try {
			list = jdbcTemplate.query(this.sql, new ReplyRowMapper(), postId);
		}
		catch(EmptyResultDataAccessException e) {
		}
    	
		return list;
    }
	
	@Override
	public void insert(ReplyDO replyDO) {	
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new ReplyPreparedStatementCreator(replyDO, new String[] {"REPLY_ID"}), keyHolder);
		
		Number keyValue = keyHolder.getKey();
		replyDO.setPostId(keyValue.longValue());
	}
}
