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
	private static String username="mF97CxbFf5kUBQCWdLk9QMw16UQ8yl";
	
	private static String password="82ALVC3qNxc2L0RLhtX34ZJ6lc5Prf";
	
public static void main(String args[]) {
	
	try {
				PactBrokerResource resource =new PactBrokerResource();
		resource.downloadLatestPactsBasedOnProvider("pact_provider");
	 System.out.println("Done Successfully");
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	 
	
/*	public static void main(String args[]) throws URISyntaxException {
		RestTemplate template=new RestTemplate();
		template.getInterceptors().add(new BasicAuthorizationInterceptor(username, password));
		System.setProperty("pact.broker.host", "kanwaldeepsingh.pact.dius.com.au");
		String url=RequestPaths.getBASE_URL()+RequestPaths.getURL_BASED_ON_PROVIDER("pact_provider");
		String apiResponse = template.getForObject(url, String.class);
		JSONObject json=new JSONObject(apiResponse);
		JSONArray array=(JSONArray) json.getJSONObject("_links").get("pacts");
		Iterator i=array.iterator();
		List <String> URILIst=new ArrayList();
		while(i.hasNext())
		{
			 JSONObject object=(JSONObject)i.next();
			 object.get("href");
			 URILIst.add((String) object.get("href"));
		}
		Iterator<String> i2=URILIst.iterator();
		while(i2.hasNext())
		{
			System.out.println(template.getForObject(i2.next(), String.class));
			JSONObject json1=new JSONObject(template.getForObject(i2.next(), String.class));
			JSONObject obj=(JSONObject) json1.get("provider");
			System.out.println(obj.get("name"));
			
		}
		System.out.println(apiResponse);
	}
*/
	
	/*public static void main(String args[]) throws URISyntaxException {
		//URI uri=new URI("");
		  String username="mF97CxbFf5kUBQCWdLk9QMw16UQ8yl";
		  String password="82ALVC3qNxc2L0RLhtX34ZJ6lc5Prf";
	
		RestTemplate template=new RestTemplate();
		template.getInterceptors().add(new BasicAuthorizationInterceptor(username, password));
		String apiResponse = template.getForObject("https://kanwaldeepsingh.pact.dius.com.au/pacts/latest", String.class);
		JSONObject json=new JSONObject(apiResponse);
		try {
			PactWriter.writePactfileInTargetDiretory(apiResponse, "pactFolder/pact1.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(apiResponse);
	}
*/}
