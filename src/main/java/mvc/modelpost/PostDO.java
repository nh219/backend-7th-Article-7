package mvc.modelpost;

import java.util.Date;

public class PostDO {
	
	private int postId;
	private String category;
	private String title;
	private int memberId;
	private String content;
	private int views;
	private int reportNum;
	private int likeNum;
	private int dislikeNum;
	private int notice;
	private Date post_date;
	
	public PostDO(int postId, String category, String title, int memberId, String content, int views, 
				  int reportNum, int likeNum, int dislikeNum, int notice, Date post_date) {
		this.postId = postId;
		this.category = category;
		this.title = title;
		this.memberId = memberId;
		this.content = content;
		this.views = views;
		this.reportNum = 0;
		this.likeNum = 0;
		this.dislikeNum = 0;
		this.notice = 0;
		this.post_date = post_date;
	}
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
	public int getReportNum() {
		return reportNum;
	}
	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}
	
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	
	public int getDislikeNum() {
		return dislikeNum;
	}
	public void setDislikeNum(int dislikeNum) {
		this.dislikeNum = dislikeNum;
	}
	
	public int getNotice() {
		return notice;
	}
	public void setNotice(int notice) {
		this.notice = notice;
	}
	
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
		
}
