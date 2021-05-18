package org.ntutssl.document;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class Editor {
	private List<Document> editor;
	private DocumentParser documentParser;

	public Editor() {
		editor = new ArrayList<>();
		documentParser = new DocumentParser();
	}

	public void importDocumentFromJsonFile(String filePath) {
		try (JsonReader reader = new JsonReader(new FileReader(filePath))) {
			Gson gson = new Gson();
			JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
			for (JsonElement jsonElement : jsonArray) {
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				editor.add(documentParser.parse(jsonObject));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exportDocumentAsHtmlFile(String outputPath) {
		List<String> result = new ArrayList<>();
		HtmlOutputConsumer hoc = new HtmlOutputConsumer(result);
		iterator().forEachRemaining(hoc);
		try (FileWriter fileWriter = new FileWriter(new File(outputPath))) {
			for (String text : result) {
				fileWriter.write(text);
				fileWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void findContent(String target) {
		List<Document> result = new ArrayList<>();
		FindContentConsumer fcc = new FindContentConsumer(result, target);
		iterator().forEachRemaining(fcc);
		for (Document document : result) {
			System.out.print(document.toString());
		}
	}

	public void add(Document document) {
		editor.add(document);
	}

	public int size() {
		return editor.size();
	}

	public Iterator<Document> iterator() {
		return editor.iterator();
	}
}