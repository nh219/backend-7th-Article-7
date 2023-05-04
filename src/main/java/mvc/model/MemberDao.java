package mvc.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemberDao {

	private static long nextId = 0;	
	// id는 회원이 추가될 때마다 1씩 증가되도록 설정할 것. 그 id값을 관리하는 변수
	private Map<String, Member> members;
	
	public MemberDao() {
		this.members = new HashMap<>();		
	}
	
	public Member selectByEmail(String email) {
		// email에 해당하는 key값, member 객체를 반환
		return members.get(email);
	}
	
	public void insert(Member member) {		// 매개변수로 오는 멤버는 새로운 멤버.
		member.setId(++MemberDao.nextId);
		// nextId 값을 1 증가 시키고 setId로 member에 저장.
		members.put(member.getEmail(), member);
		// 멤버 객체를 매개변수로 받아, 해당 멤버 객체의 이메일까지 해서 members에 저장.
	}
	
	public void update(Member member) { 	// 매개변수로 오는 멤버는 변경된 정볼르 담고 있는 멤버.
		
		members.put(member.getEmail(), member);
	}

	public void delete(Member member) { 	
		
		members.put(member.getEmail(), member);
	}
	
	public int nameChk(String nickname) throws Exception{
		int result = 0;
		
		return result;
	}
}
