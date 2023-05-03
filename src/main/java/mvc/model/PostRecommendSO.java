package mvc.model;

public class PostRecommendSO {

	private PostDao postDao;
	private PostDO postDO;
	private int like;
	
	public PostRecommendSO() {
	}
	
	public PostRecommendSO(PostDao postDao) {		
		this.postDao = postDao;
        this.postDO = new PostDO();
	}
	
	public int recommend(PostRecommendCommand command) {
		int postId = command.getPostId();
		int userId = command.getUserId();
		int like = command.getLike();
		return 0;
	}
}
