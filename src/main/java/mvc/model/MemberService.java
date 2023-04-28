package mvc.model;

import java.util.Collection;

public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public boolean checkLoginAuth(LoginCommand login) {
		boolean result = false;
		Member member = memberDao.selectByEmail(login.getEmail());
		
		if(member != null && member.checkPassword(login.getPassword())) {
			result = true;
		}
		
		return result;
	}
	
	public Member findMember(String email) {
		return memberDao.selectByEmail(email);
	}
}
