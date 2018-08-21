package enhancement;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.Assert;


public class PactResponseParserTest {
	

	@Test
	public void testGetFilename()
	{
		String expected="pact_consumer-pact_provider.json";
		String actual=PactResponseParser.getFilename(getResponseFromFile("sample.json"));
        org.junit.Assert.assertEquals(expected, actual);	
	}
	
	@Test
	public void testGetPactLinksList()
	{
		List<String> list=PactResponseParser.getPactLinksList(getResponseFromFile("sampleResponse2.json"));	
        org.junit.Assert.assertEquals(list.size(), 2);
	}
	
	@Test
	public void testFilterResponse()
	{
		String filteredResponse=PactResponseParser.filterResponse(getResponseFromFile("sample.json"));
	    JSONObject json=new JSONObject(filteredResponse);
	    Assert.assertNull(json.opt("createdAt"));
	}
	 private  String getResponseFromFile(String fileName) {
			StringBuilder result = new StringBuilder("");
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					result.append(line).append("\n");
				}
				scanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result.toString();
		}
}
