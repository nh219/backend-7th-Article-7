package mvc.model;

public class RegistCommand {
	
	private String email;
	private String password;
	private String confirmPassword;
	private String nickname;
	
	public RegistCommand() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/* js로 처리
	public boolean isPasswordEqualToConfirmPassword() {
		
		return this.password.equals(this.confirmPassword);
	}
	*/
	
}
