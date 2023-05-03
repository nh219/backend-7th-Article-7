package mvc.model;

public class PostReportSO {

	private PostDaoSpring postDao;
	private PostDO postDO;
	
	public PostReportSO() {
	}
	
	public PostReportSO(PostDaoSpring postDao) {
		this.postDao = postDao;
        this.postDO = new PostDO();
	}
	
	public int report(PostReportCommand command) {
		int postId = command.getPostId();
		int userId = command.getUserId();
		
		return 0;
	}
}
