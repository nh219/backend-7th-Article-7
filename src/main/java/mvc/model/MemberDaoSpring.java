package mvc.model;

import java.sql.*;
import java.util.*;
import javax.sql.*;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import mvc.exception.MemberNotFoundException;

public class MemberDaoSpring extends MemberDao{
	
	private JdbcTemplate jdbcTemplate;
	private String sql = null;
	
	public MemberDaoSpring(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@Override
	public Member selectByEmail(String email) {
		this.sql = "select * from member where email = ?";
		Member member = null;
		
		try {
			member = jdbcTemplate.queryForObject(this.sql, new MemberRowMapper(), email);
		}
		catch(EmptyResultDataAccessException e) {
			// throw new MemberNotFoundException();
		}
		
		return member;
	}
	
	@Override
	public void insert(Member member) {		// 매개변수로 오는 멤버는 새로운 멤버.
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new MemberPreparedStatementCreator(member, new String[] {"MEMBER_ID"}), keyHolder);
		
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
	
	@Override
	public void update(Member member) {		// 매개변수로 오는 멤버는 변경된 정볼르 담고 있는 멤버.
		sql = "update member set password = ? where email = ?";
		jdbcTemplate.update(sql, member.getPassword(), member.getEmail());
		
	}
	
	@Override
	public void delete(Member member) {
		sql = "delete from member where email = ?";
		jdbcTemplate.update(sql, member.getEmail());
		
	}
}
