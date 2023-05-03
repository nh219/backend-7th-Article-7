package mvc.model;

public class NoticeSO {

	PostDao postDao = new PostDao();
	
	public NoticeSO() {
	}
	
	public NoticeSO(PostDao postDao) {
		this.postDao = postDao;
	}
	
	public void notice(NoticeCommand command) {
		int userId = command.getUserId();
		int notice = command.getNotice();
	}
}
