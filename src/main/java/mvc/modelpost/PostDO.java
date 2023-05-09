package mvc.modelpost;

import java.time.LocalDateTime;
import java.util.Date;

public class PostDO {
	
	private long postId;
	private String category;
	private String title;
	private String nickname;
	private String content;
	private int views;
	private int reportNum;
	private int likeNum;
	private int dislikeNum;
	private int notice;
	private String keyword;
	private Date postRegistTime;
	
	public PostDO() {
		
	}
	
	public PostDO(int postId, String category, String title, String nickname, String content, int views, 
				  int reportNum, int likeNum, int dislikeNum, int notice, Date postRegistTime) {
		this.postId = postId;
		this.category = category;
		this.title = title;
		this.nickname = nickname;
		this.content = content;
		this.views = views;
		this.reportNum = 0;
		this.likeNum = 0;
		this.dislikeNum = 0;
		this.notice = 0;
		this.postRegistTime = postRegistTime;
	}
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
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
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public Date getPostRegistTime() {
		return postRegistTime;
	}
	public void setPostRegistTime(Date postRegistTime) {
		this.postRegistTime = postRegistTime;
	}
		
}
