package org.ntutssl.document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Article implements Document {
	private List<Document> documents;
	private String topic;
	private int level;

	public Article(String topic, int level) {
		this.documents = new ArrayList<>();
		this.topic = topic;
		this.level = level;
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
		if (document.getClass().equals(Article.class)) {
			if (document.getLevel() <= getLevel())
				throw new DocumentException("Level must be higher than current article.");
		}
		this.documents.add(document);
	}

	@Override
	public void remove(Document document) {
		documents.remove(document);
	}

	@Override
	public Iterator<Document> iterator() {
		return documents.iterator();
	}

	@Override
	public String toString() {
		return "Article\t\ttopic: " + topic + "\n\t\tlevel: " + level + "\n";
	}
}
