package enhancement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.json.JSONObject;

public class PactWriter {

	static String fileName;

	public static void writePactfileInTargetDiretory(Map<String, String> input) {
		try {
			for (Map.Entry<String, String> pair : input.entrySet()) {
				writePact(pair.getKey(), pair.getValue());
			}
		} catch (Throwable cause) {
			cause.printStackTrace();
			throw new PactBrokerUtilityException("Exception in Pact broker Utility with cause", cause);
		}
	}

	private static void writePact(String filename, String response) throws IOException {
		String targetFolder = System.getProperty(PactUtilityConstants.PACT_BROKER_TARGET_FOLDER);
		FileWriter fileWriter = null;
		try {
			if (!Files.isDirectory(Paths.get(targetFolder))) {
				File file = new File(targetFolder);
				file.mkdir();
			}
			fileWriter = new FileWriter(targetFolder + "/" + filename);
			fileWriter.write(formatStringInJson(response));
		} catch (Exception cause) {
			throw new PactBrokerUtilityException("Exception in writing file ", cause);
		} finally {
			fileWriter.flush();
			fileWriter.close();
		}
	}

	private static String formatStringInJson(String response) {
		JSONObject json = new JSONObject(response);
		return json.toString(2);
	}

}
