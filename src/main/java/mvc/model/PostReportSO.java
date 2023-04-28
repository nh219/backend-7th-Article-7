package mvc.model;

public class PostReportSO {

	PostDao postDao = new PostDao();
	PostDO postDO = new PostDO();
	
	public PostReportSO() {
		
	}
	
	public PostReportSO(PostDao postDao) {
	}
	
	public int report(PostSearchCommand command) {
		return 0;
	}
}
