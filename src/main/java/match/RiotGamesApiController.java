package match;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RiotGamesApiController {

    @Autowired
    private MatchInfoService matchInfoService;

    @PostMapping("/saveMatchInfo")
    public String saveMatchInfo(@RequestBody MatchInfoDto matchInfo) {
        long matchId = Long.parseLong(matchInfo.getMatchId());
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kr.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=<API_KEY>";
        ResponseEntity<InfoDto> response = restTemplate.getForEntity(url, InfoDto.class);
        InfoDto infoDto = response.getBody();

        MatchInfoDto savedMatchInfoDto = new MatchInfoDto();
        savedMatchInfoDto.setMatchId(matchInfo.getMatchId());
        savedMatchInfoDto.setGameDuration(infoDto.getGameDuration());
        savedMatchInfoDto.setParticipants(infoDto.getParticipants());

        matchInfoService.saveMatchInfo(savedMatchInfoDto);

        return "redirect:/match-info/" + matchId;
    }
}
