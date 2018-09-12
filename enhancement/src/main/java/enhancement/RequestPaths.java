package enhancement;

public class RequestPaths {

	private static String URL_BASED_ON_PROVIDER_AND_TAGS = "/pacts/provider/{{provider}}/latest/{{tag}}";

	private static String URL_BASED_ON_PROVIDER = "/pacts/provider/{{provider}}/latest";

	private static String URL_BASED_ON_PARTICIPANTS = "/pacticipants/{{pacticipant}}/latest-version";

	public static String getURL_BASED_ON_PROVIDER_AND_TAGS(String provider, String tag) {
		return URL_BASED_ON_PROVIDER_AND_TAGS.replace("{{provider}}", provider).replace("{{tag}}", tag);
	}

	public static String getURL_BASED_ON_PROVIDER(String provider) {
		return URL_BASED_ON_PROVIDER.replace("{{provider}}", provider);
	}

	public static String getURL_BASED_ON_PARTICIPANTS(String pacticipant) {
		return URL_BASED_ON_PARTICIPANTS.replace("{{pacticipant}}", pacticipant);
	}

	public static String getBASE_URL() {
		return "https://" + System.getProperty(PactUtilityConstants.PACT_BROKER_HOST);
	}

}
