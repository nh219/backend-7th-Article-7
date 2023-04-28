package mvc.model;

public class PostRecommendSO {

	PostDao postDao = new PostDao();
	PostDO postDO = new PostDO();
	private int like;
	
	public PostRecommendSO() {
	}
	
	public PostRecommendSO(PostDao postDao) {		
	}
	
	public int recommend(PostRecommendCommand command) {
		return 0;
	}
}
