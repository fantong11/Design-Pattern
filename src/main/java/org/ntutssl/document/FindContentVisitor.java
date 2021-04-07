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
		if (paragraph.getText().toLowerCase().contains(target)) {
			if (target.trim().length() > 0) {
				result.add(paragraph);
			} else if (paragraph.getText().trim().length() == 0) {
				result.add(paragraph);
			}
		}
	}

	public void visitTitle(Title title) {
		if (title.getText().toLowerCase().contains(target)) {
			if (target.trim().length() > 0) {
				result.add(title);
			} else if (title.getText().trim().length() == 0) {
				result.add(title);
			}
		}
	}

	public void visitArticle(Article article) {
		Iterator<Document> it = article.iterator();

		if (article.getText().toLowerCase().contains(target)) {
			if (target.trim().length() > 0) {
				result.add(article);
			} else if (article.getText().trim().length() == 0) {
				result.add(article);
			}
		}

		while (it.hasNext()) {
			Document nextDocument = it.next();
			FindContentVisitor fcv = new FindContentVisitor(this.target);
			nextDocument.accept(fcv);
			List<Document> documentResult = fcv.getResult();
			for (Document document : documentResult) {
				this.result.add(document);
			}
		}
	}

	public List<Document> getResult() {
		return this.result;
	}
}
