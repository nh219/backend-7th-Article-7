package mvc.model;

public class PostSearchSO {

	PostDao postDao = new PostDao();
	PostDO postDO = new PostDO();
	
	public PostSearchSO() {
	}
	
	public PostSearchSO(PostDao postDao) {
	}
	
	public int search(PostSearchCommand command) {
		String category = command.getCategory();
		String keyword = command.getKeyword();
		return 0;
	}
}
