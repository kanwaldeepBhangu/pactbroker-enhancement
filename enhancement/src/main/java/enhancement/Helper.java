package enhancement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application.properties")
@Component
@ComponentScan
public class Helper {

public static void main(String args[]) {
	
	try {
				PactBrokerResource resource =new PactBrokerResource();
		resource.downloadLatestPactsBasedOnProvider("pact_provider");
	 System.out.println("Done Successfully");
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	 
}
