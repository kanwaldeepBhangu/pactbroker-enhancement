package enhancement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class PactBrokerResource {

	RestTemplate template;

	PactBrokerResource() {
		template = RequestClientConfiguration.configureRequestClient();
	}

	public void downloadLatestPactsBasedOnProvider(String provider) throws IOException {
		String response = template.getForObject(
				RequestPaths.getBASE_URL() + RequestPaths.getURL_BASED_ON_PROVIDER(provider), String.class);
		List<String> pactUrls = PactResponseParser.getPactLinksList(response);
		PactWriter.writePactfileInTargetDiretory(getMap(pactUrls));
	}

	public void downloadLatestPactsBasedOnProviderAndTags(String provider, String tag) throws IOException {
		String response = template.getForObject(
				RequestPaths.getBASE_URL() + RequestPaths.getURL_BASED_ON_PROVIDER_AND_TAGS(provider, tag),
				String.class);
		List<String> pactUrls = PactResponseParser.getPactLinksList(response);
		PactWriter.writePactfileInTargetDiretory(getMap(pactUrls));
	}

	public void downloadLatestPactsBasedOnParticipants(String participants) throws IOException {
		String response = template.getForObject(
				RequestPaths.getBASE_URL() + RequestPaths.getURL_BASED_ON_PARTICIPANTS(participants), String.class);
		List<String> pactUrls = PactResponseParser.getPactLinksList(response);
		PactWriter.writePactfileInTargetDiretory(getMap(pactUrls));
	}

	private Map<String, String> getMap(List<String> links) {
		Map<String, String> map = new HashMap<>();
		Iterator<String> iterator = links.iterator();
		while (iterator.hasNext()) {
			String response = template.getForObject((String) iterator.next(), String.class);
			map.put(PactResponseParser.getFilename(response), PactResponseParser.filterResponse(response));
		}
		return map;
	}
}
