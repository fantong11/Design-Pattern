package org.ntutssl.document;

public interface Document {
    default public Document getContent() { return null; }

    default public int getLevel() { return 0; }

    default public void add(Document document) {}
}
