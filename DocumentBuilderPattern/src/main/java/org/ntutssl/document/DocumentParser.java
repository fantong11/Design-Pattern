package org.ntutssl.document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

class DocumentParser {
	private DocumentBuilder documentBuilder;

	public DocumentParser() {
		documentBuilder = new DocumentBuilder();
	}

	/**
	 * parse a JsonObject as a Document
	 * 
	 * @param jObj the json object to be parsed
	 */
	public Document parse(JsonObject jObj) {
		String type = jObj.get("type").getAsString();
		String text = jObj.get("text").getAsString();

		switch (type) {
			case "title":
				String size = jObj.get("size").getAsString();
				documentBuilder.buildTitle(text, Integer.valueOf(size));
				break;
		
			case "paragraph":
				documentBuilder.buildParagraph(text);
				break;

			case "article":
				JsonArray jsonArray = new JsonArray();
				jsonArray = jObj.get("contents").getAsJsonArray();
				int level = Integer.valueOf(jObj.get("level").getAsString());

				documentBuilder.startBuildArticle(text, level);
				for (JsonElement jsonElement : jsonArray) {
					parse(jsonElement.getAsJsonObject());
				}

				documentBuilder.endBuildArticle();
				break;

			default:
				throw new DocumentException("Parsing failed.");
		}
		return documentBuilder.getResult();
	}
}