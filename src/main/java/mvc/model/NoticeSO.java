package mvc.model;

public class NoticeSO {

	private PostDaoSpring postDao;
	
	public NoticeSO() {
	}
	
	public NoticeSO(PostDaoSpring postDao) {
		this.postDao = postDao;
	}
	
	public void notice(NoticeCommand command) {
		int userId = command.getUserId();
		int notice = command.getNotice();
	}
}
