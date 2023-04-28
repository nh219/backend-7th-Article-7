package mvc.model;

import java.sql.*;
import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {
	
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member = new Member();
		member.setId(rs.getLong("member_id"));
		member.setEmail(rs.getString("email"));
		member.setPassword(rs.getString("password"));
		member.setNickname(rs.getString("nickname"));
		member.setRegisterDateTime(rs.getTimestamp("sign_up_date").toLocalDateTime());
		
		return member;
	}
}
