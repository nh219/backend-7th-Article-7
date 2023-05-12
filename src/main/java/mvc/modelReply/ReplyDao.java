package mvc.modelReply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReplyDao {
	
	private static long nextId = 0;
	private Map<Long, ReplyDO> replys;
	
	public ReplyDao() {
		this.replys = new HashMap<>();
	}
	
	public List<ReplyDO> readReply(long postId){
		List<ReplyDO> list = null;
		
		return list;
	}
	
	public void insert(ReplyDO replyDO) {
		replyDO.setReplyId(++ReplyDao.nextId);
		
		replys.put(replyDO.getReplyId(), replyDO);
	}
}
