package mvc.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import mvc.modelpost.*;

@Configuration
@EnableTransactionManagement 
public class PostConfig {
    
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
    public PostDaoSpring postDaoSpring() {
        return new PostDaoSpring(dataSource());
    }
    
    @Bean
    public PostChangeSO postChangeSvc() {
        return new PostChangeSO(this.postDaoSpring());
    }

    @Bean
    public PostRecommendSO postRecSvc() {
        return new PostRecommendSO(this.postDaoSpring());
    }
    
    @Bean
    public PostRegistSO postRegSvc() {
        return new PostRegistSO(this.postDaoSpring());
    }
    
    @Bean
    public PostReportSO postReportSvc() {
        return new PostReportSO(this.postDaoSpring());
    }
    
    @Bean
    public PostSearchSO postSearchSvc() {
        return new PostSearchSO(this.postDaoSpring());
    }
    
    @Bean
    public NoticeSO noticeSvc() {
        return new NoticeSO(this.postDaoSpring());
    }
    
}
