package mvc.model;

public class PostRecommendSO {

	PostDao postDao = new PostDao();
	PostDO postDO = new PostDO();
	private int like;
	
	public void PostRecommendSO() {
		
	}
	
	public void PostRecommendSO(PostDao postDao) {		
		
	}
	
	public int Recommend(PostRecommendCommand command) {
		return 0;
	}
}
