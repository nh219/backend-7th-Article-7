ackage match;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Repository
public class MatchInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveMatchInfo(MatchInfoDto matchInfo) {
        String sql = "INSERT INTO match_info (match_id, game_duration, participants) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, matchInfo.getMatchId(), matchInfo.getGameDuration(), matchInfo.getParticipants());
    }

    public MatchInfoDto getMatchInfoById(String matchId) {
        String sql = "SELECT * FROM match_info WHERE match_id = ?";
        MatchInfoDto matchInfo = jdbcTemplate.queryForObject(sql, new Object[]{matchId}, new MatchInfoRowMapper());
        return matchInfo;
    }

    public class MatchInfoRowMapper implements RowMapper<MatchInfoDto> {
    	@Override
    	public MatchInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
    	    MatchInfoDto matchInfo = new MatchInfoDto();
    	    matchInfo.setMatchId(rs.getString("match_id"));
    	    matchInfo.setGameDuration(rs.getInt("game_duration"));
    	    
    	    String participantJson = rs.getString("participants");
    	    List<ParticipantDto> participants = new Gson().fromJson(participantJson, new TypeToken<List<ParticipantDto>>() {}.getType());
    	    matchInfo.setParticipants(participants);
    	    
    	    return matchInfo;
    		}
        }
    }
