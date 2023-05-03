package mvc.model;

public class PostRegistSO {

	PostDao postDao = new PostDao();
	
	public PostRegistSO() {
	}
	
	public PostRegistSO(PostDao postDao) {
		this.postDao = postDao;

	}
	
	public void regist(PostRegistCommand command) {
		int userId = command.getUserId();
		String title = command.getTitle();
		String content = command.getContent();
	}
}
