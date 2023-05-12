package mvc.config;

import java.sql.SQLException;

// 모델들에 대한 설정 클래스. 모델들을 Spring Container로 등록시키는 작업 수행.

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mvc.model.*;
import mvc.modelpost.PostDaoSpring;
import mvc.modelpost.PostService;
import oracle.jdbc.pool.OracleDataSource;

@Configuration
@EnableTransactionManagement
public class MemberConfig {
	
//	@Bean
//	public OracleDataSource dataSource() {
//		OracleDataSource ds=null;
//		try {
//			ds = new OracleDataSource();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//        ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
//		ds.setUser("system");
//		ds.setPassword("a1234");
//		
//		return ds;
//	}
//	
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
//		dstm.setDataSource(dataSource());
//		
//		return dstm;
//	}
	
//	@Bean
//	public MemberDaoSpring memberDao() {
//		return new MemberDaoSpring(datasource());
//	}

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(this.memberDao());
	}
	
	@Bean
	public MemberService memberSvc() {
		return new MemberService(this.memberDao());
	}
	
	@Bean
	public MemberUpdateService memberUpdateSvc() {
		MemberUpdateService updateSvc = new MemberUpdateService();
		updateSvc.setMemberDao(this.memberDao());
		
		return updateSvc;
	}
	
}
