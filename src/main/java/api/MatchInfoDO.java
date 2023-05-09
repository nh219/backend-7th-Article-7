package api;

import com.google.gson.JsonArray;

public class MatchInfoDO {

	private String matchId;
	private String dataVersion;
	private long gameStartTimeStamp;
	private long gameDuration;
	private String gameType;
	private String gameMode;
	private int queueId;
	private int mapId;
	private JsonArray redTeamWin;
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}
	public String getDataVersion() {
		return dataVersion;
	}
	public void setDataVersion(String dataVersion) {
		this.dataVersion = dataVersion;
	}
	public long getGameStartTimeStamp() {
		return gameStartTimeStamp;
	}
	public void setGameStartTimeStamp(long gameStartTimeStamp) {
		this.gameStartTimeStamp = gameStartTimeStamp;
	}
	public long getGameDuration() {
		return gameDuration;
	}
	public void setGameDuration(long gameDuration) {
		this.gameDuration = gameDuration;
	}
	public String getGameType() {
		return gameType;
	}
	public void setGameType(String gameType) {
		this.gameType = gameType;
	}
	public String getGameMode() {
		return gameMode;
	}
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	public int getQueueId() {
		return queueId;
	}
	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public JsonArray isRedTeamWin() {
		return redTeamWin;
	}
	public void setRedTeamWin(JsonArray jsonArray) {
		this.redTeamWin = jsonArray;
	}

	
}
