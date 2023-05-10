package mvc.config;
//Controller들을 등록하는 클래스

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mvc.controller.MemberController;
import mvc.controller.PostController;
import mvc.controller.RegisterController;
import mvc.model.MemberRegisterService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberRegisterService memberRegisterService;
	
	@Bean
	public RegisterController registerController() {
		RegisterController rc =  new RegisterController();
		rc.setMemberRegisterService(memberRegisterService);
		
		return rc;
	}
	
	@Bean
	public MemberController memberController() {
		return new MemberController();
	}
	
	@Bean
	public PostController postController() {
		return new PostController();
	}
}
