package match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MatchInfoController {

    @Autowired
    private MatchInfoService matchInfoService;

    @GetMapping("/match-info/{matchId}")
    public String getMatchInfoById(@PathVariable String matchId, Model model) {
        MatchInfoDto matchInfo = matchInfoService.getMatchInfoById(matchId);
        model.addAttribute("matchInfo", matchInfo);
        return "match-info";
    }
}
