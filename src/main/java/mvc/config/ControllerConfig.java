package mvc.config;
//Controller들을 등록하는 클래스

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import api.MatchInfo;
import api.MatchInfoDAO;
import api.MatchInfoDO;
import api.MostChampion;
import api.ParticipantInfoDAO;
import api.ParticipantInfoDO;
import api.RiotGamesAPIExample;
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
	public ParticipantInfoDO participantInfo() {
		return new ParticipantInfoDO();
	}
	
	@Bean
	public MostChampion mostChampion() {
		return new MostChampion();
	}
	
	@Bean
	public ParticipantInfoDAO participantInfoDAO() {
		return new ParticipantInfoDAO();
	}
	
	@Bean
	public MatchInfoDO matchInfoDO() {
		return new MatchInfoDO();
	}
}


