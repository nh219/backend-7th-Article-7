package mvc.modelReply;

import java.util.Date;
import java.util.List;


public class ReplyService {
	
	private ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	
	public List<ReplyDO> readReply(long postId){
		List<ReplyDO> list = replyDao.readReply(postId);
		
		return list;
	}
	
	public long regist(ReplyDO replyDO) {	
		Date date = new Date();
		
		ReplyDO reply = new ReplyDO(replyDO.getReplyId(), replyDO.getPostId(), 0, replyDO.getNickname(), replyDO.getContent(),
						0, 0, 0,  date);
		
		replyDao.insert(reply);
		
		return reply.getReplyId();		
	}
}
