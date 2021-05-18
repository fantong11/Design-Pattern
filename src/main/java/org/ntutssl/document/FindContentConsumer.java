package org.ntutssl.document;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class FindContentConsumer implements Consumer<Document> {
	private List<Document> result;
	private String target;
	/**
	 * @param result you should add the document which contains `target` into here.
	 */
	public FindContentConsumer(List<Document> result, String target) {
		this.result = result;
		this.target = target.toLowerCase();
	}

	public void accept(Document document) {
		if (document.getText().toLowerCase().contains(target)) {
			if (target.trim().length() > 0) {
				result.add(document);
			} else if (document.getText().trim().length() == 0) {
				result.add(document);
			}
		}

		if (document instanceof Article) {
			Iterator<Document> it = document.iterator();
			it.forEachRemaining(this);
		}
	}
}