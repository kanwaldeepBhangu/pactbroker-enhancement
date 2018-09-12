package enhancement;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class PushTestPactVerifyResults {

	static RestTemplate template;

	PushTestPactVerifyResults() {
		template = RequestClientConfiguration.configureRequestClient();
	}

	public boolean pushResultsToBroker(String provider, String testResults) {
		String response = template.getForObject(
				RequestPaths.getBASE_URL() + RequestPaths.getURL_BASED_ON_PROVIDER(provider), String.class);
		List<String> pactUrls = PactResponseParser.getPactLinksList(response);
		List<String> pacts = getPacts(pactUrls);
		List<String> verifyResultsUrl = new ArrayList<>();
		pacts.forEach((pact) -> {
			verifyResultsUrl.add(getUrlForPublishReports(pact));
		});
		verifyResultsUrl.forEach((url) -> {
			postMessage(url, testResults);
		});
		return true;
	}

	private static List<String> getPacts(List<String> links) {
		final List<String> list = new ArrayList<>();
		links.forEach((link) -> {
			list.add(template.getForObject(link, String.class));
		});
		return list;
	}

	private static String getUrlForPublishReports(String pact) {
		JSONObject json = new JSONObject(pact);
		JSONObject links = json.getJSONObject(PactUtilityConstants.LINKS);
		JSONObject testResult = links.getJSONObject("pb:publish-verification-results");
		return testResult.getString(PactUtilityConstants.HREF);
	}

	private static void postMessage(String url, String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(message, headers);
		try {
			template.postForObject(new URI(url), entity, String.class);
		} catch (RestClientException e) {
			throw new PactBrokerUtilityException("Exception while posting result to broker", e);
		} catch (URISyntaxException e) {
			throw new PactBrokerUtilityException("Url to URI conversion failed", e);
		} catch (Exception ex) {
			throw new PactBrokerUtilityException("Excecption during publish result", ex);
		}
	}
}
