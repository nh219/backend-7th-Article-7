package mvc.model;

public class PostSearchSO {

	private PostDao postDao;
	private PostDO postDO;
	
	public PostSearchSO() {
	}
	
	public PostSearchSO(PostDao postDao) {
		this.postDao = postDao;
        this.postDO = new PostDO();
	}
	
	public int search(PostSearchCommand command) {
		String category = command.getCategory();
		String keyword = command.getKeyword();
		return 0;
	}
}
