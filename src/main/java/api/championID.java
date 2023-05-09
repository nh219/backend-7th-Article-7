package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class championID {// 챔피언 ID 맵
	private static Map<Long, String> championIdToName = new HashMap<Long, String>();

	// 챔피언 ID 맵핑
	public static String getChampionName(long championId) {
		try {
			// 1. HTTP 요청 보내기
			String championDataUrl = "https://ddragon.leagueoflegends.com/cdn/13.9.1/data/ko_KR/champion.json";
			URL url = new URL(championDataUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 2. HTTP 응답 처리하기
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				return "HTTP 응답 오류: " + responseCode;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// 3. JSON 데이터 파싱하기
			JsonObject champion = JsonParser.parseString(content.toString()).getAsJsonObject();
			JsonObject championData = champion.getAsJsonObject("data");
			for (Map.Entry<String, JsonElement> entry : championData.entrySet()) {
				JsonObject championObject = entry.getValue().getAsJsonObject();
				if (championObject.get("key").getAsString().equals(String.valueOf(championId))) {
					String championName = championObject.get("name").getAsString();
					championIdToName.put(championId, championName);
					return championName;
				}
			}

			return "해당하는 챔피언이 없습니다.";
		} catch (IOException e) {
			return "HTTP 요청 오류: " + e.getMessage();
		}
	}

}
