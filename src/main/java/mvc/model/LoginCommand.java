package mvc.model;

public class LoginCommand {
	
	private String nickname;
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginCommand [nickname=" + nickname + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
