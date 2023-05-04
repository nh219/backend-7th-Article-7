package mvc.model;

public class PostChangeSO {

	private PostDaoSpring postDao;
	
	public PostChangeSO() {
	}
	
	public PostChangeSO(PostDaoSpring postDao) {
		this.postDao = postDao;
	}
	
	public void Update(PostUpdateCommand command) {
		int postId = command.getPostId();
		String title = command.getTitle();
		String content = command.getContent();
	}
	
	public void delete(int postId) {
		
	}
}
