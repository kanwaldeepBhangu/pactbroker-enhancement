package enhancement;

import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

public class RequestClientConfiguration {

	private static String userName;

	private static String password;

	private static RestTemplate restTemplate;

	public static RestTemplate configureRequestClient() {
		userName = System.getProperty("pact.broker.username");
		password = System.getProperty("pact.broker.password");
		restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(userName, password));
		return restTemplate;
	}
}
