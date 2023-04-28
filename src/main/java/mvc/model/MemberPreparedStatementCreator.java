package mvc.model;

import java.sql.*;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class MemberPreparedStatementCreator implements PreparedStatementCreator {
	private Member member;
	private String [] keyColumns;
	
	public MemberPreparedStatementCreator(Member member) {
		this(member, new String[] {});
	}
	
	public MemberPreparedStatementCreator(Member member, String[] keyColumns) {
		this.member = member;
		this.keyColumns = keyColumns;
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
		String sql = "insert into member (member_id, email, nickname, password) values (member_id_seq.nextval, ?, ?, ?)";
		PreparedStatement pstmt = null;
		
		if(keyColumns.length > 0) {
			pstmt = conn.prepareStatement(sql, keyColumns);
		}
		else {
			pstmt = conn.prepareStatement(sql);
		}
		
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getNickname());
		pstmt.setString(3, member.getPassword());
		
		return pstmt;
	}
	


}
