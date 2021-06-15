package org.ntutssl.document;

import java.util.Stack;

/**
 * Document builder helps you build a Document object.
 * 
 * Please note that the return value of each build procedure is DocumentBuilder
 * itself, so you can build a large object using a single statement.
 */
class DocumentBuilder {
	private Document document;
	private Stack<Document> stack;

	public DocumentBuilder() {
		this.stack = new Stack<>();
	}

	public DocumentBuilder buildTitle(String text, int size) {
		if (stack.empty()) {
			document = new Title(text, size);
		} else {
			stack.peek().add(new Title(text, size));
		}
		return this;
	}

	public DocumentBuilder buildParagraph(String text) {
		if (stack.empty()) {
			document = new Paragraph(text);
		} else {
			stack.peek().add(new Paragraph(text));
		}
		return this;
	}

	public DocumentBuilder startBuildArticle(String topic, int level) {
		stack.push(new Article(topic, level));
		return this;
	}

	public DocumentBuilder endBuildArticle() {
		Document currDocument = stack.pop();

		if (stack.empty()) {
			document = currDocument;
		} else {
			stack.peek().add(currDocument);
		}
		return this;
	}

	public Document getResult() {
		return document;
	}

}