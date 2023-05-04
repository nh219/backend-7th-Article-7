package mvc.model;

public class NoticeCommand {

	private int userId;
	private int postId;
	private int notice;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getPostId() {
		return postId;
	}
	
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public int getNotice() {
		return notice;
	}
	
	public void setNotice(int notice) {
		this.notice = notice;
	}
	
}
