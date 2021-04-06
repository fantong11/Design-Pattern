package org.ntutssl.document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindContentVisitor implements Visitor<List<Document>> {
	private String target;
	private List<Document> result;

	public FindContentVisitor(String target) {
		this.target = target.toLowerCase();
		this.result = new ArrayList<>();
	}

	public void visitParagraph(Paragraph paragraph) {
		boolean textIsFound = paragraph.getText().toLowerCase().contains(target);
		if (textIsFound) {
			result.add(paragraph);
		}
	}

	public void visitTitle(Title title) {
		boolean textIsFound = title.getText().toLowerCase().contains(target);
		if (textIsFound) {
			result.add(title);
		}
	}

	public void visitArticle(Article article) {
		Iterator<Document> it = article.iterator();
		boolean textIsFound = article.getText().toLowerCase().contains(target);
		if (textIsFound) {
			result.add(article);
		}

		while (it.hasNext()) {
			Document nextDocument = it.next();
			if (nextDocument instanceof Article) {
				visitArticle((Article) nextDocument);
			} else if (nextDocument instanceof Title) {
				visitTitle((Title) nextDocument);
			} else if (nextDocument instanceof Paragraph) {
				visitParagraph((Paragraph) nextDocument);
			}
		}
	}

	public List<Document> getResult() {
		return this.result;
	}
}
