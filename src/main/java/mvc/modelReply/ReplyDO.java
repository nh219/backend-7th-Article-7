package mvc.modelReply;

import java.util.Date;

public class ReplyDO {
	
	private long replyId;
	private long postId;
	private long parentReplyId;
	private String nickname;
	private String content;
	private int reportNum;
	private int likeNum;
	private int dislikeNum;
	private Date replyRegistTime;
	
	public ReplyDO() {
		
	}
	
	public ReplyDO(long replyId, long postId, long parentReplyId, String nickname, String content, 
					int reportNum, int likeNum, int dislikeNum, Date replyRegistTime) {
		
	}
	
	public long getReplyId() {
		return replyId;
	}
	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getParentReplyId() {
		return parentReplyId;
	}
	public void setParentReplyId(long parentReplyId) {
		this.parentReplyId = parentReplyId;
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
	public Date getReplyRegistTime() {
		return replyRegistTime;
	}
	public void setReplyRegistTime(Date replyRegistTime) {
		this.replyRegistTime = replyRegistTime;
	}
}
