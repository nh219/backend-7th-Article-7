package mvc.modelpost;

import java.time.LocalDateTime;

public class PostDisplayDO {
	
	private String title;
	private String nickname;
	private LocalDateTime postRegistTime;
	
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
	
	public LocalDateTime getPostRegistTime() {
		return postRegistTime;
	}
	
	public void setPostRegistTime(LocalDateTime postRegistTime) {
		this.postRegistTime = postRegistTime;
	}
}
