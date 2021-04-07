package org.ntutssl.document;

import java.util.Iterator;

public class HtmlOutputVisitor implements Visitor<String> {
	String result = "";

	public HtmlOutputVisitor() {

	}

	public void visitParagraph(Paragraph paragraph) {
		result += "<p>" + paragraph.getText() + "</p>\n";

	}

	public void visitTitle(Title title) {
		result += "<h" + title.getSize() + ">" + title.getText() + "</h" + title.getSize() + ">\n";
	}

	public void visitArticle(Article article) {
		Iterator<Document> it = article.iterator();
		result += "<article topic='" + article.getText() + "'>\n";

		while (it.hasNext()) {
			Document nextDocument = it.next();
			HtmlOutputVisitor hov = new HtmlOutputVisitor();

			nextDocument.accept(hov);
			String documentResult = hov.getResult();

			for (int i = 0; i < (article.getLevel()) * 2; i++) {
				result += " ";
			}
			result += documentResult;
		}
		for (int i = 0; i < (article.getLevel() - 1) * 2; i++) {
			result += " ";
		}
		result += "</article>\n";
	}

	public String getResult() {
		return result;
	}
}
