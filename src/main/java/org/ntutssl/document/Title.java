package org.ntutssl.document;

public class Title implements Document {
	private String text;
	private int fontSize;

  	public Title(String text, int size) {
		this.text = text;
		this.fontSize = size;
  	}

  	public String getText() {
		return this.text;
  	}

  	public int getSize() {
		return fontSize;
  	}

  	@Override
  	public void accept(Visitor visitor) {
		visitor.visitTitle(this);
  	}

  	@Override
  	public String toString() {
		return "Title\t\ttext: " + text + "\n\t\tsize: " + fontSize;
  	}
}