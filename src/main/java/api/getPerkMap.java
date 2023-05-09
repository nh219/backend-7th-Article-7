package api;

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
import com.google.gson.JsonSyntaxException;

public class getPerkMap {
	public static Map<Long, String> getPerkMap() {
		Map<Long, String> perkMap = new HashMap<Long, String>();
		try {
			// 1. HTTP ��û ������
			String urlString = "https://ddragon.leagueoflegends.com/cdn/13.8.1/data/ko_KR/runesReforged.json";
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 2. HTTP ���� ó���ϱ�
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				System.out.println("HTTP ���� ����: " + responseCode);
				return null;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// 3. JSON ������ �Ľ��ϱ�
			JsonArray runesArray = JsonParser.parseString(content.toString()).getAsJsonArray();
			for (JsonElement runeElement : runesArray) {
				JsonObject runeObject = runeElement.getAsJsonObject();
				long perkId = runeObject.get("id").getAsLong();
				String perkStyle = runeObject.get("name").getAsString();
				for (JsonElement perkElement : runeObject.get("slots").getAsJsonArray()) {
					for (JsonElement perkSubElement : perkElement.getAsJsonObject().get("runes").getAsJsonArray()) {
						JsonObject perkObject = perkSubElement.getAsJsonObject();
						long perkId1 = perkObject.get("id").getAsLong();
						String perkName = perkObject.get("name").getAsString();
						perkMap.put(perkId1, perkName);
						if (perkName == null) {
							break;
						}
					}
				}
				perkMap.put(perkId, perkStyle);
			}
			return perkMap;
		} catch (IOException e) {
			System.out.println("HTTP ��û ����: " + e.getMessage());
			return null;
		} catch (JsonSyntaxException e) {
			System.out.println("JSON �Ľ� ����: " + e.getMessage());
			return null;
		}
	}

}
