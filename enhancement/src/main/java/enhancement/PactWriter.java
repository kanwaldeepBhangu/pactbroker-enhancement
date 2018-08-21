package enhancement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PactWriter {

	static String fileName;

	public static void writePactfileInTargetDiretory(Map<String, String> input) throws IOException {
		for (Map.Entry<String, String> pair : input.entrySet()) {
			writePact(pair.getKey(), pair.getValue());
		}
	}

	private static void writePact(String filename, String response) throws IOException {
		String targetFolder = System.getProperty("pact.broker.targetFolder");
		if (!Files.isDirectory(Paths.get(targetFolder))) 
		{
			File file = new File(targetFolder);
			file.mkdir();
		}
		fileName = targetFolder + "/" + filename;
		FileWriter file = new FileWriter(fileName);
		file.write(formatStringInJson(response));
		file.flush();
	}

	private static String formatStringInJson(String response)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Object json = mapper.readValue(response, Object.class);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
	}

}
