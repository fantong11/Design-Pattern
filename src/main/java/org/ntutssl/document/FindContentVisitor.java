package org.ntutssl.document;

public class FindContentVisitor { 
	private String target;
	private String result = null;

  	public FindContentVisitor(String target) {
		this.target = target;
  	}

  	public void visitParagraph(Paragraph paragraph) {
		
  	}

  	public void visitTitle(Title title) {
		
  	}

  	public void visitArticle(Article article) {
		
  	}
	  
  	public List<Document> getResult() {
	
  	}
}
