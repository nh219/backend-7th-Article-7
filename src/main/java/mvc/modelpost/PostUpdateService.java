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
	public void postUpdate(PostDO postDO) {
		PostDO updatedPostDO = postDao.searchById(postDO.getPostId());
		
		updatedPostDO.setTitle(postDO.getTitle());
		updatedPostDO.setContent(postDO.getContent());
		
		postDao.update(updatedPostDO);
	}
	
	@Transactional
	public void postDelete(PostDO postDO) {
		
		postDao.delete(postDO);
	}
	
}