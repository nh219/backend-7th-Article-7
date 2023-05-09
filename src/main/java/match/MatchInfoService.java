package match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchInfoService {

    @Autowired
    private MatchInfoDao matchInfoDao;

    public void saveMatchInfo(MatchInfoDto matchInfo) {
        matchInfoDao.saveMatchInfo(matchInfo);
    }

    public MatchInfoDto getMatchInfoById(String matchId) {
        return matchInfoDao.getMatchInfoById(matchId);
    }
}