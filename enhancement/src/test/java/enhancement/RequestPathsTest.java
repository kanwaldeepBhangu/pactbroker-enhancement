package enhancement;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RequestPathsTest {

	static RequestPaths requestPaths;

	@BeforeClass
	public static void setSystemProperties() {
		System.setProperty("pact.broker.host", "kanwaldeepsingh.pact.dius.com.au");
		requestPaths = new RequestPaths();
	}

	@Test
	public void testgetURL_BASED_ON_PROVIDER_AND_TAGS() {
		String expected = "/pacts/provider/provider/latest/tag";
		String actual = requestPaths.getURL_BASED_ON_PROVIDER_AND_TAGS("provider", "tag");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testgetURL_BASED_ON_PROVIDER() {
		String expected = "/pacts/provider/provider/latest";
		String actual = requestPaths.getURL_BASED_ON_PROVIDER("provider");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testgetURL_BASED_ON_PARTICIPANTS() {
		String expected = "/pacticipants/pacticipant/latest-version";
		String actual = requestPaths.getURL_BASED_ON_PARTICIPANTS("pacticipant");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testgetBASE_URL() {
		String expected = "https://kanwaldeepsingh.pact.dius.com.au";
		String actual = requestPaths.getBASE_URL();
		Assert.assertEquals(expected, actual);
	}
}
