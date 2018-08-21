package enhancement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class PactResponeParser {

	public static String getFilename(String response) {
		JSONObject json = new JSONObject(response);
		JSONObject provider_name = (JSONObject) json.get("provider");
		JSONObject consumer_name = (JSONObject) json.get("consumer");
		return consumer_name.getString("name") + "-" + provider_name.getString("name") + ".json";
	}

	public static List<String> getPactLinksList(String response) {
		JSONObject json = new JSONObject(response);
		JSONArray array = getJSONPactsArray(json);
		return populateHrefsLinkFromJSONArray(array);
	}

	private static JSONArray getJSONPactsArray(JSONObject json) {
		return (JSONArray) json.getJSONObject("_links").get("pacts");
	}

	private static List<String> populateHrefsLinkFromJSONArray(JSONArray jsonArray) {
		List<String> hrefsList = new ArrayList();
		Iterator iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			JSONObject pact = (JSONObject) iterator.next();
			hrefsList.add((String) pact.get("href"));
		}
		return hrefsList;
	}

}
