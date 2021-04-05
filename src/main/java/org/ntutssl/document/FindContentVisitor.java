package org.ntutssl.document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FindContentVisitor implements Visitor<List<Document>> { 
	private String target;
	private List<Document> result;
	private Iterator<Document> it = null;

  	public FindContentVisitor(String target) {
		this.target = target.toLowerCase();
		this.result = new ArrayList<>();
  	}

  	public void visitParagraph(Paragraph paragraph) {
		boolean textIsFound = paragraph.getText().toLowerCase().indexOf(this.target) != 1 ? true : false;
		if (textIsFound) {
			result.add(paragraph);
		}
  	}

  	public void visitTitle(Title title) {
		boolean textIsFound = title.getText().toLowerCase().indexOf(this.target) != 1 ? true : false;
		if (textIsFound) {
			result.add(title);
		}
  	}

  	public void visitArticle(Article article) {
		it = article.iterator();
		while (it.hasNext()) {
			Document temp = it.next();
			if (temp instanceof Article) {
				boolean textIsFound = temp.getText().toLowerCase().indexOf(this.target) != -1 ? true : false;
				if (textIsFound) {
					result.add(temp);
				}
			}
		}
  	}
	  
  	public List<Document> getResult() {
		return this.result;
  	}
}
