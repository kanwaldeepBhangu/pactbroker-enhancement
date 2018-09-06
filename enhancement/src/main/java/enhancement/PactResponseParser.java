package enhancement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PactResponseParser {

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

	public static String filterResponse(String response) {
		JSONObject json = new JSONObject(response);
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"provider\":" + (JSONObject) json.get("provider") + ",");
		builder.append("\"consumer\":" + (JSONObject) json.get("consumer") + ",");
		builder.append("\"interactions\":" + (JSONArray) json.get("interactions") + ",");
		builder.append("\"metadata\":" + (JSONObject) json.get("metadata"));
		builder.append("}");
		return builder.toString();
	}

	private static JSONArray getJSONPactsArray(JSONObject json) {
		return (JSONArray) json.getJSONObject("_links").get("pacts");
	}

	private static List<String> populateHrefsLinkFromJSONArray(JSONArray jsonArray) {
		List<String> hrefsList = new ArrayList<>();
		Iterator<Object> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			JSONObject pact = (JSONObject) iterator.next();
			hrefsList.add((String) pact.get("href"));
		}
		return hrefsList;
	}
}
