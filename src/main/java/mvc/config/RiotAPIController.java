package mvc.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import api.LeagueDO;
import api.MatchInfo;
import api.MatchInfoDAO;
import api.MostChampion;
import api.ParticipantInfo;
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
	
	@GetMapping("/search")
	public String summoner(@RequestParam("summonerName") String summonerName, Model model) throws JsonSyntaxException, IOException, SQLException {
		SummonerDO summonerDO = riotGamesAPIExample.summonerInfo(summonerName);
		model.addAttribute("summoner", summonerDO);
		
		List<LeagueDO> leagueDO = riotGamesAPIExample.league(summonerDO);
		model.addAttribute("leagues", leagueDO);
		
		List<String> matchIds = matchInfo.matchIds(summonerDO);
		model.addAttribute("matchIds", matchIds);
		
		Map<String, JsonObject> matchObjectMap = new HashMap<>();
		for (String matchId : matchIds) {
		    JsonObject matchObject = matchInfo.matchInfo(matchId);
		    matchObjectMap.put(matchId, matchObject);
		}
		model.addAttribute("matchObjects", matchObjectMap);

		Map<String, Integer> mostPlayedChampions = mostChampion.findMostPlayedChampions(summonerName);
	    model.addAttribute("mostPlayedChampions", mostPlayedChampions);
	    
	    Map<String, Integer> winRates = mostChampion.calculateWinRate(summonerName);
	    model.addAttribute("winRates", winRates);
		
		return "summoner";
	}
}
