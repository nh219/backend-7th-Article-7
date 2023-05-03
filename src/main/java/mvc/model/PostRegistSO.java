package mvc.model;

public class PostRegistSO {

	private PostDao postDao;
	
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
