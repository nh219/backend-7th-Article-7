package mvc.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonSyntaxException;

import api.LeagueDO;
import api.MatchInfo;
import api.MatchInfoDAO;
import api.MostChampion;
import api.MyInfoDO;
import api.ParticipantInfoDO;
import api.ParticipantInfoDAO;
import api.RiotGamesAPIExample;
import api.SummonerDO;

@Controller
public class RiotAPIController {

	@Autowired
	private RiotGamesAPIExample riotGamesAPIExample;

	@Autowired
	private MatchInfo matchInfo;

	@Autowired
	private MostChampion mostChampion;

	@Autowired
	private ParticipantInfoDAO participantInfoDAO;
	
	@Autowired
	private MatchInfoDAO matchInfoDAO;
	
	@GetMapping("/search")
	public String summoner(@RequestParam("summonerName") String summonerName, Model model) throws JsonSyntaxException, IOException, SQLException {
		SummonerDO summonerDO = riotGamesAPIExample.summonerInfo(summonerName);
		model.addAttribute("summoner", summonerDO);

		List<LeagueDO> leagueDO = riotGamesAPIExample.league(summonerDO);
		model.addAttribute("leagues", leagueDO);

		List<String> matchIds = matchInfo.matchIds(summonerDO);
		model.addAttribute("matchIds", matchIds);
		
		List<String> matchIds1 = matchInfo.matchIds(summonerDO);
		for (String matchId : matchIds1) {
			matchInfoDAO.matchInfo(matchId);
		}
		
		List<ParticipantInfoDO> participantInfoList = new ArrayList<>();
		for (String matchId : matchIds) {
			List<ParticipantInfoDO> matchParticipants = participantInfoDAO.getParticipantInfoByMatchId(matchId);
			participantInfoList.addAll(matchParticipants);
		}
		model.addAttribute("participantInfoList", participantInfoList);
	
		List<MyInfoDO> myInfo = new ArrayList<>();
		for (String matchId : matchIds) {
		    List<MyInfoDO> myMatchInfo = matchInfoDAO.myMatchInfoDB(matchId, summonerName);
		    myInfo.addAll(myMatchInfo);
		}
		model.addAttribute("myInfo", myInfo);


		Map<String, Integer> mostPlayedChampions = mostChampion.findMostPlayedChampions(summonerName);
		model.addAttribute("mostPlayedChampions", mostPlayedChampions);

		Map<String, Integer> winRates = mostChampion.calculateWinRate(summonerName);
		model.addAttribute("winRates", winRates);

		return "summoner";
		
	}
}
