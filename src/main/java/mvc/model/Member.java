package mvc.model;

import java.time.LocalDateTime;

import mvc.exception.WrongIdPasswordException;

public class Member {
	
	private Long id;
	private String email;
	private String password;
	private String nickname;
	private LocalDateTime registerDateTime;
	
	public Member() {
		
	}
	
	public Member(String email, String password, String nickname, LocalDateTime registerDateTime) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.registerDateTime = registerDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}

	public String getPassword() {
		return password;
	}

	public String getNickname() {
		return nickname;
	}

	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}
	
	public void changePassword(String oldPassword, String newPassword) {
		if(!this.password.equals(oldPassword)) {
			throw new WrongIdPasswordException();
			// 입력한 oldPassword가 기존의 password와 다르면 예외 발생
		}
		
		this.password = newPassword;
	}
	
	public void changeNickname(String oldNickname, String newNickname) {
		
		this.nickname = newNickname;
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
}
