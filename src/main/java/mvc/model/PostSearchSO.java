package mvc.model;

public class PostSearchSO {

	PostDao postDao = new PostDao();
	PostDO postDO = new PostDO();
	
	public PostSearchSO() {
	}
	
	public PostSearchSO(PostDao postDao) {
	}
	
	public int search(PostSearchCommand command) {
		return 0;
	}
}
