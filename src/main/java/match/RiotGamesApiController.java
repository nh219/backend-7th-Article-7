package match;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import api.MatchInfoDO;

@RestController
public class RiotGamesApiController<InfoDto> {

    @Autowired
    private MatchInfoService matchInfoService;

    @PostMapping("/saveMatchInfo")
    public String saveMatchInfo(@RequestBody MatchInfoDO matchInfo) {
        long matchId = Long.parseLong(matchInfo.getMatchId());
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://kr.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=<API_KEY>";
        ResponseEntity<InfoDto> response = restTemplate.getForEntity(url, InfoDto.class);
        InfoDto infoDto = response.getBody();

        MatchInfoDO savedMatchInfoDto = new MatchInfoDO();
        savedMatchInfoDto.setMatchId(matchInfo.getMatchId());


        return "redirect:/match-info/" + matchId;
    }
}
