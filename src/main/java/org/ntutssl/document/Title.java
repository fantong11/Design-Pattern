package org.ntutssl.document;

public class Title implements Document {
	private String text;
	private int fontSize;

  	public Title(String text, int size) {
		this.text = text;
  	}

  	public String getText() {
		return this.text;
  	}

  	public int getSize() {
		
  	}

  	@Override
  	public void accept(Visitor visitor) {
		visitor.visitTitle(this);
  	}

  	@Override
  	public String toString() {
		
  	}
}