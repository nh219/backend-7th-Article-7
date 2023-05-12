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

public class spellToName {
	// ��ȯ�� �ֹ� ��
	private static Map<Long, String> spellIdToName = new HashMap<Long, String>();

	// ��ȯ�� �ֹ� ����
	public static String getSpellName(long spellId) {
		try {
			// 1. HTTP ��û ������
			String championDataUrl = "https://ddragon.leagueoflegends.com/cdn/13.9.1/data/ko_KR/summoner.json";
			URL url = new URL(championDataUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// 2. HTTP ���� ó���ϱ�
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				return "HTTP ���� ����: " + responseCode;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// 3. JSON ������ �Ľ��ϱ�
			JsonObject summoner = JsonParser.parseString(content.toString()).getAsJsonObject();
			JsonObject summonerSpellData = summoner.getAsJsonObject("data");
			for (Map.Entry<String, JsonElement> entry : summonerSpellData.entrySet()) {
				JsonObject championObject = entry.getValue().getAsJsonObject();
				if (championObject.get("key").getAsString().equals(String.valueOf(spellId))) {
					String championName = championObject.get("name").getAsString();
					spellIdToName.put(spellId, championName);
					return championName;
				}
			}

			return "�ش��ϴ� ��ȯ�� �ֹ��� �����ϴ�.";
		} catch (IOException e) {
			return "HTTP ��û ����: " + e.getMessage();
		}
	}

}
