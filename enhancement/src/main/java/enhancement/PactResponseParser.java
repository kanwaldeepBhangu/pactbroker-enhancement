package enhancement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PactResponseParser {

	public static String getFilename(String response) {
		JSONObject json = new JSONObject(response);
		JSONObject provider_name = getJSONObjectFromJsonByKey(PactUtilityConstants.PROVIDER, json);
		JSONObject consumer_name = getJSONObjectFromJsonByKey(PactUtilityConstants.CONSUMER, json);
		return consumer_name.getString(PactUtilityConstants.NAME) + "-"
				+ provider_name.getString(PactUtilityConstants.NAME) + PactUtilityConstants.DOT_JSON;
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
		builder.append("\"provider\":" + getJSONObjectFromJsonByKey(PactUtilityConstants.PROVIDER, json) + ",");
		builder.append("\"consumer\":" + getJSONObjectFromJsonByKey(PactUtilityConstants.CONSUMER, json) + ",");
		builder.append("\"interactions\":" + json.getJSONArray(PactUtilityConstants.INTERACTIONS) + ",");
		builder.append("\"metadata\":" + getJSONObjectFromJsonByKey(PactUtilityConstants.METADATA, json));
		builder.append("}");
		return builder.toString();
	}

	private static JSONArray getJSONPactsArray(JSONObject json) {
		return json.getJSONObject(PactUtilityConstants.LINKS).getJSONArray(PactUtilityConstants.PACTS);
	}

	private static List<String> populateHrefsLinkFromJSONArray(JSONArray jsonArray) {
		List<String> hrefsList = new ArrayList<>();
		Iterator<Object> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			Object pact = iterator.next();
			if (pact instanceof JSONObject) {
				JSONObject json = (JSONObject) pact;
				hrefsList.add(json.getString(PactUtilityConstants.HREF));
			}
		}
		return hrefsList;
	}

	private static JSONObject getJSONObjectFromJsonByKey(String key, JSONObject json) {
		if (json.get(key) instanceof JSONObject)
			return json.getJSONObject(key);
		else
			throw new PactBrokerUtilityException("Get operation on json is not in not returning JSONObject");
	}

}
