package mvc.model;

public class PostSearchSO {

	private PostDaoSpring postDao;
	private PostDO postDO;
	
	public PostSearchSO() {
	}
	
	public PostSearchSO(PostDaoSpring postDao) {
		this.postDao = postDao;
        this.postDO = new PostDO();
	}
	
	public int search(PostSearchCommand command) {
		String category = command.getCategory();
		String keyword = command.getKeyword();
		return 0;
	}
}
