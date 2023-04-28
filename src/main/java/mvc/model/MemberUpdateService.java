package mvc.model;

import org.springframework.transaction.annotation.Transactional;

import mvc.exception.MemberNotFoundException;
import mvc.exception.WrongIdPasswordException;

public class MemberUpdateService {

	private MemberDao memberDao;
	
	public MemberUpdateService() {
		
	}
	
	public void setMemberDao(MemberDao memberDao) {		// setter를 통해 MemberDao 객체 주입.
		this.memberDao = memberDao;
	}
	
	@Transactional
	public void memberUpdate(MemberUpdateCommand command) {
		Member member = memberDao.selectByEmail(command.getEmail());
		
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		if(command.getPassword().equals(command.getNewPassword())) {
			throw new WrongIdPasswordException();
			// oldPassword가 newPassword가 같으면 예외 발생
		}
		
		if(command.getNickname() != null) {
			member.changeNickname(member.getNickname(), command.getNickname());
		}
		
		member.changePassword(command.getPassword(), command.getNewPassword());
		memberDao.update(member);
		
	}
}
