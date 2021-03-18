package org.ntutssl.document;

import java.util.ArrayList;
import java.util.List;

public class Article implements Document {
	private List<Document> documents;
	private String topic;
	private int level;

	public Article(String topic, int level) {
		this.documents = new ArrayList<>();
		this.topic = topic;
		/*if (level <= 0) throw new DocumentException("Not allow low level.");
		else*/ this.level = level;
	}

	public Document getContent(int index) {
		return documents.get(index);
	}

	public String getText() {
		return this.topic;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public void add(Document document) {
		this.documents.add(document);
	}
}
