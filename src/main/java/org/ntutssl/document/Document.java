package org.ntutssl.document;

import java.util.Iterator;

public interface Document {  
  	public default int getLevel() {
		throw new DocumentException("getLevel invalid.");
  	}
  
  	public default void add(Document document) {
		throw new DocumentException("Add invalid.");
  	}
  
  	public default Iterator<Document> iterator() {
		throw new NullIterator();
  	}
  
  	public default int getSize() {
    
  	}

  	public String getText();

  	public void accept(Visitor visitor);

  	public String toString();
}
