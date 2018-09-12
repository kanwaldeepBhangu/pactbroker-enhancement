package enhancement;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;

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
	public void testEnvVar()
	{
		//assertEquals("mF97CxbFf5kUBQCWdLk9QMw16UQ8yl", System.getProperty(PactUtilityConstants.PACT_BROKER_USERNAME));
		assertEquals("82ALVC3qNxc2L0RLhtX34ZJ6lc5Prf", System.getProperty(PactUtilityConstants.PACT_BROKER_PASSWORD));
		assertEquals("kanwaldeepsingh.pact.dius.com.au", System.getProperty(PactUtilityConstants.PACT_BROKER_HOST));
		assertEquals("pactFolder", System.getProperty(PactUtilityConstants.PACT_BROKER_TARGET_FOLDER));
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
