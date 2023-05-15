package mvc.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import api.MatchInfoDO;
import api.MostChampion;
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

		Map<String, MatchInfoDO> matchInfoMap = new HashMap<>();
		for (String matchId : matchIds) {
			List<MatchInfoDO> matchInfoList = matchInfoDAO.MatchInfoDB(matchId);
			for (MatchInfoDO matchInfo : matchInfoList) {
				matchInfoMap.put(matchId, matchInfo);
			}
		}
		model.addAttribute("matchInfoMap", matchInfoMap);

		Map<String, List<ParticipantInfoDO>> participantInfoMap = new HashMap<>();
		for (String matchId : matchIds) {
		    List<ParticipantInfoDO> matchParticipants = participantInfoDAO.getParticipantInfoByMatchId(matchId);
		    participantInfoMap.put(matchId, matchParticipants);
		}
		model.addAttribute("participantInfoMap", participantInfoMap);


		Map<String, Integer> mostPlayedChampions = mostChampion.findMostPlayedChampions(summonerName);
		model.addAttribute("mostPlayedChampions", mostPlayedChampions);

		Map<String, Integer> winRates = mostChampion.calculateWinRate(summonerName);
		model.addAttribute("winRates", winRates);
		
		return "summoner";
	}
}
