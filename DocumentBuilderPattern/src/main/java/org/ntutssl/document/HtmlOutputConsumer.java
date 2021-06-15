package org.ntutssl.document;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class HtmlOutputConsumer implements Consumer<Document> {
	private List<String> result;
	private int level;
	private final int indent = 2;

	/**
	 * @param result you should add the result into here.
	 * 
	 *  			 example: 
	 * 				 this.result.get(0) = "<article topic='topic1'>\n";              
	 *               this.result.get(1) = "  <h1>title1</h1>\n";
	 *               this.result.get(2) = "  <p>paragraph1</p>\n";
	 *               this.result.get(3) = "  <h2>title2</h2>\n";
	 *               this.result.get(4) = "</article>\n";
	 */
	public HtmlOutputConsumer(List<String> result) {
		this.result = result;
		this.level = 0;
	}

	public void accept(Document document) {
		String indentCount = "";
		for (int i = 0; i < level * indent; indentCount += " ", i++)
				;

		if (document instanceof Paragraph) {
			result.add(indentCount + "<p>" + document.getText() + "</p>\n");
		} else if (document instanceof Title) {
			result.add(indentCount + "<h" + document.getSize() + ">" + document.getText() + "</h" + document.getSize() + ">\n");
		} else if (document instanceof Article) {
			result.add(indentCount + "<article topic='" + document.getText() + "'>\n");
			Iterator<Document> it = document.iterator();

			level++;
			it.forEachRemaining(this);
			level--;

			result.add(indentCount + "</article>\n");

		}
	}
}