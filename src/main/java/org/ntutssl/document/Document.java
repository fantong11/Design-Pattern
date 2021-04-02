package org.ntutssl.document;

public interface Document {  
  	public default int getLevel() {
		throw new DocumentException("getLevel invalid.");
  	}
  
  	public default void add(Document document) {
		throw new DocumentException("Add invalid.");
  	}
  
  	public default Iterator<Document> iterator() {
    
  	}
  
  	public default int getSize() {
    
  	}

  	public String getText();

  	public void accept(Visitor visitor);

  	public String toString();
}
