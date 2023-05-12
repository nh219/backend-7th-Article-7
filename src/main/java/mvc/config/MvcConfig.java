package mvc.config;

// SpringMvc에 대한 설정

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	public void addViewControllers(ViewControllerRegistry registry) {
		//	http://localhost:8080/backend-7th-Article-7/main
		registry.addViewController("/main").setViewName("main");
		
		//	http://localhost:8080/backend-7th-Article-7/register/step1
		registry.addViewController("/register/step1").setViewName("register/step1");
		
		// http://localhost:8080/backend-7th-Article-7/member/login
		registry.addViewController("/member/login").setViewName("member/login");

		//	http://localhost:8080/backend-7th-Article-7/member/memberUpdate
		registry.addViewController("/member/memberUpdate").setViewName("member/memberUpdate");
		
		//	http://localhost:8080/backend-7th-Article-7/post/community
		registry.addViewController("/post/community").setViewName("/post/community");
		
		//	http://localhost:8080/backend-7th-Article-7/post/postListTest
		registry.addViewController("/post/postListTest").setViewName("/post/postListTest");
		
		//	http://localhost:8080/backend-7th-Article-7/post/postReadTest
		registry.addViewController("/post/postReadTest").setViewName("/post/postReadTest");
		
		//	http://localhost:8080/backend-7th-Article-7/post/postWriteTest
		registry.addViewController("/post/postWriteTest").setViewName("/post/postWriteTest");
		
		//	http://localhost:8080/backend-7th-Article-7/post/postWriteProcess
		registry.addViewController("/post/postWriteProcess").setViewName("/post/postWriteProcess");
		
		//	http://localhost:8080/backend-7th-Article-7/post/postUpdateTest
		registry.addViewController("/post/postUpdateTest").setViewName("/post/postUpdateTest");
		
		//	http://localhost:8080/backend-7th-Article-7/post/postUpdate
		registry.addViewController("/post/postUpdate").setViewName("/post/postUpdate");
		
		//	http://localhost:8080/backend-7th-Article-7/post/replyWriteProcess
		registry.addViewController("/post/replyWriteProcess").setViewName("/post/replyWriteProcess");
		
	}
	
}
