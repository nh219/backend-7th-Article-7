package mvc.config;

// 모델들에 대한 설정 클래스. 모델들을 Spring Container로 등록시키는 작업 수행.

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mvc.model.*;

@Configuration
@EnableTransactionManagement
public class MemberConfig {
	
	@Bean
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		ds.setInitialSize(2);
		ds.setMinIdle(3);
		ds.setMaxIdle(3);
		ds.setMaxActive(5);
		ds.setMinEvictableIdleTimeMillis(60000);
		ds.setTimeBetweenEvictionRunsMillis(5000);
		
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(dataSource());
		
		return dstm;
	}

	@Bean
	public MemberDaoSpring memberDao() {
		return new MemberDaoSpring(dataSource());
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
