package org.ntutssl.document;

public class AddCommandToArticle implements Command {
	private Article target;
	private Document document;

	/**
	 * @param target   the target document
	 * @param document the document to be added
	 */
	public AddCommandToArticle(Article target, Document document) {
		this.target = target;
		this.document = document;
	}

	public void execute() {
		target.add(document);
	}

	public void undo() {
		target.remove(document);
	}

	public void redo() {
		target.add(document);
	}
}
