package enhancement;

import org.junit.BeforeClass;
import org.junit.Test;

public class RequestClientConfigurationTest {

	@BeforeClass
	public static void setSystemProperties() {
		System.setProperty(PactUtilityConstants.PACT_BROKER_USERNAME, "username");
		System.setProperty(PactUtilityConstants.PACT_BROKER_PASSWORD, "password");
	}

	@Test
	public void testConfigureRequestClient() {
		org.junit.Assert.assertNotNull(RequestClientConfiguration.configureRequestClient());
	}
}
