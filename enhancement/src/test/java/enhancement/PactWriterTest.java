package enhancement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PactWriterTest {

	@BeforeClass
	public static void setSystemProperties() {
		System.setProperty("pact.broker.targetFolder", "pactFolder");
	}

	@Test
	public void testWritePactfileInTargetDiretory() {
		Map<String, String> input = new HashMap<>();
		input.put("test.json", "{\r\n" + "	\"DummyJson\": \"DummyValue\"\r\n" + "}");

		try {
			PactWriter.writePactfileInTargetDiretory(input);
		} catch (PactBrokerUtilityException e) {
			Assert.fail();
		}
	}
}
