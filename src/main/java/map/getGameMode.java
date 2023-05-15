package map;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class getGameMode {
	// QueueId 파싱 메서드
	public static String getGameMode(int queueId) {
		try {
			// 1. HTTP 요청 보내기
			String urlString = "https://static.developer.riotgames.com/docs/lol/queues.json";
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
			JsonArray queuesJsonArray = JsonParser.parseString(content.toString()).getAsJsonArray();
			Map<Integer, String> queueIdToGameMode = new HashMap<Integer, String>();
			for (JsonElement queueElement : queuesJsonArray) {
				JsonObject queue = queueElement.getAsJsonObject();
				int id = queue.get("queueId").getAsInt();
				JsonElement descriptionElement = queue.get("description");
				String gameMode = descriptionElement.isJsonNull() ? null : descriptionElement.getAsString();
				queueIdToGameMode.put(id, gameMode);
			}

			// 4. queueId 매핑 정보를 이용하여 게임 모드 이름 반환
			String gameMode = queueIdToGameMode.get(queueId);
			if (gameMode == null) {
				return "해당하는 게임 모드가 없습니다.";
			}
			return gameMode;

		} catch (IOException e) {
			return "HTTP 요청 오류: " + e.getMessage();
		}
	}

}
