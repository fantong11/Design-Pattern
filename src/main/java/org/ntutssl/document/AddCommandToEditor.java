package org.ntutssl.document;

public class AddCommandToEditor implements Command {
	private Editor target;
	private Document document;
	/**
	 * @param target   the target editor
	 * @param document the document to be added
	 */
	public AddCommandToEditor(Editor target, Document document) {
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
