package enhancement;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PushTestPactVerifyResults {
	
	static RestTemplate template;
	
	PushTestPactVerifyResults(){
		template = RequestClientConfiguration.configureRequestClient();
	}
	

	public boolean pushResultsToBroker(String provider ,String testResults)
	{
		String response = template.getForObject(
				RequestPaths.getBASE_URL() + RequestPaths.getURL_BASED_ON_PROVIDER("pact_provider"), String.class);
		List<String> pactUrls = PactResponseParser.getPactLinksList(response);
		List<String> pacts = getPacts(pactUrls);
		List<String> verifyResultsUrl = new ArrayList();
		pacts.forEach((pact) -> {
			verifyResultsUrl.add(getUrlForPublishReports(pact));
		});
		verifyResultsUrl.forEach((url)->{
			postMessage(url,testResults);
		});
		return false;
		
	}
	private static List<String> getPacts(List<String> links) {
		final List<String> list = new ArrayList();
		links.forEach((link) -> {
			list.add(template.getForObject(link, String.class));
		});
		return list;
	}

	private static String getUrlForPublishReports(String pact) {
		JSONObject json = new JSONObject(pact);
		JSONObject links = (JSONObject) json.get("_links");
		JSONObject testResult = (JSONObject) links.get("pb:publish-verification-results");
		return (String) testResult.get("href");
	}

	private static void postMessage(String url, String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(message, headers);
		template.postForObject(url, entity, String.class);
		}
}
