package mvc.model;

public class PostRecommendSO {

	PostDao postDao = new PostDao();
	PostDO postDO = new PostDO();
	private int like;
	
	public PostRecommendSO() {
	}
	
	public PostRecommendSO(PostDao postDao) {		
		this.postDao = postDao;
	}
	
	public int recommend(PostRecommendCommand command) {
		int postId = command.getPostId();
		int userId = command.getUserId();
		int like = command.getLike();
		return 0;
	}
}
