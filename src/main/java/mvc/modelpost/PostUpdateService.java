package mvc.modelpost;

import org.springframework.transaction.annotation.Transactional;

public class PostUpdateService {

	private PostDao postDao;
	
	public PostUpdateService() {
		
	}
	
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@Transactional
	public void memberUpdate(PostUpdateCommand command) {
		PostDO postDO = postDao.searchById(command.getPostId());
		
		postDO.setTitle(command.getTitle());
		postDO.setContent(command.getContent());
		
		postDao.update(postDO);
	}
	
	@Transactional
	public void memberWithdrawal(PostDO postDO) {
		
		postDao.delete(postDO);
	}
	
}