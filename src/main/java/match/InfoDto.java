package match;

import java.util.List;

public class InfoDto {
    private String gameVersion;
    private String gameMode;
    private int gameDuration;
    private List<ParticipantDto> participants;
	public String getGameVersion() {
		return gameVersion;
	}
	public void setGameVersion(String gameVersion) {
		this.gameVersion = gameVersion;
	}
	public String getGameMode() {
		return gameMode;
	}
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	public int getGameDuration() {
		return gameDuration;
	}
	public void setGameDuration(int gameDuration) {
		this.gameDuration = gameDuration;
	}
	public List<ParticipantDto> getParticipants() {
		return participants;
	}
	public void setParticipants(List<ParticipantDto> participants) {
		this.participants = participants;
	}
}
