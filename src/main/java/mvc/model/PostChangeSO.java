package mvc.model;

public class PostChangeSO {

	private PostDao postDao;
	
	public PostChangeSO() {
	}
	
	public PostChangeSO(PostDao postDao) {
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
