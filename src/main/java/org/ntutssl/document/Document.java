package org.ntutssl.document;

public interface Document {
    default public Document getContent() {
        throw new DocumentException("getContent invalid.");
    }

    default public int getLevel() { 
        throw new DocumentException("getLevel invalid.");
    }

    default public void add(Document document) {
        throw new DocumentException("add invalid.");
    }
}
