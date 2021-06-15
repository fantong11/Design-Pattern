package org.ntutssl.document;

import java.util.Iterator;

public class HtmlOutputVisitor implements Visitor<String> {
	private String result;

	public HtmlOutputVisitor() {
		this.result = "";
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
			for (int i = 0; i < (article.getLevel()) * 2; result += " ", i++)
				;
			it.next().accept(this);
		}
		for (int i = 0; i < (article.getLevel() - 1) * 2; result += " ", i++)
			;
		result += "</article>\n";
	}

	public String getResult() {
		return result;
	}
}
