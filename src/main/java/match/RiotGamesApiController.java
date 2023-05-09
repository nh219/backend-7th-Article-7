package match;

import api.RiotGamesAPIExample;
import api.SummonerDO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RiotGamesApiController {

    @GetMapping("/search")
    public String searchSummoner(@RequestParam("summonerName") String summonerName, Model model) {
        try {
            String summonerDO = RiotGamesAPIExample.summonerInfo(summonerName);
            model.addAttribute("summonerDO", summonerDO);
            return "summonerInfo";
        } catch (Exception e) {
            System.out.println("���� �߻�: " + e.getMessage());
            model.addAttribute("errorMessage", "��ȯ�� ������ �������� �� ���ܰ� �߻��Ͽ����ϴ�.");
            return "errorPage";
        }
    }

}
