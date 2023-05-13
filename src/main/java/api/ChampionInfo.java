package api;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import api.MatchInfo;
import api.ParticipantInfoDO;
import api.RiotGamesAPIExample;

public class ChampionInfo {
    
    private RiotGamesAPIExample riotGamesAPIExample;
    private MatchInfo matchInfo;
    
    public ChampionInfo(RiotGamesAPIExample riotGamesAPIExample, MatchInfo matchInfo) {
        this.riotGamesAPIExample = riotGamesAPIExample;
        this.matchInfo = matchInfo;
    }
    
    public String getSelectedChampion(String summonerName) {
        String selectedChampion = null;
        
        try {
            // ��ȯ�� ���� ��������
            SummonerDO summonerDO = riotGamesAPIExample.summonerInfo(summonerName);
            
            // ��ȯ���� �ֱ� ���� ��� ��������
			List<String> matchIds = matchInfo.matchIds(summonerDO);
            
            // �ֱ� ���� ��� �� ������ è�Ǿ� ����
            for (String matchId : matchIds) {
                JsonObject matchObject = matchInfo.matchInfo(matchId);
                JsonArray participantsArray = matchObject.getAsJsonObject("info").getAsJsonArray("participants");
                
                for (JsonElement participantElement : participantsArray) {
                    JsonObject participantObject = participantElement.getAsJsonObject();
                    String participantSummonerName = participantObject.get("summonerName").getAsString();
                    
                    if (participantSummonerName.equalsIgnoreCase(summonerName)) {
                        selectedChampion = participantObject.get("championName").getAsString();
                        break;
                    }
                }
                
                if (selectedChampion != null) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return selectedChampion;
    }
}
