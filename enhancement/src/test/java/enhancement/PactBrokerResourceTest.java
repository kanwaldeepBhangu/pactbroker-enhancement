package enhancement;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

public class PactBrokerResourceTest {

	@BeforeClass
	public static void setSystemProperties() {
		
		if(System.getProperty(PactUtilityConstants.PACT_BROKER_USERNAME)==null)
		{
			System.out.println("No Environment Variable Set");
			System.setProperty(PactUtilityConstants.PACT_BROKER_USERNAME, "xxx");
			System.setProperty(PactUtilityConstants.PACT_BROKER_PASSWORD, "xxx");
			System.setProperty(PactUtilityConstants.PACT_BROKER_HOST, "kanwaldeepsingh.pact.dius.com.au");
			System.setProperty(PactUtilityConstants.PACT_BROKER_TARGET_FOLDER, "pactFolder");
		}
	}

	@Test
	public void testDownloadLatestPactsBasedOnProvider() {
		PactBrokerResource resource = new PactBrokerResource();
		try {
			resource.downloadLatestPactsBasedOnProvider("pact_provider");
		} catch (PactBrokerUtilityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test
	public void testDownloadLatestPactsBasedOnProviderAndTags() {
		PactBrokerResource resource = new PactBrokerResource();
		try {
			resource.downloadLatestPactsBasedOnProviderAndTags("pact_provider", "dev");
		} catch (PactBrokerUtilityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
	/*
	 * @Test public void testDownloadLatestPactsBasedOnParticipants() {
	 * PactBrokerResource resource =new PactBrokerResource(); }
	 */
}
