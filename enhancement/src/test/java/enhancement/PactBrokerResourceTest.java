package enhancement;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

public class PactBrokerResourceTest {
	
	@BeforeClass
	public static void setSystemProperties() {
		System.setProperty("pact.broker.username", "XXX");
		System.setProperty("pact.broker.password", "XXX");
		System.setProperty("pact.broker.host", "XXX");
		System.setProperty("pact.broker.targetFolder", "XXX");
	}
	
	
	@Test
	public void testDownloadLatestPactsBasedOnProvider()
	{
		PactBrokerResource resource =new PactBrokerResource();
		try {
			resource.downloadLatestPactsBasedOnProvider("pact_provider");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	@Test
	public void testDownloadLatestPactsBasedOnProviderAndTags()
	{
		PactBrokerResource resource =new PactBrokerResource();
		try {
			resource.downloadLatestPactsBasedOnProviderAndTags("pact_provider", "dev");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
/*	@Test
	public void testDownloadLatestPactsBasedOnParticipants()
	{
		PactBrokerResource resource =new PactBrokerResource();
	}*/
}
