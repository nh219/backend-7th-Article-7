package match;

public class ParticipantDto {
    private Long participantId;
    private Long championId;
    private Long teamId;
    
	public Long getParticipantId() {
		return participantId;
	}
	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}
	public Long getChampionId() {
		return championId;
	}
	public void setChampionId(Long championId) {
		this.championId = championId;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

}