package mvc.config;
//Controller들을 등록하는 클래스

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import api.MatchInfo;
import api.MatchInfoDAO;
import api.MostChampion;
import api.ParticipantInfo;
import api.RiotGamesAPIExample;
import mvc.controller.MemberController;
import mvc.controller.RegisterController;
import mvc.model.MemberRegisterService;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberRegisterService memberRegisterService;
	private RestTemplate restTemplate;
	
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
	public RiotAPIController riotAPIController() {
	    return new RiotAPIController();
	}
	
	@Bean
	public RiotGamesAPIExample riotGamesAPIExample() {
		return new RiotGamesAPIExample();
	}

	@Bean
	public MatchInfo matchInfo() {
		return new MatchInfo();
	}
	
	@Bean
	public MatchInfoDAO matchInfoDAO() {
		return new MatchInfoDAO();
	}
	
	@Bean
	public ParticipantInfo participantInfo() {
		return new ParticipantInfo();
	}
	
	@Bean
	public MostChampion mostChampion() {
		return new MostChampion();
	}
	
	
}
