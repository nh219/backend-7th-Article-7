package mvc.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mvc.model.*;

@Configuration
@EnableTransactionManagement // 트랜잭션 관리를 위해 사용됨을 명시하는 어노테이션
public class PostConfig {
    
    // DataSource 객체를 생성하는 Bean을 등록함
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
    
    // PlatformTransactionManager 객체를 생성하는 Bean을 등록함
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager dstm = new DataSourceTransactionManager();
        dstm.setDataSource(dataSource());
        return dstm;
    }
    
    // PostDaoSpring 객체를 생성하는 Bean을 등록함
    @Bean
    public PostDaoSpring postDaoSpring() {
        return new PostDaoSpring(dataSource());
    }
    
    // PostChangeSO 객체를 생성하는 Bean을 등록함
    @Bean
    public PostChangeSO postChangeSvc() {
        return new PostChangeSO(this.postDaoSpring());
    }

    // PostRecommendSO 객체를 생성하는 Bean을 등록함
    @Bean
    public PostRecommendSO postRecSvc() {
        return new PostRecommendSO(this.postDaoSpring());
    }
    
    // PostRegistSO 객체를 생성하는 Bean을 등록함
    @Bean
    public PostRegistSO postRegSvc() {
        return new PostRegistSO(this.postDaoSpring());
    }
    
    // PostReportSO 객체를 생성하는 Bean을 등록함
    @Bean
    public PostReportSO postReportSvc() {
        return new PostReportSO(this.postDaoSpring());
    }
    
    // PostSearchSO 객체를 생성하는 Bean을 등록함
    @Bean
    public PostSearchSO postSearchSvc() {
        return new PostSearchSO(this.postDaoSpring());
    }
    
    // NoticeSO 객체를 생성하는 Bean을 등록함
    @Bean
    public NoticeSO noticeSvc() {
        return new NoticeSO(this.postDaoSpring());
    }
    
}
