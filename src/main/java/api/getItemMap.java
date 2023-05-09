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

public class getItemMap {
	// item 파싱 메서드
	public static Map<String, String> getItemMap() {
		Map<String, String> itemMap = new HashMap<String, String>();
		try {
			// 1. HTTP 요청 보내기
			String urlString = "https://ddragon.leagueoflegends.com/cdn/13.8.1/data/ko_KR/item.json";
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 2. HTTP 응답 처리하기
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				System.out.println("HTTP 응답 오류: " + responseCode);
				return itemMap;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// 3. JSON 데이터 파싱하기
			JsonObject itemJsonObject = JsonParser.parseString(content.toString()).getAsJsonObject();
			JsonObject items = itemJsonObject.getAsJsonObject("data");
			for (Map.Entry<String, JsonElement> entry : items.entrySet()) {
				JsonObject itemObject = entry.getValue().getAsJsonObject();
				String itemName = itemObject.get("name").getAsString();
				String itemId = entry.getKey();
				itemMap.put(itemId, itemName);
			}

			return itemMap;
		} catch (IOException e) {
			System.out.println("HTTP 요청 오류: " + e.getMessage());
			return itemMap;
		}
	}

}
