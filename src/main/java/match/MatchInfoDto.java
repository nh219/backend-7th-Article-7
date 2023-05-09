ackage match;

import java.util.List;

public class MatchInfoDto {
	private String matchId;
	private int gameDuration;
	private List<ParticipantDto> participants;
	
	public String getMatchId() {
		return matchId;
	}
	public void setMatchId(String matchId) {
		this.matchId = matchId;
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