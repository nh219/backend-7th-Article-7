package map;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
@Component
public class getMap {
	// mapId 파싱 메서드
	public static String getMap(long mapId) {
		try {
			// 1. HTTP 요청 보내기
			String urlString = "https://static.developer.riotgames.com/docs/lol/maps.json";
			URL url = new URL(urlString);
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
			JsonArray mapJsonArray = JsonParser.parseString(content.toString()).getAsJsonArray();
			Map<Long, String> mapIdToMap = new HashMap<Long, String>();
			for (JsonElement mapElement : mapJsonArray) {
				JsonObject mapObject = mapElement.getAsJsonObject();
				long id = mapObject.get("mapId").getAsLong();
				JsonElement descriptionElement = mapObject.get("mapName");
				String mapName = (descriptionElement == null || descriptionElement.isJsonNull()) ? null
						: descriptionElement.getAsString();
				mapIdToMap.put(id, mapName);
			}

			// 4. mapId 매핑 정보를 이용하여 맵 이름 반환
			String mapName = mapIdToMap.get(mapId);
			if (mapName == null) {
				return "해당하는 맵이 없습니다.";
			}
			return mapName;

		} catch (IOException e) {
			return "HTTP 요청 오류: " + e.getMessage();
		}
	}

}
