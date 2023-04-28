package mvc.model;

import java.time.LocalDateTime;

import mvc.exception.DuplicateMemberException;
import mvc.exception.MemberNotFoundException;

public class MemberRegisterService {
	// 서비스 오브젝트. 서비스 오브젝트는 단편적인 기능을 구한하기보다는 프로그램에서 제공하는, 실제적으로든 내부적으로든 
	// 여러 단위 작업들을 묶어서 제공. 
	
	private MemberDao memberDao;
	
	public MemberRegisterService() {
		
	}
	
	public MemberRegisterService(MemberDao memberDao) {		// 생성자를 통해 MemberDao 객체 주입.
		this.memberDao = memberDao;
	}
	
	public long regist(RegistCommand req) {		// 실제 회원 등록을 수행하는 메서드
		Member member = memberDao.selectByEmail(req.getEmail());
		
		if(member != null) { 		// 동일한 이메일을 갖는 회원이 존재하는지, 중복 검사를 먼저 수행
			throw new DuplicateMemberException();
		}
		
		member = new Member(req.getEmail(), req.getPassword(), req.getNickname(), LocalDateTime.now()); 
		memberDao.insert(member);
		
		return member.getId();			// 생성된 member의 nextId를 반환
	}
}
