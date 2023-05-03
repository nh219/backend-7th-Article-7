package mvc.model;

public class PostReportSO {

	PostDao postDao = new PostDao();
	PostDO postDO = new PostDO();
	
	public PostReportSO() {
	}
	
	public PostReportSO(PostDao postDao) {
		this.postDao = postDao;

	}
	
	public int report(PostReportCommand command) {
		int postId = command.getPostId();
		int userId = command.getUserId();
		return 0;
	}
}
