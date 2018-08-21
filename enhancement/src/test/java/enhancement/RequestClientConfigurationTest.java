package enhancement;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class RequestClientConfigurationTest {

	@BeforeClass
	public static void setSystemProperties() {
		System.setProperty("pact.broker.username", "username");
		System.setProperty("pact.broker.password", "password");
	}

	@Test
	public void testConfigureRequestClient() {
		org.junit.Assert.assertNotNull(RequestClientConfiguration.configureRequestClient());
	}
}
